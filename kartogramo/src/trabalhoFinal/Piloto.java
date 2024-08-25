package trabalhoFinal;

public class Piloto implements Runnable {
  private Cliente cliente;
  private Gerenciador gerenciador;
  private Relatorio relatorio;

  public Piloto(Cliente cliente, Gerenciador gerenciador, Relatorio relatorio) {
    this.cliente = cliente;
    this.gerenciador = gerenciador;
    this.relatorio = relatorio;
  }

  @Override
  public void run() {
    try {
      boolean recursoEncontrado = buscarRecursos();
      long tempoEspera = System.currentTimeMillis() - cliente.getTempoChegada();

      if (recursoEncontrado) {
        relatorio.registrarAtendimento(cliente, tempoEspera);

        Thread.sleep(cliente.getTempoUso());

        gerenciador.liberarRecursos(cliente);
        System.out.println("Cliente " + cliente.getNome() + " usou os recursos e foi embora.");
      } else {
        relatorio.registrarNaoAtendimento(cliente, tempoEspera);
        System.out.println("Cliente " + cliente.getNome() + " não conseguiu obter os recursos e foi embora.");
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Thread interrompida: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      gerenciador.getSemaphoreCapacetes().release();
      gerenciador.getSemaphoreKarts().release();
    }
  }

  private boolean buscarRecursos() {
    long tempoInicio = System.currentTimeMillis();
    boolean recursosDisponiveis = false;

    while (System.currentTimeMillis() - tempoInicio < cliente.getTempoEspera()) {
      if (gerenciador.atribuirRecursos(cliente)) {
        recursosDisponiveis = true;
        break;
      } else {
        try {
          Thread.sleep(100); 
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          System.err.println("Thread interrompida durante a espera: " + e.getMessage());
        }
      }
    }

    return recursosDisponiveis;
  }
}