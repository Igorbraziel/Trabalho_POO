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
                "Ordenar Por:",
                "Ordenação",
                JOptionPane.INFORMATION_MESSAGE, null,
                sortOptions, sortOptions[0]
        );

        if(chosenOption == null){
            try {
                throw new TaskException("A Forma de Ordenação Deve ser Escolhida");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(chosenOption.toString().equals("Id")){
            toDoList = new OrderedListById(toDoList);
        } else if(chosenOption.toString().equals("Nome")){
            toDoList = new OrderedListByName(toDoList);
        } else if(chosenOption.toString().equals("Prazo Final")){
            toDoList = new OrderedListByDeadlineDay(toDoList);
        } else if(chosenOption.toString().equals("Porcentagem Concluida")){
            toDoList = new OrderedListByPercentageDone(toDoList);
        }

        JOptionPane.showMessageDialog(
                null,
                "Ordenação Realizada com Sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.setToDoList(toDoList);
        toDoListGUI.showList();
    }
}
