package trabalhoFinal;

public abstract class Cliente {
    private String nome;
    private int idade;
    private long tempoEspera;
    private long tempoUso;
    private long tempoChegada;

    public Cliente(String nome, int idade, long tempoEspera, long tempoUso) {
        this.nome = nome;
        this.idade = idade;
        this.tempoEspera = tempoEspera;
        this.tempoUso = tempoUso;
        this.tempoChegada = System.currentTimeMillis(); // Define o tempo de chegada no momento da criação
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public long getTempoEspera() {
        return tempoEspera;
    }

    public long getTempoUso() {
        return tempoUso;
    }

    public long getTempoChegada() {
        return tempoChegada;
    }

    public abstract boolean prioridade();
}
