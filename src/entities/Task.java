package entities;

import enums.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import exceptions.DateException;

public class Task {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;
    private String name;
    private Level difficultyLevel;
    private LocalDate deadlineDay;
    private Status progressingStatus;

    private static Integer idNumber = 1;

    public Task(String name, Level difficultyLevel, LocalDate deadlineDay){
        if(Duration.between(LocalDate.now().atStartOfDay(), deadlineDay.atStartOfDay()).toDays() < 0){
            throw new DateException("Data inválida para nova tarefa");
        }

        this.id = idNumber;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.deadlineDay = deadlineDay;
        this.progressingStatus = Status.PENDING;

        idNumber += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Level difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public LocalDate getDeadlineDay(){
        return deadlineDay;
    }

    public void setDeadlineDay(LocalDate deadlineDay){
        this.deadlineDay = deadlineDay;
    }

    public Status getProgressingStatus() {
        return progressingStatus;
    }

    public void setProgressingStatus(Status progressingStatus) {
        this.progressingStatus = progressingStatus;
    }

    public Integer getId(){
        return id;
    }

    public String showFormattedDate(){
        return deadlineDay.format(dateTimeFormatter).toString();
    }

    @Override
    public String toString() {
        return "Tarefa - {" +
                "id=" + id +
                ", nome=" + name +
                ", dificuldade=" + difficultyLevel +
                ", prazo final=" + deadlineDay +
                ", status=" + progressingStatus +
                '}';
    }
}
