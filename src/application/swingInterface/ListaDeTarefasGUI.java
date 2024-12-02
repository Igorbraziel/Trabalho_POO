package application.swingInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import entities.Task;
import entities.ToDoList;
import enums.Level;

public class ListaDeTarefasGUI {
    private JFrame frame;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JTextField taskField;
    private ToDoList toDoList;

    public ListaDeTarefasGUI() {
        // Inicializar a lista de tarefas personalizada
        toDoList = ToDoList.getInstance();

        // Criar a janela principal
        frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Modelo da lista
        model = new DefaultListModel<>();
        list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);

        // Painel superior para adicionar tarefas
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        taskField = new JTextField();
        JButton addButton = new JButton("Adicionar");
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Painel inferior para remover tarefas
        JPanel bottomPanel = new JPanel();
        JButton removeButton = new JButton("Remover Selecionado");
        bottomPanel.add(removeButton);

        // Adicionar componentes à janela
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Ação para adicionar tarefas
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task tarefa = new Task("Tarefa", Level.EASY, LocalDate.now());

                toDoList.addTask(tarefa);
                atualizarLista(); // Atualizar a exibição
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
                    atualizarLista(); // Atualizar a exibição
                }
            }
        });

        // Exibir a janela
        frame.setVisible(true);
    }

    // Atualizar o modelo da lista com os dados do ToDoList
    private void atualizarLista() {
        model.clear();
        for (Task tarefa : toDoList.getTasks()) {
            model.addElement(tarefa.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaDeTarefasGUI());
    }
}

