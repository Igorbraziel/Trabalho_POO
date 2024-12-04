package application.swingInterface;

import entities.IList;
import entities.Task;
import enums.Level;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeDifficultyButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public ChangeDifficultyButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Id
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o número do Id da tarefa a ser trocada a dificuldade:",
                "Mudar Dificuldade",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskIdString == null){
            throw new TaskException("O número de Id precisa ser informado");
        }

        try {
            taskId = Integer.parseInt(taskIdString);
        } catch(NumberFormatException e){
            throw new TaskException("O número do Id informado precisa ser inteiro");
        }


        Task taskToChangeDifficulty = toDoList.getTask(taskId);
        if(taskToChangeDifficulty == null){
            throw new TaskException("O Id informado não corresponde a nenhuma tarefa");
        }


        String[] choices = {"FÁCIL", "MÉDIO", "DIFÍCIL"};

        int option = JOptionPane.showOptionDialog(
                null,
                "Qual a nova dificuldade?",
                "Dificuldade", 0,
                JOptionPane.QUESTION_MESSAGE, null,
                choices, choices[0]
        );

        Level taskLevel = null;

        if(option == 0){
            taskLevel = Level.EASY;
        } else if (option == 1){
            taskLevel = Level.MEDIUM;
        } else if (option == 2){
            taskLevel = Level.HARD;
        } else {
            throw new TaskException("A opção precisa ser escolhida para mudar a dificuldade");
        }

        toDoList.changeDifficultyLevel(taskToChangeDifficulty, taskLevel);

        JOptionPane.showMessageDialog(
                null,
                "Dificuldade alterada com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
