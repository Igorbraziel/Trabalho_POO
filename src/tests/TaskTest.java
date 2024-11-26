package tests;

import entities.Task;
import entities.ToDoList;
import enums.Level;
import enums.Status;
import exceptions.DateException;
import exceptions.TaskException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getName() {
        String name = "New Name";
        Task task = new Task(name, Level.EASY, LocalDate.now());
        assertEquals(name, task.getName());
    }

    @Test
    public void setName() {
        String name = "New Name";
        Task task = new Task("current name", Level.EASY, LocalDate.now());
        task.setName(name);
        assertEquals(name, task.getName());
    }

    @Test
    public void getDifficultyLevel() {
        Task task = new Task("Task", Level.EASY, LocalDate.now());
        assertEquals(Level.EASY, task.getDifficultyLevel());
    }

    @Test
    public void setDifficultyLevel() {
        Task task = new Task("Task", Level.EASY, LocalDate.now());
        Level newLevel = Level.HARD;
        task.setDifficultyLevel(newLevel);
        assertEquals(newLevel, task.getDifficultyLevel());
    }

    @Test
    public void getDeadlineDay(){
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);
        assertEquals(deadlineDay, task.getDeadlineDay());
    }

    @Test
    public void setDeadlineDay(){
        Task task = new Task("Task", Level.EASY, LocalDate.parse("2026-07-08"));
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        task.setDeadlineDay(deadlineDay);
        assertEquals(deadlineDay, task.getDeadlineDay());
    }

    @Test
    public void getProgressingStatus() {
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);
        assertEquals(Status.PENDING, task.getProgressingStatus());
    }

    @Test
    public void setProgressingStatus() {
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);
        task.setProgressingStatus(Status.PROGRESSING);
        assertEquals(Status.PROGRESSING, task.getProgressingStatus());
    }

    @Test
    public void getPercentageDone(){
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);
        assertEquals(0, task.getPercentageDone(), 0.0);
    }

    @Test
    public void setPercentageDone(){
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);

        task.setPercentageDone(0);
        assertEquals(0, task.getPercentageDone(), 0.0);

        task.setPercentageDone(50);
        assertEquals(50, task.getPercentageDone(), 0.0);

        task.setPercentageDone(200);
        assertEquals(100, task.getPercentageDone(), 0.0);
    }

    @Test
    public void update(){
        Object obj;
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        Task task = new Task("Task", Level.EASY, deadlineDay);

        obj = 10;
        task.update(obj);
        assertEquals(10, task.getPercentageDone(), 0.0);

        obj = deadlineDay.plusDays(50);
        task.update(obj);
        assertEquals(deadlineDay.plusDays(50), task.getDeadlineDay());

        obj = Level.HARD;
        task.update(obj);
        assertEquals(Level.HARD, task.getDifficultyLevel());
    }
}
