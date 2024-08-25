package trabalhoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Gerenciador {
  private List<Kart> karts;
  private List<Capacete> capacetes;
  private Semaphore semaphoreKarts;
  private Semaphore semaphoreCapacetes;
  private int kartsUtilizados;
  private int capacetesUtilizados;

  public Gerenciador(int numKarts, int numCapacetes) {
    this.karts = new ArrayList<>();
    this.capacetes = new ArrayList<>();
    for (int i = 0; i < numKarts; i++) {
      karts.add(new Kart(i));
    }
    for (int i = 0; i < numCapacetes; i++) {
      capacetes.add(new Capacete(i));
    }
    this.semaphoreKarts = new Semaphore(numKarts, true);
    this.semaphoreCapacetes = new Semaphore(numCapacetes, true);
    this.kartsUtilizados = 0;
    this.capacetesUtilizados = 0;
  }

  public boolean atribuirRecursos(Cliente cliente) {
    try {
      boolean kartAlocado = false;
      boolean capaceteAlocado = false;

      semaphoreKarts.acquire();
      semaphoreCapacetes.acquire();

      synchronized (karts) {
        for (Kart kart : karts) {
          if (kart.isDisponivel()) {
            kart.alocar(cliente);
            kartsUtilizados++;
            kartAlocado = true;
            break;
          }
        }
      }

      synchronized (capacetes) {
        for (Capacete capacete : capacetes) {
          if (capacete.isDisponivel()) {
            capacete.alocar(cliente);
            capacetesUtilizados++;
            capaceteAlocado = true;
            break;
          }
        }
      }

      if (kartAlocado && capaceteAlocado) {
        return true;

      } else {
        if (kartAlocado) {
          liberarRecursos(cliente); 
        }
        if (capaceteAlocado) {
          liberarRecursos(cliente); 
        }
        return false;
      }

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupção ao tentar adquirir recursos: " + e.getMessage());
      return false;
    } finally {
      semaphoreKarts.release();
      semaphoreCapacetes.release();

    }
  }

  public void liberarRecursos(Cliente cliente) {
    liberarRecursoKart(cliente);
    liberarRecursoCapacete(cliente);
  }

  private void liberarRecursoKart(Cliente cliente) {
    synchronized (karts) {
      for (Kart kart : karts) {
        if (kart.getClienteAtual() != null && kart.getClienteAtual().equals(cliente)) {
          kart.liberar();
          break;
        }
      }
    }
  }

  private void liberarRecursoCapacete(Cliente cliente) {
    synchronized (capacetes) {
      for (Capacete capacete : capacetes) {
        if (capacete.getClienteAtual() != null && capacete.getClienteAtual().equals(cliente)) {
          capacete.liberar();
          break;
        }
      }
    }
  }

  public int getKartsDisponiveis() {
    return semaphoreKarts.availablePermits();
  }

  public int getCapacetesDisponiveis() {
    return semaphoreCapacetes.availablePermits();
  }

  public void setSemaphores(Semaphore semaphoreKarts, Semaphore semaphoreCapacetes) {
    this.semaphoreKarts = semaphoreKarts;
    this.semaphoreCapacetes = semaphoreCapacetes;
  }

  public Semaphore getSemaphoreKarts() {
    return semaphoreKarts;
  }

  public Semaphore getSemaphoreCapacetes() {
    return semaphoreCapacetes;
  }

  public int getKartsUtilizados() {
    return kartsUtilizados;
  }

  public int getCapacetesUtilizados() {
    return capacetesUtilizados;
  }

}
