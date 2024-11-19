package entities;

import enums.Level;
import exceptions.IdException;
import exceptions.TaskException;
import observer.ListObservable;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ToDoList implements IList, ListObservable {
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
    public void updateTask(Task currentTask, Task newTask){ // Conferir o funcionamento
        Task task = tasks.stream().filter(t -> t.getId() == currentTask.getId()).findFirst().orElse(null);
        if(task != null){
            task = newTask;
        }
    }

    @Override
    public Task getTask(int id){
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
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
    //--------------------------------


    // GoF Observer methods;
    @Override
    public void notifyTask(Task task, Object obj){
        task.update(obj);
    }

    @Override
    public void notifyTasks(Object obj){
        for(Task t : tasks){
            t.update(obj);
        }
    }
    //--------------------------------

    public void finishTask(Task task){
        System.out.println("A tarefa foi concluida com sucesso, parabéns!");
        System.out.println("Tarefa Concluida: " + task);
        this.removeTask(task);
    }

    public void cleanList(){
        notifyTasks(getInstance());
    }

    public void doTask(Task task, Integer percentageDone){
        notifyTask(task, percentageDone);
    }

    public void changeDeadlineDay(Task task, LocalDate deadlineDay){
        notifyTask(task, deadlineDay);
    }

    public void changeDifficultyLevel(Task task, Level difficultyLevel){
        notifyTask(task, difficultyLevel);
    }
}
