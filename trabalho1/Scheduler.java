import java.io.*;
import java.util.*;

public class Scheduler {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Scheduler <tasks_file> <num_processors>");
            return;
        }

        String tasksFile = args[0];
        int numProcessors = Integer.parseInt(args[1]);

        List<Task> tasks = readTasks(tasksFile);

        // Imprimir as tarefas para verificar se foram lidas corretamente
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Escalonamento SJF
        List<Processor> sjfProcessors = createProcessors(numProcessors);
        tasks.sort(Comparator.comparingInt(task -> task.time)); //verifica a ordem das tarefas por tempo
        scheduleTasks(sjfProcessors, tasks);
        writeSchedule(sjfProcessors, "output_sjf.txt");

        // Escalonamento LJF
        List<Processor> ljfProcessors = createProcessors(numProcessors);
        tasks.sort((task1, task2) -> Integer.compare(task2.time, task1.time));
        scheduleTasks(ljfProcessors, tasks);
        writeSchedule(ljfProcessors, "output_ljf.txt");
    }

    private static List<Task> readTasks(String tasksFile) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tasksFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int duration = Integer.parseInt(parts[1]);
                tasks.add(new Task(name, duration));
            }
        }
        return tasks;
    }

    private static List<Processor> createProcessors(int numProcessors) {
      List<Processor> processors = new ArrayList<>();
      for (int i = 0; i < numProcessors; i++) {
          processors.add(new Processor());
      }
      return processors;
  }

  private static void scheduleTasks(List<Processor> processors, List<Task> tasks) {
    for (Task task : tasks) {
        Processor leastLoadedProcessor = processors.get(0);
        for (Processor processor : processors) {
            if (processor.currentTime < leastLoadedProcessor.currentTime) {
                leastLoadedProcessor = processor;
            }
        }
        leastLoadedProcessor.addTask(task);
    }
}

private static void writeSchedule(List<Processor> processors, String outputFile) throws IOException {
  try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
      for (int i = 0; i < processors.size(); i++) {
          bw.write("Processador_" + (i + 1));
          bw.newLine();
          for (String task : processors.get(i).tasks) {
              bw.write(task);
              bw.newLine();
          }
      }
  }
}
}

