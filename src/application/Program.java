package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.*;
import enums.Level;

public class Program {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        IList myList = ToDoList.getInstance();

        Task t1 = new Task("Tarefa 1", Level.EASY, LocalDate.of(2024, 12, 27), );
        Task t2 = new Task("Tarefa 2", Level.MEDIUM, LocalDate.of(2023, 2, 27));
        Task t3 = new Task("Tarefa 3", Level.HARD, LocalDate.of(2022, 4, 27));

        myList.addTask(t1);
        myList.addTask(t2);
        myList.addTask(t3);

        myList.showList();

        sc.close();
    }

}
