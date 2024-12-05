package application.swingInterface;

import entities.IList;
import entities.Task;
import exceptions.TaskException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ChangeDeadlineDayButtonListener implements ActionListener {
    private IList toDoList;
    private ToDoListGUI toDoListGUI;

    public ChangeDeadlineDayButtonListener(IList toDoList, ToDoListGUI toDoListGUI){
        this.toDoList = toDoList;
        this.toDoListGUI = toDoListGUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Id
        Integer taskId = null;
        String taskIdString = JOptionPane.showInputDialog(
                null,
                "Digite o número do Id da tarefa a ser trocado o prazo final:",
                "Mudar Prazo Final",
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


        Task taskToChangeDeadlineDay = toDoList.getTask(taskId);
        if(taskToChangeDeadlineDay == null){
            try {
                throw new TaskException("O Id informado não corresponde a nenhuma tarefa");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }


        String[] choices = {"Adicionar", "Remover"};

        int option = JOptionPane.showOptionDialog(
                null,
                "Deseja adicionar ou remover dias ao prazo final?",
                "Opções", 0,
                JOptionPane.QUESTION_MESSAGE, null,
                choices, choices[0]
        );

        Integer plusDays = null;
        Integer minusDays = null;

        if(option == 0){
            String taskPlusDaysString = JOptionPane.showInputDialog(
                    null,
                    "Digite o número de dias para adicionar ao prazo final",
                    "Adicionar dias",
                    JOptionPane.QUESTION_MESSAGE
            );

            if(taskPlusDaysString == null){
                try {
                    throw new TaskException("O número de dias precisa ser informado");
                } catch(TaskException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }

            try {
                plusDays = Integer.parseInt(taskPlusDaysString);
            } catch(NumberFormatException numberFormatException){
                try {
                    throw new TaskException("O número de dias informado precisa ser inteiro");
                } catch(TaskException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } else if (option == 1){
            String taskMinusDaysString = JOptionPane.showInputDialog(
                    null,
                    "Digite o número de dias para remover do prazo final",
                    "Remover dias",
                    JOptionPane.QUESTION_MESSAGE
            );

            if(taskMinusDaysString == null){
                try {
                    throw new TaskException("O número de dias precisa ser informado");
                } catch(TaskException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }

            try {
                minusDays = Integer.parseInt(taskMinusDaysString);
            } catch(NumberFormatException numberFormatException){
                try {
                    throw new TaskException("O número de dias informado precisa ser inteiro");
                } catch(TaskException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } else {
            try {
                throw new TaskException("A opção precisa ser escolhida para mudar o prazo final");
            } catch(TaskException e){
                System.out.println(e.getMessage());
                return;
            }
        }

        if(plusDays != null){
            toDoList.changeDeadlineDay(taskToChangeDeadlineDay, taskToChangeDeadlineDay.getDeadlineDay().plusDays(plusDays));
        }

        if(minusDays != null){
            toDoList.changeDeadlineDay(taskToChangeDeadlineDay, taskToChangeDeadlineDay.getDeadlineDay().minusDays(minusDays));
        }

        JOptionPane.showMessageDialog(
                null,
                "prazo final alterado com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        toDoListGUI.showList();
    }
}
