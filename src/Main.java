public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        System.out.println("Создание задачи");

        Task task1 = new Task(0, "1 Название задачи 1", "1 Описание задачи 1", "NEW");
        Task task2 = new Task(0, "2 Название задачи 2", "2 Описание задачи 2", "NEW");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        System.out.println(taskManager.taskHash);

        System.out.println("Создание эпика с 2 подзадачами");

        Epic epic1 = new Epic(1, "1 Название эпик 1", "1 Описание эпик 1", "NEW");
        taskManager.addEpic(epic1);
        SubTask subTask1 = new SubTask(0, "1 Название подзадачи 1", "1 Описание подзадачи 1",
                "NEW", epic1.getId());
        taskManager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask(0, "2 Название подзадачи 2", "2 Описание подзадачи 2",
                "NEW", epic1.getId());
        taskManager.addSubTask(subTask2);
        System.out.println(taskManager.epicHash);
        System.out.println(taskManager.subTaskHash);

        System.out.println("Создание эпика с 2 подзадачами");

        Epic epic2 = new Epic(2, "2 Название эпик 2", "2 Описание эпик 2", "NEW");
        taskManager.addEpic(epic2);
        SubTask subTask3 = new SubTask(1, "1 Название подзадачи 1", "1 Описание подзадачи 1",
                "NEW", epic2.getId());
        taskManager.addSubTask(subTask3);
        System.out.println(taskManager.epicHash);
        System.out.println(taskManager.subTaskHash);
        System.out.println(taskManager.getTaskId(1));

        System.out.println("Обновление задачи");

        Task taskUpdate = new Task(1, "Обновили название задачи 1", "Обновили описание задачи 1", "IN_PROGRESS");
        taskUpdate.setId(1);
        taskManager.updateTask(taskUpdate);
        taskManager.updateTask(new Task(2, "Обновили название задачи 2", "Обновили описание задачи 2", "DONE"));
        System.out.println("Список обновленных задач: " + taskManager.taskHash);

        System.out.println("Обновление подзадачи");

        taskManager.updateSubTask(new SubTask(3, "Обновили название подзадачи 1 эпик 1", "Обновили описание подзадачи 1", "IN_PROGRESS", epic1.getId()));
        taskManager.updateSubTask(new SubTask(4, "Обновили название подзадачи 2 эпик 1", "Обновили описание подзадачи 1", "DONE", epic1.getId()));
        taskManager.updateSubTask(new SubTask(5, "Обновили название подзадачи 1 эпик 2", "Обновили описание подзадачи 1", "DONE", epic2.getId()));
        System.out.println(taskManager.epicHash);
        System.out.println(taskManager.subTaskHash);

        System.out.println("Получение списка всех подзадач определённого эпика: " + taskManager.getEpicSubTasks(epic2.getId()));

    }

}
