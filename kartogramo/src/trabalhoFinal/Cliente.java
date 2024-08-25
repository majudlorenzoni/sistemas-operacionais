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

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public boolean isCrianca() {
    return idade <= 14;
  }

  public long getTempoEspera() {
    return this.tempoEspera;
  }

  public void setTempoEspera(long tempoEspera) {
    this.tempoEspera = tempoEspera;
  }

  public long getTempoUso() {
    return tempoUso;
  }

  public void setTempoUso(long tempoUso) {
    this.tempoUso = tempoUso;
  }

  public long getTempoChegada() {
    return tempoChegada;
  }

  public abstract boolean prioridade();
}
