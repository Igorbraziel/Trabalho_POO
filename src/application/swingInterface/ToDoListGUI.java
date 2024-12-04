package application.swingInterface;

import javax.swing.*;
import java.awt.*;

import entities.*;

public class ToDoListGUI {
    private ListFrame listFrame;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JTextField taskField;
    private IList toDoList;

    public ToDoListGUI() {
        // Inicializar a lista de tarefas personalizada
        toDoList = ToDoList.getInstance();

        // Criar a janela principal
        listFrame = new ListFrame("Lista de Tarefas");

        // Modelo da lista
        model = new DefaultListModel<>();
        list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);

        // Painel inferior para funcionalidades tarefas
        JPanel bottomPanel = new JPanel(new GridLayout(2, 4, 8, 8));

        // Botões de funcionalidades
        JButton addButton = new JButton("Adicionar Tarefa");
        JButton removeButton = new JButton("Remover Tarefa");
        JButton doButton = new JButton("Fazer Tarefa");
        JButton changeDeadlineDayButton = new JButton("Mudar Prazo Final");
        JButton changeDifficultyButton = new JButton("Mudar Dificuldade");
        JButton cleanListButton = new JButton("Limpar Tarefas Concluidas");
        JButton decoratorButton = new JButton("Decorar Lista");

        // Adicionando os botões ao painel inferior
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(doButton);
        bottomPanel.add(changeDeadlineDayButton);
        bottomPanel.add(changeDifficultyButton);
        bottomPanel.add(cleanListButton);
        bottomPanel.add(decoratorButton);

        // Adicionar componentes à janela
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Ação para adicionar tarefas
        addButton.addActionListener(new AddButtonListener(toDoList, this));

        // Ação para remover tarefas
        removeButton.addActionListener(new RemoveButtonListener(toDoList, this));

        // Ação para fazer tarefas
        doButton.addActionListener(new DoButtonListener(toDoList, this));

        //Ação para mudar o prazo final das tarefas
        changeDeadlineDayButton.addActionListener(new ChangeDeadlineDayButtonListener(toDoList, this));

        //Ação para mudar a dificuldade das tarefas
        changeDifficultyButton.addActionListener(new ChangeDifficultyButtonListener(toDoList, this));
    }

    public static void showError(Exception exception){
        String exceptionMessage = "Erro:" + exception.getMessage();
        JOptionPane.showMessageDialog(
                null,
                exceptionMessage,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Atualizar o modelo da lista com os dados do ToDoList
    public void showList() {
        model.clear();
        for (Task tarefa : toDoList.getTasks()) {
            model.addElement(tarefa.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListGUI());
    }
}

