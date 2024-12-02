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

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        File outputFile = new File("Trabalho_POO/listContent.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(outputFile))){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
                String line = br.readLine();
                ToDoList toDoList = ToDoList.getInstance(); // inicializando a minha lista de acordo com os dados já salvos

                while(true){
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
            } catch(IOException writeException){
                System.out.println("Erro de escrita no arquivo: " + writeException.getMessage());
                return;
            }
        } catch (IOException readException){
            System.out.println("Erro de leitura no arquivo: " + readException.getMessage());
            return;
        }
    }
}
