package application.swingInterface;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Locale;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import entities.*;
import enums.*;
import exceptions.*;
import threads.ReadToDoList;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        IList toDoList = ToDoList.getInstance(); // inicializando a minha lista de acordo com os dados já salvos
        File outputFile = new File("listContent.csv");

        ReadToDoList readToDoList = new ReadToDoList(toDoList, outputFile);

        try {
            SwingUtilities.invokeLater(() -> new ToDoListGUI(readToDoList));
        } catch (DateException dateException) {
            ToDoListGUI.showError(dateException);
        } catch (IdException idException) {
            ToDoListGUI.showError(idException);
        } catch (TaskException taskException) {
            ToDoListGUI.showError(taskException);
        }
    }
}

