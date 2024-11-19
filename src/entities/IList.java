package entities;

import java.util.List;

public interface IList {
    boolean addTask(Task task);
    boolean removeTask(Task task);
    boolean updateTask(Task currentTask, Task newTask);
    Task getTask(int index);
    List<Task> getTasks();
    void showList();
    int size();
    boolean joinLists(IList list);
}
