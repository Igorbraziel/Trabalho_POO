package application.swingInterface;

import entities.IList;
import entities.Task;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public DoButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Id
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o Número do Id da Tarefa a ser feita:",
                "Fazer Tarefa",
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


        Task taskToDo = toDoList.getTask(taskId);
        if(taskToDo == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        // Porcentagem
        Integer taskPercentageDone = null;
        String taskPercentageDoneString = JOptionPane.showInputDialog(
                null,
                "Digite o Número em Porcentagem que você Deseja Fazer de sua Tarefa:",
                "Número em Porcentagem",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskPercentageDoneString == null){
            try {
                throw new TaskException("O Número da Porcentagem precisa ser informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            taskPercentageDone = Integer.parseInt(taskPercentageDoneString);
        } catch(NumberFormatException numberFormatException){
            try {
                throw new TaskException("O Número da Porcentagem precisa ser INTEIRO");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(taskPercentageDone < 0) {
            try {
                throw new TaskException("Atualização de Tarefa inválida, a Porcentagem feita deve ser POSITIVA");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        toDoList.doTask(taskToDo, taskPercentageDone);

        JOptionPane.showMessageDialog(
                null,
                "Porcentagem da Tarefa Atualizada com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
