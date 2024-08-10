package trabalhoFinal;

public class Main {
    public static void main(String[] args) {
        // Instancia o Gerenciador com 10 karts e 10 capacetes disponíveis
        Gerenciador gerenciador = new Gerenciador(10, 10);
        
        // Cria o Relatório, passando o Gerenciador como parâmetro
        Relatorio relatorio = new Relatorio(gerenciador);
        
        // Instancia o GeradorThreads que cria e gerencia os pilotos
        GeradorThreads gerador = new GeradorThreads(gerenciador, relatorio);
        
        // Cria 12 pilotos (threads) para a simulação
        gerador.criarPilotos(12);

        try {
            // Aguarda um tempo suficiente para a simulação terminar
            // Aqui estou assumindo 2 minutos (120 segundos)
            Thread.sleep(120000); // 120 segundos em milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Após a simulação, gera o relatório final
        relatorio.gerarRelatorio();
    }
}
