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

        Task t1 = new Task("Lavar o Banheiro", Level.EASY, LocalDate.now().plusDays(20));
        Task t2 = new Task("Ir na Academia", Level.MEDIUM, LocalDate.now().plusDays(21));
        Task t3 = new Task("Abastecer o carro", Level.HARD, LocalDate.now().plusDays(22));

        IList orderedByName = new OrderedListByName(myList);
        IList orderedById = new OrderedListById(orderedByName);

        orderedByName.addTask(t1);
        orderedByName.addTask(t2);
        orderedByName.addTask(t3);

        orderedById.showList();
        myList.showList();

        sc.close();
    }

}
