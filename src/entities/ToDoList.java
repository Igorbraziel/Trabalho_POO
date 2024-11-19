package entities;

import java.util.List;
import java.util.ArrayList;

public class ToDoList implements IList {
    private static ToDoList instance;
    private List<Task> tasks;

    private ToDoList(){
        tasks = new ArrayList<>();
    }

    public static ToDoList getInstance(){
        if(instance == null){
            instance = new ToDoList();
        }

        return instance;
    }

    boolean addTask(Task task);
    boolean removeTask(Task task);
    boolean updateTask(Task currentTask, Task newTask);
    Task getTask(int index);
    List<Task> getTasks();
    void showList();
    int size();
    boolean joinLists(IList list);
}
