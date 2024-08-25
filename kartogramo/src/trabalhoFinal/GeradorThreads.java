package trabalhoFinal;

import java.util.concurrent.Semaphore;

public class GeradorThreads {
  private Gerenciador gerenciador;
  private Relatorio relatorio;
  private Semaphore semaphore;

  public GeradorThreads(Gerenciador gerenciador, Relatorio relatorio, Semaphore semaphore) {
    this.gerenciador = gerenciador;
    this.relatorio = relatorio;
    this.semaphore = semaphore;
  }

  public void criarPilotos(int quantidade) {
    for (int i = 1; i <= quantidade; i++) {
      try {
        semaphore.acquire();

        Cliente cliente;
        if (i % 2 == 0) {
          cliente = new Crianca("Piloto " + i, 10 + (i % 10), 60000, 10000);
        } else {
          cliente = new Adulto("Piloto " + i, 20 + (i % 10), 60000, 10000);
        }

        Piloto piloto = new Piloto(cliente, gerenciador, relatorio);

        Thread thread = new Thread(piloto);
        thread.start();

        System.out.println("Piloto " + i + " criado e iniciado.");
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Erro ao adquirir permissão do semáforo: " + e.getMessage());
      }
    }
  }
}
