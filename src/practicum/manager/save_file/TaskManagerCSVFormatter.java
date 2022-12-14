package practicum.manager.save_file;

import practicum.manager.HistoryManager;
import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerCSVFormatter {

   public static String getHeader() {
       return "id,type,name,status,description,epic";
   }

    public static List<Integer> historyFromString(String value) {
        List<Integer> result = new ArrayList<>();
        String[] lines = value.split(",");
        for(String line :lines) {
            result.add(Integer.parseInt(line));
        }
        return result;
    }

    public static Task taskFromString(String value) {
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        TaskType taskType = typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];

        Task task = new Task(name, description,  taskType, status);
        task.setId(id);
        task.setStatus(status);
        return task;
    }

    public static Epic epicFromString(String value) {
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        TaskType taskType = typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];

        Epic epic = new Epic(name, description, taskType);
        epic.setId(id);
        epic.setStatus(status);
        return epic;
    }

    public static SubTask subtaskFromString(String value) {
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        TaskType taskType = typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];
        int epic = Integer.parseInt(lines[5]);

        SubTask subtask = new SubTask(name, description, epic, taskType);
        subtask.setId(id);
        subtask.setStatus(status);
        return subtask;
    }
    static String toString(HistoryManager historyManager) {
        List<Task> tasks = historyManager.getHistory();
        List<String> ids = new ArrayList<>();
        for (Task task : tasks) {
            int id = task.getId();
            ids.add(String.valueOf(id));
        }
        return String.join(",", ids);
    }
    public static TaskType typeFromString(String string) {
        switch (string) {
            case "TASK":
                return TaskType.TASK;
            case "EPIC":
                return TaskType.EPIC;
            case "SUBTASK":
                return TaskType.SUBTASK;
        }
        return null;
    }
    public static Status statusFromString (String string) {
        switch (string) {
            case "NEW":
                return Status.NEW;
            case "IN_PROGRESS":
                return Status.IN_PROGRESS;
            case "DONE":
                return Status.DONE;
        }
        return null;
    }

}
