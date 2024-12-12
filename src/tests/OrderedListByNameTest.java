package tests;

import entities.IList;
import entities.OrderedListByName;
import entities.Task;

import java.time.LocalDate;
import java.util.List;

import entities.ToDoList;
import enums.Level;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListByNameTest {
    @Test
    public void getTasks(){
        Task t1 = new Task("Zebra", Level.EASY, LocalDate.now());
        Task t2= new Task("Abelha", Level.MEDIUM, LocalDate.now());
        Task t3 = new Task("Elefante", Level.HARD, LocalDate.now());
        ToDoList list = ToDoList.getInstance();
        list.addTask(t1);
        list.addTask(t2);
        list.addTask(t3);
        IList orderedByName = new OrderedListByName(list);
        List<Task> tasksList = orderedByName.getTasks();
        String first, second, third;
        first = tasksList.getFirst().getName();
        tasksList.remove(tasksList.getFirst());
        second = tasksList.getFirst().getName();
        tasksList.remove(tasksList.getFirst());
        third = tasksList.getFirst().getName();
        tasksList.remove(tasksList.getFirst());
        assertEquals("Abelha", first);
        assertEquals("Elefante", second);
        assertEquals("Zebra", third);
    }
}
