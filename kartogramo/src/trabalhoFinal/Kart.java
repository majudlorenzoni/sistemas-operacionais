package trabalhoFinal;

public class Kart {
    private int id;
    private Cliente clienteAtual;
    private boolean disponivel;

    public Kart(int id) {
        this.id = id;
        this.disponivel = true; // Inicialmente disponível
    }

    public int getId() {
        return id;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public synchronized void alocar(Cliente cliente) {
        if (disponivel) {
            this.clienteAtual = cliente;
            this.disponivel = false;
        } else {
            throw new IllegalStateException("Kart já alocado");
        }
    }

    public synchronized void liberar() {
        this.clienteAtual = null;
        this.disponivel = true;
    }
}
