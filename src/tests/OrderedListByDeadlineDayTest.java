package tests;

import entities.IList;
import entities.OrderedListByDeadlineDay;
import entities.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import entities.ToDoList;
import enums.Level;
import org.junit.Test;
import static org.junit.Assert.*;


// WORKING
public class OrderedListByDeadlineDayTest {
    @Test
    public void getTasks(){
        LocalDate d1 = LocalDate.parse("2026-09-11");
        LocalDate d2 = LocalDate.parse("2027-09-11");
        LocalDate d3 = LocalDate.parse("2025-09-11");
        Task t1 = new Task("Task 1", Level.EASY, d1);
        Task t2= new Task("Task 2", Level.MEDIUM, d3);
        Task t3 = new Task("Task 3", Level.HARD, d2);

        ToDoList list = ToDoList.getInstance();

        list.addTask(t1);
        list.addTask(t2);
        list.addTask(t3);

        IList orderedByDeadlineDay = new OrderedListByDeadlineDay(list);

        List<Task> tasksList = orderedByDeadlineDay.getTasks();

        LocalDate first, second, third;
        first = tasksList.getFirst().getDeadlineDay();
        tasksList.remove(tasksList.getFirst());
        second = tasksList.getFirst().getDeadlineDay();
        tasksList.remove(tasksList.getFirst());
        third = tasksList.getFirst().getDeadlineDay();
        tasksList.remove(tasksList.getFirst());
        assertEquals(d3, first);
        assertEquals(d1, second);
        assertEquals(d2, third);
    }
}
