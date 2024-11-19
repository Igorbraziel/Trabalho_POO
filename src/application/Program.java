package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import entities.*;

import enums.Level;

import exceptions.*;

public class Program {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try {
            IList myList = ToDoList.getInstance();

            Task t1 = new Task("Lavar o Banheiro", Level.EASY, LocalDate.now().plusDays(50));
            Task t2 = new Task("Ir na Academia", Level.MEDIUM, LocalDate.now().plusDays(25));
            Task t3 = new Task("Abastecer o carro", Level.HARD, LocalDate.now().plusDays(51));

            IList orderedByDeadline = new OrderedListByDeadlineDay(myList);

            myList.addTask(t1);
            myList.addTask(t2);
            myList.addTask(t3);

            orderedByDeadline.showList();
        } catch(DateException e){
            System.out.println("Mensagem de erro:" + e.getMessage());
        } catch(IdException e){
            System.out.println("Mensagem de erro:" + e.getMessage());
        } catch(TaskException e){
            System.out.println("Mensagem de erro:" + e.getMessage());
        }

        sc.close();
    }

}
