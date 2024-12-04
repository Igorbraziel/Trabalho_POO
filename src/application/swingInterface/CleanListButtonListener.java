package application.swingInterface;

import entities.IList;
import entities.Task;
import enums.Level;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CleanListButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public CleanListButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        toDoList.cleanList();

        JOptionPane.showMessageDialog(
                null,
                "Tarefas concluidas ou que já passaram do prazo foram removidas",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
