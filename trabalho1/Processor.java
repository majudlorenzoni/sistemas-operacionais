import java.util.ArrayList;
import java.util.List;

public class Processor {
    List<String> tasks;
    int currentTime;

    public Processor() {
        tasks = new ArrayList<>();
        currentTime = 0;
    }

    public void addTask(Task task) {
        tasks.add(task.name + ";" + currentTime + ";" + (currentTime + task.time));
        currentTime += task.time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }
}
