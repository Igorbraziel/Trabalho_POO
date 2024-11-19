package entities;

import java.util.List;

public interface IList {
    void addTask(Task task);
    void removeTask(Task task);
    void updateTask(Task currentTask, Task newTask);
    Task getTask(int index);
    List<Task> getTasks();
    void showList();
    int size();
}
