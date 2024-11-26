package tests;

import entities.*;

import enums.Level;
import org.junit.Test;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ToDoListTest {
    // GoF Decorator methods:
    @Test
    public void testAddTask(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste", Level.HARD, LocalDate.now());
        list.addTask(task);
        Task assertTask = list.getTask(task.getId());
        assertEquals(task, assertTask);
    }

    @Test
    public void testRemoveTask(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste", Level.HARD, LocalDate.now());
        list.addTask(task);
        list.removeTask(task);
        assertNull(list.getTask(task.getId()));
    }

    @Test
    public void testUpdateTask(){
        ToDoList list = ToDoList.getInstance();
        Task currentTask = new Task("Tarefa Teste", Level.HARD, LocalDate.now());
        Task newTask = new Task("Tarefa Teste 2", Level.MEDIUM, LocalDate.now());
        list.addTask(currentTask);
        list.updateTask(currentTask, newTask);
        assertEquals(newTask, list.getTask(newTask.getId()));
    }

    @Test
    public void testGetTask(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste", Level.HARD, LocalDate.now());
        int id = task.getId();
        list.addTask(task);
        assertEquals(task, list.getTask(id));
    }

    @Test
    public void testGetTasks(){
        ToDoList list = ToDoList.getInstance();
        List<Task> tasksList = new ArrayList<>();
        Task task1 = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        Task task2 = new Task("Tarefa Teste 2", Level.EASY, LocalDate.now());

        list.addTask(task1);
        list.addTask(task2);

        tasksList.add(task1);
        tasksList.add(task2);

        assertEquals(tasksList, list.getTasks());
    }

    @Test
    public void testFinishTask(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        list.addTask(task);
        list.finishTask(task);
        assertNull(list.getTask(task.getId()));
    }

    @Test
    public void testCleanList(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        list.addTask(task);
        list.doTask(task, 100);
        list.cleanList();
        assertNull(list.getTask(task.getId()));
    }

    @Test
    public void testDoTask(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        int percentageDone = 50;
        list.doTask(task, percentageDone);
        assertEquals(percentageDone, task.getPercentageDone(), 0.0);
    }

    @Test
    public void testChangeDeadlineDay(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        list.addTask(task);
        LocalDate deadlineDay = LocalDate.parse("2025-07-08");
        list.changeDeadlineDay(task, deadlineDay);
        assertEquals(deadlineDay, task.getDeadlineDay());
    }

    @Test
    public void testChangeDifficultyLevel(){
        ToDoList list = ToDoList.getInstance();
        Task task = new Task("Tarefa Teste 1", Level.HARD, LocalDate.now());
        list.addTask(task);
        Level difficultyLevel = Level.EASY;
        list.changeDifficultyLevel(task, difficultyLevel);
        assertEquals(difficultyLevel, task.getDifficultyLevel());
    }
}

