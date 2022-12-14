package practicum.manager.save_file;

import practicum.manager.*;
import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {

    private static File file;
    public FileBackedTasksManager (File file) {
        this.file = file;
    }

    public static FileBackedTasksManager loadFromFile(File file) throws FileNotFoundException {
        FileBackedTasksManager tasksManager = new FileBackedTasksManager(file);
        List<Integer> history = null;
        try {
            String csv = Files.readString(file.toPath());
            String[] lines = csv.split(System.lineSeparator());
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (line.isEmpty()) {
                    line = lines[i + 1];
                    history = TaskManagerCSVFormatter.historyFromString(line);
                    break;
                }
                String[] type = line.split(",");
                String typeTask = type[3];
                switch (typeTask){
                    case "TASK" :
                        Task task = TaskManagerCSVFormatter.taskFromString(lines[i]);
                        tasksManager.taskHash.put(task.getId(), task);
                        tasksManager.updateAddId(task.getId());
                        break;
                    case "EPIC" :
                        Epic epic = TaskManagerCSVFormatter.epicFromString(lines[i]);
                        tasksManager.epicHash.put(epic.getId(), epic);
                        tasksManager.updateAddId(epic.getId());
                        break;
                    case "SUBTASK" :
                        SubTask subtask = TaskManagerCSVFormatter.subtaskFromString(lines[i]);
                        tasksManager.subTaskHash.put(subtask.getId(), subtask);
                        tasksManager.updateAddId(subtask.getId());
                        break;
                }
            }
        } catch(IOException e) {
            throw new FileNotFoundException("???????? ???? ????????????????");
        }
        return tasksManager;
    }

    public void save() {
        
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(TaskManagerCSVFormatter.getHeader());
            for (Task task : taskHash.values()) {
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            for (Task epic : epicHash.values()) {
                fileWriter.write(epic.toString() + System.lineSeparator());
            }
            for (Task subtask : subTaskHash.values()) {
                fileWriter.write(subtask.toString() + System.lineSeparator());
            }
            fileWriter.write(System.lineSeparator());
            fileWriter.write(TaskManagerCSVFormatter.toString(this.getHistoryManager()));
            fileWriter.close();
        } catch(IOException e) {
            throw new ManagerSaveException("????????????. ???????? ???? ????????????????");
        }
    }

    @Override
    public void deleteTasks() {

        super.deleteTasks();
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubTasks() {
        super.deleteSubTasks();
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int addTask(Task task) {
        int id = super.addTask(task);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int addSubTask(SubTask subTask) {
        int id = super.addSubTask(subTask);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
       }
        return id;
    }

    @Override
    public int addEpic(Epic epic) {
       int id = super.addEpic(epic);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        super.updateEpicStatus(epic);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTaskId(int id) {
        super.removeTaskId(id);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSubTaskId(int id) {
        super.removeSubTaskId(id);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEpicId(int id) {
        super.removeEpicId(id);
        try {
            save();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File(String.valueOf(file)));
        Task task1 = new Task("???????????? 1","???????????????? ???????????? 1", TaskType.TASK, Status.NEW);
        fileBackedTasksManager.addTask(task1);
        Task task2 = new Task("???????????? 2", "???????????????? ???????????? 2", TaskType.TASK, Status.NEW);
        fileBackedTasksManager.addTask(task2);
        Epic epic1 = new Epic("???????? 1", "???????????????? ?????????? 1", TaskType.EPIC);
        fileBackedTasksManager.addEpic(epic1);
        SubTask subtask1 = new SubTask("?????????????????? 1", "???????????????? ?????????????????? 1",epic1.getId(), TaskType.SUBTASK);
        fileBackedTasksManager.addSubTask(subtask1);
        SubTask subtask2 = new SubTask("?????????????????? 2", "???????????????? ?????????????????? 2",epic1.getId(), TaskType.SUBTASK);
        fileBackedTasksManager.addSubTask(subtask2);

        fileBackedTasksManager.getTaskId(task1.getId());
        fileBackedTasksManager.getTaskId(task2.getId());
        fileBackedTasksManager.getEpicId(epic1.getId());
        fileBackedTasksManager.getSubTaskId(subtask1.getId());
        fileBackedTasksManager.getSubTaskId(subtask2.getId());
        System.out.println(fileBackedTasksManager.getTasks());
        System.out.println(fileBackedTasksManager.getSubTasks());
        System.out.println(fileBackedTasksManager.getEpic());
    }
}
