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
                "Digite o número do Id da tarefa a ser feita:",
                "Fazer tarefa",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskIdString == null){
            try {
                throw new TaskException("O número de Id precisa ser informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            taskId = Integer.parseInt(taskIdString);
        } catch(NumberFormatException numberFormatException){
            try {
                throw new TaskException("O número do Id informado precisa ser inteiro");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }


        Task taskToDo = toDoList.getTask(taskId);
        if(taskToDo == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        // Porcentagem
        Integer taskPercentageDone = null;
        String taskPercentageDoneString = JOptionPane.showInputDialog(
                null,
                "Digite o número em porcentagem que você deseja fazer de sua tarefa:",
                "Número em porcentagem",
                JOptionPane.QUESTION_MESSAGE
        );

        if(taskPercentageDoneString == null){
            try {
                throw new TaskException("O número da porcentagem precisa ser informado");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            taskPercentageDone = Integer.parseInt(taskPercentageDoneString);
        } catch(NumberFormatException numberFormatException){
            try {
                throw new TaskException("O número da porcentagem precisa ser inteiro");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(taskPercentageDone < 0) {
            try {
                throw new TaskException("Atualização de Tarefa inválida, a porcentagem feita deve ser positiva");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        toDoList.doTask(taskToDo, taskPercentageDone);

        JOptionPane.showMessageDialog(
                null,
                "Porcentagem da tarefa atualizado com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
