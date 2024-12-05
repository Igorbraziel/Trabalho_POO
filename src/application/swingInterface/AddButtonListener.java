package application.swingInterface;

import javax.swing.*;
import java.awt.event.*;

import entities.IList;
import entities.Task;
import enums.Level;

import exceptions.TaskException;

import java.time.LocalDate;

public class AddButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public AddButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        String taskName = JOptionPane.showInputDialog(
                null,
                "Digite o Nome da Tarefa:",
                "Nome da Tarefa",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskName == null || taskName.equals("")){
            try {
                throw new TaskException("O Nome da Tarefa deve ser informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(taskName.contains(",")){
            try {
                throw new TaskException("Nome inválido para uma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        String[] difficultyLevelOptions = {"FÁCIL", "MÉDIO", "DIFÍCIL"};

        int difficultyLevelChoice = JOptionPane.showOptionDialog(
                null,
                "Qual o Nível de Dificuldade da Tarefa?",
                "Dificuldade da Tarefa", 0,
                JOptionPane.QUESTION_MESSAGE, null,
                difficultyLevelOptions, difficultyLevelOptions[0]
        );

        Level taskDifficultyLevel;
        if(difficultyLevelChoice == 0) {
            taskDifficultyLevel = Level.EASY;
        } else if(difficultyLevelChoice == 1) {
            taskDifficultyLevel = Level.MEDIUM;
        } else if(difficultyLevelChoice == 2) {
            taskDifficultyLevel = Level.HARD;
        } else {
            try {
                throw new TaskException("O Nível de Dificuldade da Tarefa precisa ser escolhido");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        LocalDate taskDeadlineDay;

        String deadlineDays = JOptionPane.showInputDialog(
                null,
                "Digite o Número inteiro de Dias para fazer a Tarefa:",
                "Dias",
                JOptionPane.INFORMATION_MESSAGE
        );

        Integer days;

        try {
             days = Integer.parseInt(deadlineDays);
        } catch (NumberFormatException numberFormatException){
            try {
                throw new TaskException("O Número de Dias informado deve ser um INTEIRO");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(days < 0) {
            try {
                throw new TaskException("O Número de Dias informado não pode ser NEGATIVO");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        taskDeadlineDay = LocalDate.now().plusDays(days);

        toDoList.addTask(new Task(taskName, taskDifficultyLevel, taskDeadlineDay));

        JOptionPane.showMessageDialog(
                null,
                "Tarefa Adicionada com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
