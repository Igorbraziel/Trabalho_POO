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
                "Digite o nome da tarefa:",
                "Nome",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskName == null || taskName.equals("")){
            throw new TaskException("O nome da tarefa deve ser informado");
        }

        if(taskName.contains(",")){
            throw new TaskException("Nome inválido para uma tarefa");
        }

        String[] difficultyLevelOptions = {"FÁCIL", "MÉDIO", "DIFÍCIL"};

        int difficultyLevelChoice = JOptionPane.showOptionDialog(
                null,
                "Qual o nível de dificuldade da tarefa?",
                "Dificuldade", 0,
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
            throw new TaskException("O nível de dificuldade da tarefa precisa ser escolhido");
        }

        LocalDate taskDeadlineDay;

        String deadlineDays = JOptionPane.showInputDialog(
                null,
                "Digite o número inteiro de dias para fazer a tarefa:",
                "Dias",
                JOptionPane.INFORMATION_MESSAGE
        );

        Integer days;

        try {
             days = Integer.parseInt(deadlineDays);
        } catch (NumberFormatException e){
            throw new TaskException("O Número de dias informado deve ser um inteiro");
        }

        if(days < 0) {
            throw new TaskException("O Número de dias informado não pode ser negativo");
        }

        taskDeadlineDay = LocalDate.now().plusDays(days);

        toDoList.addTask(new Task(taskName, taskDifficultyLevel, taskDeadlineDay));

        JOptionPane.showMessageDialog(
                null,
                "Tarefa adicionada com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
