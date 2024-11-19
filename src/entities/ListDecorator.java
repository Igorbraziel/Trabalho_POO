package entities;

import java.util.List;

public abstract class ListDecorator implements IList {
    private ToDoList list;

    public ListDecorator(ToDoList list){
        this.list = list;
    }

    public abstract void addTask(Task task);
    public abstract void removeTask(Task task);
    public abstract void updateTask(Task currentTask, Task newTask);
    public abstract Task getTask(int index);
    public abstract List<Task> getTasks();
    public abstract void showList();
    public abstract int size();
}
