package entities;

import enums.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import exceptions.DateException;
import exceptions.TaskException;

import observer.ListObserver;

public class Task implements ListObserver {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;
    private String name;
    private Level difficultyLevel;
    private LocalDate deadlineDay;
    private Status progressingStatus;
    private Integer percentageDone;

    private static Integer idNumber = 1;

    public Task(String name, Level difficultyLevel, LocalDate deadlineDay) {
        if(Duration.between(LocalDate.now().atStartOfDay(), deadlineDay.atStartOfDay()).toDays() < 0){
            try {
                throw new DateException("Data inválida para nova tarefa");
            } catch(DateException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        this.id = idNumber;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.deadlineDay = deadlineDay;

        this.progressingStatus = Status.PENDING;
        this.percentageDone = 0;

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

    public void setDeadlineDay(LocalDate deadlineDay) {
        if(Duration.between(LocalDate.now().atStartOfDay(), deadlineDay.atStartOfDay()).toDays() < 0){
            try {
                throw new DateException("Nova Data inválida para tarefa");
            } catch(DateException e){
                System.out.println(e.getMessage());
                return;
            }
        }
        this.deadlineDay = deadlineDay;
    }

    public Status getProgressingStatus() {
        return progressingStatus;
    }

    public void setProgressingStatus(Status progressingStatus) {
        this.progressingStatus = progressingStatus;
    }

    public Integer getPercentageDone(){
        return percentageDone;
    }

    public void setPercentageDone(Integer percentageDone) {
        if(percentageDone < 0) {
            try {
                throw new TaskException("Atualização de tarefa inválida, a porcentagem feita deve ser positiva");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }
        if(percentageDone == 0){
            setProgressingStatus(Status.PENDING);
        } else if (percentageDone < 100){
            setProgressingStatus(Status.PROGRESSING);
        } else {
            setProgressingStatus(Status.FINISHED);
        }
        if(percentageDone > 100){
            this.percentageDone = 100;
        } else {
            this.percentageDone = percentageDone;
        }
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String showFormattedDate(){
        return deadlineDay.format(dateTimeFormatter);
    }

    // GoF pattern: Observer
    @Override
    public void update(Object obj) {
        if(obj == ToDoList.getInstance()){
            if(this.getProgressingStatus() == Status.FINISHED) {
                ((ToDoList) obj).finishTask(this);
            }
            if(Duration.between(LocalDate.now().atStartOfDay(), deadlineDay.atStartOfDay()).toDays() < 0){
                ((ToDoList) obj).removeTask(this);
            }
        } else if(obj instanceof Integer) {
            Integer percentageDone = (Integer) obj;
            setPercentageDone(percentageDone + getPercentageDone());
        } else if(obj instanceof LocalDate){
            LocalDate deadlineDay = (LocalDate) obj;
            setDeadlineDay(deadlineDay);
        } else if(obj instanceof Level){
            Level difficultyLevel = (Level) obj;
            setDifficultyLevel(difficultyLevel);
        }
    }
    // GoF pattern

    public static Integer getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(Integer id){
        idNumber = id;
    }

    public String showDifficultyLevel(){
        if(difficultyLevel.equals(Level.EASY)){
            return "FÁCIL";
        } else if(difficultyLevel.equals(Level.MEDIUM)){
            return "MÉDIO";
        }
        return "DIFÍCIL";
    }

    public String showProgressingStatus(){
        if(progressingStatus.equals(Status.PENDING)){
            return "PENDENTE";
        } else if(progressingStatus.equals(Status.PROGRESSING)){
            return "PROGREDINDO";
        }
        return "FINALIZADO";
    }

    @Override
    public String toString() {
        return "Tarefa - {" +
                "Id = " + id +
                ", Nome = " + name +
                ", Dificuldade = " + showDifficultyLevel() +
                ", Prazo Final = " + this.showFormattedDate() +
                ", Status = " + showProgressingStatus() +
                ", Porcentagem Feita = " + percentageDone + "%" +
                '}';
    }
}
