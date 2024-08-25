package trabalhoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int totalPilotos = 57;
        int tamanhoGrupo = 15;

        Gerenciador gerenciador = new Gerenciador(5, 5);
        Relatorio relatorio = new Relatorio(gerenciador, totalPilotos);

        Semaphore semaphore = new Semaphore(25);

        List<Thread> threads = new ArrayList<>();

        // Definindo o tempo de funcionamento do kartódromo (8 horas = 28.800.000 milissegundos)
        long tempoAberto = 5 * 60 * 1000; // 2 minutos para teste
        long inicioOperacao = System.currentTimeMillis();

        // Contadores para clientes não atendidos
        int gruposAtendidos = 0;

        for (int i = 0; i < totalPilotos; i += tamanhoGrupo) {
            // Checando se o tempo de funcionamento excedeu o limite definido
            if (System.currentTimeMillis() - inicioOperacao >= tempoAberto) {
                System.out.println("Tempo de funcionamento do kartódromo encerrou.");
                break;
            }

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

            gruposAtendidos++;

            try {
                // Checando novamente se o tempo de funcionamento excedeu o limite definido
                if (System.currentTimeMillis() - inicioOperacao >= tempoAberto) {
                    System.out.println("Tempo de funcionamento do kartódromo encerrou.");
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Gerando relatório...");
        relatorio.gerarRelatorio();
    }
}
