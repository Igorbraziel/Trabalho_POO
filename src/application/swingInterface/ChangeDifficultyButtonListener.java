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
                "Digite o Número do Id da Tarefa a ser trocada a Dificuldade:",
                "Mudar Dificuldade",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskIdString == null){
            try {
                throw new TaskException("O Número de Id precisa ser informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            taskId = Integer.parseInt(taskIdString);
        } catch(NumberFormatException numberFormatException){
            try {
                throw new TaskException("O Número do Id informado precisa ser INTEIRO");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }


        Task taskToChangeDifficulty = toDoList.getTask(taskId);
        if(taskToChangeDifficulty == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }


        String[] choices = {"FÁCIL", "MÉDIO", "DIFÍCIL"};

        int option = JOptionPane.showOptionDialog(
                null,
                "Qual a Nova Dificuldade?",
                "Nova Dificuldade", 0,
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
            try {
                throw new TaskException("A Opção precisa ser escolhida para mudar a Dificuldade");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        toDoList.changeDifficultyLevel(taskToChangeDifficulty, taskLevel);

        JOptionPane.showMessageDialog(
                null,
                "Dificuldade Alterada com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
