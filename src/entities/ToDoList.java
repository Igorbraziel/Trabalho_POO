package entities;

import exceptions.IdException;

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

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public void updateTask(Task currentTask, Task newTask){ // Conferir o funcionamento
        Task task = tasks.stream().filter(t -> t.getId() == currentTask.getId()).findFirst().orElse(null);
        if(task != null){
            task = newTask;
        }
    }

    public Task getTask(int id){
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void showList(){
        for(Task t : tasks){
            System.out.println(t);
        }
    }

    public int size(){
        return tasks.size();
    }
}
