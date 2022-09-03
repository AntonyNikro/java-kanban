import practicum.manager.*;
import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

public class Main {

    public static void main(String[] args) {


        TaskManager taskManager = Managers.getDefaultTask();

        Task task1 = new Task(0, "1 Название задачи 1", "1 Описание задачи 1", Status.NEW);
        Task task2 = new Task(0, "2 Название задачи 2", "2 Описание задачи 2", Status.NEW);
        taskManager.addTask(task1);
        taskManager.getHistoryManager().addHistory(task1);
        taskManager.addTask(task2);
        taskManager.getHistoryManager().addHistory(task2);


        Epic epic1 = new Epic(0,"1 Название эпик 1", "1 Описание эпик 1", Status.NEW);
        taskManager.addEpic(epic1);
        taskManager.getHistoryManager().addHistory(epic1);
        Epic epic2 = new Epic(0, "2 Название эпик 2", "2 Описание эпик 2", Status.NEW);
        taskManager.addEpic(epic2);
        taskManager.getHistoryManager().addHistory(epic2);
        SubTask subTask1 = new SubTask(0, "1 Название подзадачи 1", "1 Описание подзадачи 1", Status.NEW, epic1.getId());
        taskManager.addSubTask(subTask1);
        taskManager.getHistoryManager().addHistory(subTask1);
        SubTask subTask2 = new SubTask(0, "2 Название подзадачи 2", "2 Описание подзадачи 2", Status.NEW, epic1.getId());
        taskManager.addSubTask(subTask2);
        taskManager.getHistoryManager().addHistory(subTask2);
        SubTask subTask3 = new SubTask(0, "3 Название подзадачи 3", "3 Описание подзадачи 3", Status.NEW, epic1.getId());
        taskManager.addSubTask(subTask3);
        taskManager.getHistoryManager().addHistory(subTask3);


        taskManager.getTaskId(1);
        taskManager.getTaskId(2);
        taskManager.getEpicId(1);
        taskManager.getSubTaskId(1);
        taskManager.getSubTaskId(2);
        taskManager.getSubTaskId(3);
        taskManager.getEpicId(2);
        taskManager.removeTaskId(1);
        taskManager.removeEpicId(1);

        for (Task task : taskManager.getHistoryManager().getHistory()) {
            System.out.println(task);
        }

    }

}
