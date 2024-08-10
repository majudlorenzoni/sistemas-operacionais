package trabalhoFinal;

public class Adulto extends Cliente {
    public Adulto(String nome, int idade, long tempoEspera, long tempoUso) {
        super(nome, idade, tempoEspera, tempoUso);
    }

    @Override
    public boolean prioridade() {
        return false; // Adultos não têm prioridade
    }
}
