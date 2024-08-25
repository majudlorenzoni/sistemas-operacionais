package trabalhoFinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private List<Cliente> clientesAtendidos;
    private List<Cliente> clientesNaoAtendidos;
    private Gerenciador gerenciador;
    private int totalPilotos;

    public Relatorio(Gerenciador gerenciador, int totalPilotos) {
        this.gerenciador = gerenciador;
        clientesAtendidos = new ArrayList<>();
        clientesNaoAtendidos = new ArrayList<>();
        this.totalPilotos = totalPilotos;
    }

    public synchronized void registrarAtendimento(Cliente cliente, long tempoEspera) {
        clientesAtendidos.add(cliente);
        System.out
                .println("Cliente atendido: " + cliente.getNome() + ", Tempo de espera: " + tempoEspera + " milissegundos");
    }

    public synchronized void registrarNaoAtendimento(Cliente cliente, long tempoEspera) {
        clientesNaoAtendidos.add(cliente);
        System.out
                .println("Cliente não atendido: " + cliente.getNome() + ", Tempo de espera: " + tempoEspera + " milissegundos");
    }

    public void gerarRelatorio() {
        long tempoSimulacao = 120; // Duração da simulação em segundos
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            writer.write("Relatório do Kartódromo Parallel Speed:");
            writer.newLine();
            writer.write("Duração da simulação: " + tempoSimulacao + " segundos");
            writer.newLine();
            writer.write("Total de clientes atendidos: " + clientesAtendidos.size());
            writer.newLine();

            long totalCriancasAtendidas = clientesAtendidos.stream().filter(c -> c instanceof Crianca).count();
            long totalAdultosAtendidos = clientesAtendidos.stream().filter(c -> c instanceof Adulto).count();

            writer.write("Total de crianças atendidas: " + totalCriancasAtendidas);
            writer.newLine();
            writer.write("Total de adultos atendidos: " + totalAdultosAtendidos);
            writer.newLine();

            double tempoMedioEspera = clientesAtendidos.stream()
                    .mapToLong(c -> System.currentTimeMillis() - c.getTempoChegada())
                    .average()
                    .orElse(0.0);
            writer.write("Tempo médio de espera para clientes atendidos: " + tempoMedioEspera + " milissegundos");
            writer.newLine();

            writer.write("Total de clientes não atendidos no kartogramo: " + clientesNaoAtendidos.size());
            writer.newLine();
            for (Cliente cliente : clientesNaoAtendidos) {
                writer.write("Cliente não atendido: " + cliente.getNome() + ", Tempo de espera: "
                        + (System.currentTimeMillis() - cliente.getTempoChegada()) + " milissegundos");
                writer.newLine();
            }

            int totalClientesNaoAtendidosPorFechamento = totalPilotos - (clientesAtendidos.size() + clientesNaoAtendidos.size());

            writer.write("Total de clientes não atendidos devido ao fechamento: " + totalClientesNaoAtendidosPorFechamento);
            writer.newLine();

            int kartsUtilizados = gerenciador.getKartsUtilizados();
            int capacetesUtilizados = gerenciador.getCapacetesUtilizados();

            writer.write("Quantidade de karts utilizados: " + kartsUtilizados);
            writer.newLine();
            writer.write("Quantidade de capacetes utilizados: " + capacetesUtilizados);
            writer.newLine();

            // Adicional: Total de recursos alocados (karts e capacetes) e estatísticas
            // sobre o tempo de espera
            writer.write("Recursos alocados (Karts e Capacetes): " + (kartsUtilizados + capacetesUtilizados));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de relatório: " + e.getMessage());
        }
        System.exit(0);
    }
}
