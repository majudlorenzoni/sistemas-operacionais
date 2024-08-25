package trabalhoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
  public static void main(String[] args) {
    Gerenciador gerenciador = new Gerenciador(5, 5);
    Relatorio relatorio = new Relatorio(gerenciador);

    Semaphore semaphore = new Semaphore(25); 
    GeradorThreads gerador = new GeradorThreads(gerenciador, relatorio, semaphore);

    int totalPilotos = 25;
    int tamanhoGrupo = 10;
    List<Thread> threads = new ArrayList<>();

    for (int i = 0; i < totalPilotos; i += tamanhoGrupo) {
      int inicio = i + 1;
      int fim = Math.min(i + tamanhoGrupo, totalPilotos);
      for (int j = inicio; j <= fim; j++) {
        Cliente cliente;
        if (j % 2 == 0) {
          cliente = new Crianca("Piloto " + j, 10 + (j % 10), 60000, 30000);
        } else {
          cliente = new Adulto("Piloto " + j, 20 + (j % 10), 60000, 30000);
        }

        Piloto piloto = new Piloto(cliente, gerenciador, relatorio);
        Thread thread = new Thread(piloto);
        thread.start();
        threads.add(thread);
        System.out.println("Piloto " + j + " criado e iniciado.");
      }

      for (Thread thread : threads) {
        try {
          thread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      threads.clear();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("Gerando relatório...");
    relatorio.gerarRelatorio();
  }
}
