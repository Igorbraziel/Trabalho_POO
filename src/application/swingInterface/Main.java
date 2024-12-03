package application.swingInterface;

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

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        File outputFile = new File("listContent.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(outputFile))){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
                ToDoList toDoList = ToDoList.getInstance(); // inicializando a minha lista de acordo com os dados já salvos
                String line;
                while(true){ // USAR THREADS AQUI
                    line = br.readLine();
                    if(line == null) break;

                    String[] fields = line.split(",");

                    String taskName = fields[1];
                    Level taskDifficultyLevel = Level.valueOf(fields[2]);
                    LocalDate taskDeadlineDay = LocalDate.parse(fields[3], Task.dateTimeFormatter);
                    Status taskStatus = Status.valueOf(fields[4]);
                    Integer taskPercentageDone = Integer.valueOf(fields[5]);

                    Task task = new Task(taskName, taskDifficultyLevel, taskDeadlineDay);
                    task.setProgressingStatus(taskStatus);
                    task.setPercentageDone(taskPercentageDone);

                    toDoList.addTask(task);
                }

                SwingUtilities.invokeLater(() -> new ToDoListGUI()); // iniciando a interface
            } catch(IOException writeException){
                ToDoListGUI.showError(writeException);
            } catch(DateException dateException){
                ToDoListGUI.showError(dateException);
            } catch(IdException idException){
                ToDoListGUI.showError(idException);
            } catch(TaskException taskException){
                ToDoListGUI.showError(taskException);
            }
        } catch (IOException readException){
            ToDoListGUI.showError(readException);
        }
    }
}