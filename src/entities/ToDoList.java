package entities;

import enums.Level;

import observer.ListObservable;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

public final class ToDoList implements IList, ListObservable {
    private static ToDoList instance;
    private List<Task> tasks;

    // GoF Pattern Singleton:
    private ToDoList(){
        tasks = new ArrayList<>();
    }

    public static ToDoList getInstance(){
        if(instance == null){
            instance = new ToDoList();
        }

        return instance;
    }
    //--------------------------------

    // GoF Decorator methods:
    @Override
    public void addTask(Task task){
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task){
        tasks.remove(task);
    }

    @Override
    public void updateTask(Task currentTask, Task newTask){
        if(currentTask != null && newTask != null){
            newTask.setId(currentTask.getId());
            removeTask(currentTask);
            addTask(newTask);
        }
    }

    @Override
    public Task getTask(int id){
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Task> getTasks(){
        return tasks;
    }

    @Override
    public void showList(){
        for(Task t : tasks){
            System.out.println(t);
        }
    }

    @Override
    public int size(){
        return tasks.size();
    }

    @Override
    public void finishTask(Task task){
        this.removeTask(task);
    }

    @Override
    public void cleanList(){
        notifyTasks(getInstance());
    }

    @Override
    public void doTask(Task task, Integer percentageDone){
        notifyTask(task, percentageDone);
    }

    @Override
    public void changeDeadlineDay(Task task, LocalDate deadlineDay){
        notifyTask(task, deadlineDay);
    }

    @Override
    public void changeDifficultyLevel(Task task, Level difficultyLevel){
        notifyTask(task, difficultyLevel);
    }
    //--------------------------------


    // GoF Observer methods;
    @Override
    public void notifyTask(Task task, Object obj){
        task.update(obj);
    }

    @Override
    public void notifyTasks(Object obj){
        for(Task t : new ArrayList<Task>(tasks)){
            t.update(obj);
        }
    }
    //--------------------------------
}
