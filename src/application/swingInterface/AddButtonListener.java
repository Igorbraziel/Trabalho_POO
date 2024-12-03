package application.swingInterface;

import javax.swing.*;
import java.awt.event.*;

import enums.Level;

import exceptions.TaskException;

import java.time.LocalDate;

public class AddButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // implementar ação para adicionar tarefa
        String taskName = JOptionPane.showInputDialog(
                null,
                "Digite o nome da tarefa:",
                "Nome",
                JOptionPane.QUESTION_MESSAGE
        );

        String[] difficultyLevelOptions = {"FÁCIL", "MÉDIO, DIFÍCIL"};

        int difficultyLevelChoice = JOptionPane.showOptionDialog(
                null,
                "Qual o nível de dificuldade da tarefa?",
                "Dificuldade", 0,
                JOptionPane.QUESTION_MESSAGE, null,
                difficultyLevelOptions, difficultyLevelOptions[0]
        );

        Level taskDifficultyLevel;
        if(difficultyLevelChoice == 1) {
            taskDifficultyLevel = Level.EASY;
        } else if(difficultyLevelChoice == 2) {
            taskDifficultyLevel = Level.MEDIUM;
        } else if(difficultyLevelChoice == 3) {
            taskDifficultyLevel = Level.HARD;
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

        // acabar de concluir, inserir a tarefa na lista
    }
}
