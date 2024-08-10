package trabalhoFinal;

import java.util.Random;

public class GeradorThreads {
    private Gerenciador gerenciador;
    private Relatorio relatorio;

    public GeradorThreads(Gerenciador gerenciador, Relatorio relatorio) {
        this.gerenciador = gerenciador;
        this.relatorio = relatorio;
    }

    public void criarPilotos(int quantidadePilotos) {
        Random random = new Random();
        for (int i = 0; i < quantidadePilotos; i++) {
            int idade = random.nextInt(50); // Idade aleatória entre 0 e 49
            String nome = "Piloto " + (i + 1);
            long tempoEspera = 5000 + random.nextInt(5000); // Tempo de espera aleatório entre 5 e 10 segundos
            long tempoUso = 60000; // Tempo de uso fixo de 1 minuto

            Cliente cliente;
            if (idade <= 14) {
                cliente = new Crianca(nome, idade, tempoEspera, tempoUso);
            } else {
                cliente = new Adulto(nome, idade, tempoEspera, tempoUso);
            }

            Piloto piloto = new Piloto(cliente, gerenciador, relatorio);
            piloto.start(); // Inicia a thread

            // Simula o intervalo entre a chegada dos pilotos
            try {
                Thread.sleep(random.nextInt(5000)); // Intervalo aleatório entre 0 e 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
