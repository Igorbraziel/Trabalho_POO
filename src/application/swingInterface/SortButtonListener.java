package application.swingInterface;

import entities.*;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public SortButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        Object[] sortOptions = {"Id", "Nome", "Prazo Final", "Porcentagem Concluida"};
        Object chosenOption = JOptionPane.showInputDialog(
                null,
                "Ordenar por:",
                "Ordenação",
                JOptionPane.INFORMATION_MESSAGE, null,
                sortOptions, sortOptions[0]
        );

        if(chosenOption == null){
            try {
                throw new TaskException("A forma de ordenação deve ser escolhida");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(chosenOption.toString().equals("Id")){
            System.out.println("entrei");
            toDoList = new OrderedListById(toDoList);
        } else if(chosenOption.toString().equals("Nome")){
            System.out.println("entrei");
            toDoList = new OrderedListByName(toDoList);
        } else if(chosenOption.toString().equals("Prazo Final")){
            System.out.println("entrei");
            toDoList = new OrderedListByDeadlineDay(toDoList);
        } else if(chosenOption.toString().equals("Porcentagem Concluida")){
            System.out.println("entrei");
            toDoList = new OrderedListByPercentageDone(toDoList);
        }

        JOptionPane.showMessageDialog(
                null,
                "Ordenação feita com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.setToDoList(toDoList);
        toDoListGUI.showList();
    }
}
