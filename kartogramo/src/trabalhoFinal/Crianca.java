package trabalhoFinal;

public class Crianca extends Cliente {
    public Crianca(String nome, int idade, long tempoEspera, long tempoUso) {
        super(nome, idade, tempoEspera, tempoUso);
    }

    @Override
    public boolean prioridade() {
        return true; // Crianças têm prioridade
    }
}
