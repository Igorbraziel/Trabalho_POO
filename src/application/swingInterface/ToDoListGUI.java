package application.swingInterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import entities.*;
import threads.ReadToDoList;

public class ToDoListGUI {
    private ListFrame listFrame;
    private DefaultListModel<String> model;
    private JList<String> list;
    private IList toDoList;

    public ToDoListGUI(ReadToDoList readToDoList) {
        toDoList = ToDoList.getInstance();

        // Criando a janela principal
        listFrame = new ListFrame("Lista de Tarefas");
        // Quando a janela principal for fechada os dados serão salvos em um arquivo
        listFrame.addWindowListener(new ListWindowListener(listFrame, toDoList, new File("listContent.csv")));

        // Modelo da lista
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setFont(new Font("SansSerif", Font.PLAIN, 20));
        list.setCellRenderer(new ListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(list);

        // Painel inferior para funcionalidades tarefas
        JPanel bottomPanel = new JPanel(new GridLayout(4, 2, 15, 15));

        // Botões de funcionalidades
        ListButton addButton = new ListButton("Adicionar Tarefa");
        ListButton removeButton = new ListButton("Remover Tarefa");
        ListButton updateButton = new ListButton("Atualizar Tarefa");
        ListButton doButton = new ListButton("Fazer Tarefa");
        ListButton changeDeadlineDayButton = new ListButton("Mudar Prazo Final de Uma Tarefa");
        ListButton changeDifficultyButton = new ListButton("Mudar Dificuldade de Uma Tarefa");
        ListButton cleanListButton = new ListButton("Limpar Tarefas Concluidas");
        ListButton sortButton = new ListButton("Ordenar Lista de Tarefas");

        // Adicionando os botões ao painel inferior
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(doButton);
        bottomPanel.add(changeDeadlineDayButton);
        bottomPanel.add(changeDifficultyButton);
        bottomPanel.add(cleanListButton);
        bottomPanel.add(sortButton);

        // Adicionar componentes à janela
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Ação para adicionar tarefas
        addButton.addActionListener(new AddButtonListener(toDoList, this));

        // Ação para remover tarefas
        removeButton.addActionListener(new RemoveButtonListener(toDoList, this));

        // Ação para atualizar tarefas
        updateButton.addActionListener(new UpdateButtonListener(toDoList, this));

        // Ação para fazer tarefas
        doButton.addActionListener(new DoButtonListener(toDoList, this));

        // Ação para mudar o prazo final das tarefas
        changeDeadlineDayButton.addActionListener(new ChangeDeadlineDayButtonListener(toDoList, this));

        // Ação para mudar a dificuldade das tarefas
        changeDifficultyButton.addActionListener(new ChangeDifficultyButtonListener(toDoList, this));

        // Ação para limpar a lista de tarefas
        cleanListButton.addActionListener(new CleanListButtonListener(toDoList, this));

        // Ação para ordenar a lista de tarefas
        sortButton.addActionListener(new SortButtonListener(toDoList, this));

        readToDoList.setToDoListGUI(this); // Inicializando a interface com os valores já existentes na lista
        readToDoList.start(); // Iniciando a Thread
    }

    public static void showError(Exception exception){
        String exceptionMessage = "Erro: " + exception.getMessage();
        JOptionPane.showMessageDialog(
                null,
                exceptionMessage,
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void showList() {
        model.clear();
        for (Task tarefa : toDoList.getTasks()) {
            model.addElement(tarefa.toString());
        }
    }

    public void setToDoList(IList toDoList){
        this.toDoList = toDoList;
    }
}

