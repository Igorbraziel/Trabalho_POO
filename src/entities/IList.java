package entities;

import enums.Level;

import java.time.LocalDate;
import java.util.List;

public interface IList {
    void addTask(Task task);
    void removeTask(Task task);
    void updateTask(Task currentTask, Task newTask);
    Task getTask(int index);
    List<Task> getTasks();
    void showList();
    int size();

    void finishTask(Task task);
    void cleanList();
    void doTask(Task task, Integer percentageDone);
    void changeDeadlineDay(Task task, LocalDate deadlineDay);
    void changeDifficultyLevel(Task task, Level difficultyLevel);
}
