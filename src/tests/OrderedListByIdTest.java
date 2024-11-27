package tests;

import entities.IList;
import entities.OrderedListById;
import entities.Task;

import java.time.LocalDate;
import java.util.List;

import entities.ToDoList;
import enums.Level;
import org.junit.Test;
import static org.junit.Assert.*;

// WORKING
public class OrderedListByIdTest {
    @Test
    public void getTasks(){
        Task t1 = new Task("Task 1", Level.EASY, LocalDate.now());
        Task t2= new Task("Task 2", Level.MEDIUM, LocalDate.now());
        Task t3 = new Task("Task 3", Level.HARD, LocalDate.now());

        ToDoList list = ToDoList.getInstance();

        list.addTask(t3);
        list.addTask(t1);
        list.addTask(t2);

        IList orderedById = new OrderedListById(list);

        List<Task> tasksList = orderedById.getTasks();

        int first, second, third;
        first = tasksList.getFirst().getId();
        tasksList.remove(tasksList.getFirst());
        second = tasksList.getFirst().getId();
        tasksList.remove(tasksList.getFirst());
        third = tasksList.getFirst().getId();
        tasksList.remove(tasksList.getFirst());
        assert(first < second && second < third);
    }
}
