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
            throw new TaskException("O Número de Id Precisa ser Informado");
        }

        try {
            taskId = Integer.parseInt(taskIdString);
        } catch(NumberFormatException e){
            throw new TaskException("O Número do Id Informado Precisa ser INTEIRO");
        }

        Task updatedTask = toDoList.getTask(taskId);

        if(updatedTask == null){
            throw new TaskException("O Id Informado não Corresponde a Nenhuma Tarefa");
        }

        String taskNewName = JOptionPane.showInputDialog(
                null,
                "Digite o Novo Nome da tarefa:",
                "Nome",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskNewName == null || taskNewName.isEmpty()){
            throw new TaskException("O Nome da Tarefa Deve ser Informado");
        }

        if(taskNewName.contains(",")){
            throw new TaskException("Nome Inválido para uma Tarefa");
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
            throw new TaskException("O Nível de Dificuldade da Tarefa Precisa ser Escolhido");
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
        } catch (NumberFormatException e){
            throw new TaskException("O Número de Dias Informado Deve ser um Inteiro");
        }

        if(days < 0) {
            throw new TaskException("O Número de Dias Informado não Pode ser Negativo");
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
