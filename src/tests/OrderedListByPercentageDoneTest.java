package tests;

import entities.IList;
import entities.ToDoList;
import entities.OrderedListByPercentageDone;
import entities.Task;

import java.time.LocalDate;
import java.util.List;

import enums.Level;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListByPercentageDoneTest {
    @Test
    public void getTasks(){
        Task t1 = new Task("Task 1", Level.EASY, LocalDate.now());
        Task t2= new Task("Task 2", Level.MEDIUM, LocalDate.now());
        Task t3 = new Task("Task 3", Level.HARD, LocalDate.now());
        ToDoList list = ToDoList.getInstance();
        list.addTask(t1);
        list.addTask(t2);
        list.addTask(t3);
        list.doTask(t1, 60);
        list.doTask(t2, 90);
        list.doTask(t3, 10);
        IList orderedByPercentage = new OrderedListByPercentageDone(list);
        List<Task> tasksList = orderedByPercentage.getTasks();
        int first, second, third;
        first = tasksList.getFirst().getPercentageDone();
        tasksList.remove(tasksList.getFirst());
        second = tasksList.getFirst().getPercentageDone();
        tasksList.remove(tasksList.getFirst());
        third = tasksList.getFirst().getPercentageDone();
        tasksList.remove(tasksList.getFirst());
        assertEquals(10, first);
        assertEquals(60, second);
        assertEquals(90, third);
    }
}