package trabalhoFinal;

public abstract class Recursos {
  protected int id;
  protected boolean disponivel;

  public Recursos(int id) {
    this.id = id;
    this.disponivel = false;
  }

  public int getId() {
    return this.id;
  }

  public boolean isDisponivel() {
    return this.disponivel;
  }

  public void ocupar() {
    this.disponivel = true;
  }

  public void desocupar() {
    this.disponivel = false;
  }
}
