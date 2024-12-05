package application.swingInterface;

import java.util.Locale;

import java.io.File;

import entities.*;

import threads.ReadToDoList;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        IList toDoList = ToDoList.getInstance();
        File outputFile = new File("listContent.csv");

        ReadToDoList readToDoList = new ReadToDoList(toDoList, outputFile);

        SwingUtilities.invokeLater(() -> new ToDoListGUI(readToDoList));
    }
}

