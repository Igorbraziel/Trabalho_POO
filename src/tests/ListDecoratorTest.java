package tests;

import entities.IList;
import entities.Task;
import enums.Level;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class ListDecoratorTest implements IList {
    private IList list;

    public ListDecoratorTest(IList list){
        this.list = list;
    }

    @Override
    public void addTask(Task task){
        list.addTask(task);
    }

    @Override
    public void removeTask(Task task){
        list.removeTask(task);
    }

    @Override
    public void updateTask(Task currentTask, Task newTask){
        list.updateTask(currentTask, newTask);
    }

    @Override
    public Task getTask(int id){
        return list.getTask(id);
    }

    @Override
    public List<Task> getTasks(){
        return list.getTasks();
    }

    @Override
    public void showList(){
        list.showList();
    }

    @Override
    public int size(){
        return list.size();
    }

    @Override
    public void finishTask(Task task){
        list.finishTask(task);
    }

    @Override
    public void cleanList(){
        list.cleanList();
    }

    @Override
    public void doTask(Task task, Integer percentageDone){
        list.doTask(task, percentageDone);
    }

    @Override
    public void changeDeadlineDay(Task task, LocalDate deadlineDay){
        list.changeDeadlineDay(task, deadlineDay);
    }

    @Override
    public void changeDifficultyLevel(Task task, Level difficultyLevel){
        list.changeDifficultyLevel(task, difficultyLevel);
    }

    public IList getList(){
        return list;
    }
}
