package application.swingInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import entities.*;
import enums.Level;

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
        JButton finishButton = new JButton("Concluir Tarefa");
        JButton doButton = new JButton("Fazer Tarefa");
        JButton changeDeadlineDayButton = new JButton("Mudar Prazo Final");
        JButton changeDifficultyButton = new JButton("Mudar Dificuldade");
        JButton cleanListButton = new JButton("Limpar Tarefas Concluidas");
        JButton decoratorButton = new JButton("Decorar Lista");

        // Adicionando os botões ao painel inferior
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(finishButton);
        bottomPanel.add(doButton);
        bottomPanel.add(changeDeadlineDayButton);
        bottomPanel.add(changeDifficultyButton);
        bottomPanel.add(cleanListButton);
        bottomPanel.add(decoratorButton);

        // Adicionar componentes à janela
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Ação para adicionar tarefas
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task tarefa = new Task("Tarefa", Level.EASY, LocalDate.now());

                toDoList.addTask(tarefa);
                showList(); // Atualizar a exibição
                taskField.setText("");
            }
        });

        // Ação para remover tarefas
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    toDoList.removeTask(toDoList.getTask(selectedIndex));
                    showList(); // Atualizar a exibição
                }
            }
        });
    }

    // Atualizar o modelo da lista com os dados do ToDoList
    private void showList() {
        model.clear();
        for (Task tarefa : toDoList.getTasks()) {
            model.addElement(tarefa.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListGUI());
    }
}

