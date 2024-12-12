package application.swingInterface;

import entities.IList;
import entities.Task;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButtonListener implements ActionListener{
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public RemoveButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o Número do Id da Tarefa a ser Removida:",
                "Remover Tarefa por Id",
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


        Task removedTask = toDoList.getTask(taskId);
        if(removedTask == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma Tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        toDoList.removeTask(removedTask);

        JOptionPane.showMessageDialog(
                null,
                "Tarefa Removida com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
