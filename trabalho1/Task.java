class Task {
  String name;
  int time;

  public Task(String name, int time) {
    this.name = name;
    this.time = time;
  }

  @Override
  public String toString() {
      return "Tarefa: " + name + ", Tempo: " + time + "s";
  }
}