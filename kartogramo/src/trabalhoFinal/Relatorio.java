package trabalhoFinal;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private List<Cliente> clientesAtendidos;
    private List<Cliente> clientesNaoAtendidos;
    private Gerenciador gerenciador;

    public Relatorio(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
        clientesAtendidos = new ArrayList<>();
        clientesNaoAtendidos = new ArrayList<>();
    }

    public synchronized void registrarAtendimento(Cliente cliente, long tempoEspera) {
        clientesAtendidos.add(cliente);
        System.out.println("Cliente atendido: " + cliente.getNome() + ", Tempo de espera: " + tempoEspera + " milissegundos");
    }

    public synchronized void registrarNaoAtendimento(Cliente cliente, long tempoEspera) {
        clientesNaoAtendidos.add(cliente);
        System.out.println("Cliente não atendido: " + cliente.getNome() + ", Tempo de espera: " + tempoEspera + " milissegundos");
    }

    public void gerarRelatorio() {
        long tempoTotal = 120; // 2 minutos para simulação
        System.out.println("Relatório do Kartódromo Parallel Speed:");
        System.out.println("Duração do dia: " + tempoTotal + " segundos");
        System.out.println("Total de clientes atendidos: " + clientesAtendidos.size());
        System.out.println("Total de crianças atendidas: " + clientesAtendidos.stream().filter(c -> c instanceof Crianca).count());
        System.out.println("Total de adultos atendidos: " + clientesAtendidos.stream().filter(c -> c instanceof Adulto).count());

        double tempoMedioEspera = clientesAtendidos.stream().mapToLong(c -> System.currentTimeMillis() - c.getTempoChegada()).average().orElse(0.0);
        System.out.println("Tempo médio de espera: " + tempoMedioEspera + " milissegundos");

        System.out.println("Total de clientes não atendidos: " + clientesNaoAtendidos.size());
        for (Cliente cliente : clientesNaoAtendidos) {
            System.out.println("Cliente não atendido: " + cliente.getNome() + ", Tempo de espera: " + (System.currentTimeMillis() - cliente.getTempoChegada()) + " milissegundos");
        }

        System.out.println("Quantidade de karts utilizados: " + (10 - gerenciador.getKartsDisponiveis()));
        System.out.println("Quantidade de capacetes utilizados: " + (10 - gerenciador.getCapacetesDisponiveis()));
    }
}
