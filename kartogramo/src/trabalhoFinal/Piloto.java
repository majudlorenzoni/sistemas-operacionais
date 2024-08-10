package trabalhoFinal;

public class Piloto extends Thread {
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
          System.out.println("Cliente " + cliente.getNome() + " chegou.");

            boolean recursoEncontrado = buscarRecursos();
            if (recursoEncontrado) {
                long tempoEspera = System.currentTimeMillis() - cliente.getTempoChegada();
                relatorio.registrarAtendimento(cliente, tempoEspera);

                // Simula o tempo de uso dos recursos
                Thread.sleep(cliente.getTempoUso());
            } else {
                long tempoEspera = System.currentTimeMillis() - cliente.getTempoChegada();
                relatorio.registrarNaoAtendimento(cliente, tempoEspera);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean buscarRecursos() {
        return gerenciador.atribuirRecursos(cliente);
    }
}
