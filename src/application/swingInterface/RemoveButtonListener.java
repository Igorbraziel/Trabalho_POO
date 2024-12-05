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
        // implementar ação para remover tarefa
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o número do Id da tarefa a ser removida:",
                "Remover tarefa por Id",
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
                throw new TaskException("O Número do Id Informado Precisa ser Inteiro");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }


        Task removedTask = toDoList.getTask(taskId);
        if(removedTask == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        toDoList.removeTask(removedTask);

        JOptionPane.showMessageDialog(
                null,
                "Tarefa Removida com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
