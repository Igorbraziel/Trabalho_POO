package application.swingInterface;

import entities.IList;
import entities.Task;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public RemoveButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // implementar ação para remover tarefa
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o número do Id da tarefa a ser removida:",
                "Remover tarefa por Id",
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


        Task removedTask = toDoList.getTask(taskId);
        if(removedTask == null){
            throw new TaskException("O Id informado não corresponde a nenhuma tarefa");
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
