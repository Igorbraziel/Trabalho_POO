package application.swingInterface;

import entities.IList;
import entities.Task;
import enums.Level;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class UpdateButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public UpdateButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o Número do Id da Tarefa a ser Atualizada:",
                "Atualizar Tarefa por Id",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskIdString == null){
            try {
                throw new TaskException("O Número de Id Precisa ser Informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            taskId = Integer.parseInt(taskIdString);
        } catch(NumberFormatException numberFormatException){
            try {
                throw new TaskException("O Número do Id Informado Precisa ser INTEIRO");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        Task updatedTask = toDoList.getTask(taskId);

        if(updatedTask == null){
            try {
                throw new TaskException("O Id Informado não Corresponde a Nenhuma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        String taskNewName = JOptionPane.showInputDialog(
                null,
                "Digite o Novo Nome da tarefa:",
                "Nome",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskNewName == null || taskNewName.isEmpty()){
            try {
                throw new TaskException("O Nome da Tarefa Deve ser Informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(taskNewName.contains(",")){
            try {
                throw new TaskException("Nome Inválido para uma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        String[] difficultyLevelOptions = {"FÁCIL", "MÉDIO", "DIFÍCIL"};

        int difficultyLevelChoice = JOptionPane.showOptionDialog(
                null,
                "Qual o Nível de Dificuldade da Tarefa?",
                "Dificuldade", 0,
                JOptionPane.QUESTION_MESSAGE, null,
                difficultyLevelOptions, difficultyLevelOptions[0]
        );

        Level taskNewDifficultyLevel;
        if(difficultyLevelChoice == 0) {
            taskNewDifficultyLevel = Level.EASY;
        } else if(difficultyLevelChoice == 1) {
            taskNewDifficultyLevel = Level.MEDIUM;
        } else if(difficultyLevelChoice == 2) {
            taskNewDifficultyLevel = Level.HARD;
        } else {
            try {
                throw new TaskException("O Nível de Dificuldade da Tarefa Precisa ser Escolhido");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        LocalDate taskNewDeadlineDay;

        String deadlineDays = JOptionPane.showInputDialog(
                null,
                "Digite o Número Inteiro de Dias para Fazer a Tarefa:",
                "Número de Dias",
                JOptionPane.INFORMATION_MESSAGE
        );

        Integer days;

        try {
             days = Integer.parseInt(deadlineDays);
        } catch (NumberFormatException numberFormatException){
            try {
                throw new TaskException("O Número de Dias Informado Deve ser um Inteiro");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(days < 0) {
            try {
                throw new TaskException("O Número de Dias Informado não Pode ser Negativo");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        taskNewDeadlineDay = LocalDate.now().plusDays(days);

        Task newTask = new Task(taskNewName, taskNewDifficultyLevel, taskNewDeadlineDay);

        toDoList.updateTask(updatedTask, newTask);

        JOptionPane.showMessageDialog(
                null,
                "Tarefa Atualizada com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
