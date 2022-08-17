import practicum.manager.*;
import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

public class Main {

    public static void main(String[] args) {


        TaskManager inMemoryTaskManager = Managers.getDefaultTask();
        HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

        Task task1 = new Task(0, "1 Название задачи 1", "1 Описание задачи 1", Status.NEW);
        Task task2 = new Task(0, "2 Название задачи 2", "2 Описание задачи 2", Status.NEW);
        inMemoryTaskManager.addTask(task1);
        inMemoryHistoryManager.addHistory(task1);
        inMemoryTaskManager.addTask(task2);
        inMemoryHistoryManager.addHistory(task2);

        System.out.println("История:");
        inMemoryTaskManager.getTaskId(1);
        inMemoryTaskManager.getTaskId(2);


        for (Task task : inMemoryHistoryManager.getHistory()) {
            System.out.println(task);
        }


        /*System.out.println("Создание задачи");

        Task task1 = new Task(0, "1 Название задачи 1", "1 Описание задачи 1", Status.NEW);
        Task task2 = new Task(0, "2 Название задачи 2", "2 Описание задачи 2", Status.NEW);
        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);
        System.out.println(inMemoryTaskManager.getTasks());

        System.out.println("Создание эпика с 2 подзадачами");

        Epic epic1 = new Epic(1, "1 Название эпик 1", "1 Описание эпик 1", Status.NEW);
        inMemoryTaskManager.addEpic(epic1);
        SubTask subTask1 = new SubTask(0, "1 Название подзадачи 1", "1 Описание подзадачи 1",
                Status.NEW, epic1.getId());
        inMemoryTaskManager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask(0, "2 Название подзадачи 2", "2 Описание подзадачи 2",
                Status.NEW, epic1.getId());
        inMemoryTaskManager.addSubTask(subTask2);
        System.out.println(inMemoryTaskManager.getEpic());
        System.out.println(inMemoryTaskManager.getSubTasks());

        System.out.println("Создание эпика с 2 подзадачами");

        Epic epic2 = new Epic(2, "2 Название эпик 2", "2 Описание эпик 2", Status.NEW);
        inMemoryTaskManager.addEpic(epic2);
        SubTask subTask3 = new SubTask(1, "1 Название подзадачи 1", "1 Описание подзадачи 1",
                Status.NEW, epic2.getId());
        inMemoryTaskManager.addSubTask(subTask3);
        System.out.println(inMemoryTaskManager.getEpic());
        System.out.println(inMemoryTaskManager.getSubTasks());
        System.out.println(inMemoryTaskManager.getTaskId(1));

        System.out.println("Обновление задачи");

        Task taskUpdate = new Task(1, "Обновили название задачи 1", "Обновили описание задачи 1",
                Status.IN_PROGRESS);
        taskUpdate.setId(1);
        inMemoryTaskManager.updateTask(taskUpdate);
        inMemoryTaskManager.updateTask(new Task(2, "Обновили название задачи 2",
                "Обновили описание задачи 2", Status.DONE));
        System.out.println("Список обновленных задач: " + inMemoryTaskManager.getTasks());

        System.out.println("Обновление подзадачи");

        inMemoryTaskManager.updateSubTask(new SubTask(3, "Обновили название подзадачи 1 эпик 1",
                "Обновили описание подзадачи 1", Status.IN_PROGRESS, epic1.getId()));
        inMemoryTaskManager.updateSubTask(new SubTask(4, "Обновили название подзадачи 2 эпик 1",
                "Обновили описание подзадачи 1", Status.DONE, epic1.getId()));
        inMemoryTaskManager.updateSubTask(new SubTask(5, "Обновили название подзадачи 1 эпик 2",
                "Обновили описание подзадачи 1", Status.DONE, epic2.getId()));
        System.out.println(inMemoryTaskManager.getEpic());
        System.out.println(inMemoryTaskManager.getSubTasks());

        System.out.println("Получение списка всех подзадач определённого эпика: "
                + inMemoryTaskManager.epicSubTaskIds(epic2.getId()));
*/


    }

}
