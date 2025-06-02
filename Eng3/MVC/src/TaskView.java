package src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class TaskView extends JFrame implements TaskObserver {
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);
    private JButton addTaskBtn = new JButton("Nova Tarefa");
    private JButton addFolderBtn = new JButton("Nova Pasta");
    private JButton removeBtn = new JButton("Remover");
    private JButton toggleCompleteBtn = new JButton("Concluir/Reabrir");
    private JComboBox<String> sortCombo = new JComboBox<>(new String[] { "Prioridade", "Prazo" });

    public JComboBox<String> getSortCombo() {
        return sortCombo;
    }

    public TaskView() {
        setTitle("Gerenciador de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Painel de controles
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(addTaskBtn);
        controlPanel.add(addFolderBtn);
        controlPanel.add(removeBtn);
        controlPanel.add(toggleCompleteBtn);
        controlPanel.add(new JLabel("Ordenar por:"));
        controlPanel.add(sortCombo);

        // Lista de tarefas
        JScrollPane scrollPane = new JScrollPane(taskList);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        taskList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Só expande/recolhe com duplo clique
                    int index = taskList.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        TaskComponent comp = TaskController.getModel().getComponentByIndex(index);
                        if (comp instanceof TaskFolder) {
                            TaskFolder folder = (TaskFolder) comp;
                            folder.setExpanded(!folder.isExpanded());
                            TaskController.getModel().notifyObservers();
                        }
                    }
                }
            }
        });
    }

    public void addAddTaskListener(ActionListener listener) {
        addTaskBtn.addActionListener(listener);
    }

    public void addAddFolderListener(ActionListener listener) {
        addFolderBtn.addActionListener(listener);
    }

    public void addRemoveListener(ActionListener listener) {
        removeBtn.addActionListener(listener);
    }

    public void addToggleCompleteListener(ActionListener listener) {
        toggleCompleteBtn.addActionListener(listener);
    }

    public void addSortChangeListener(ActionListener listener) {
        sortCombo.addActionListener(listener);
    }

    public int getSelectedIndex() {
        return taskList.getSelectedIndex();
    }

    @Override
    public void taskListChanged() {
        listModel.clear();
        updateTaskList(TaskController.getModel().getRoot(), 0);
    }

    private void updateTaskList(TaskComponent component, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++)
            indent.append("  ");
        String label = indent + component.getDescription();
        if (component instanceof TaskFolder) {
            TaskFolder folder = (TaskFolder) component;
            label += folder.isExpanded() ? " [-]" : " [+]";
        }
        listModel.addElement(label);

        if (component instanceof TaskFolder) {
            TaskFolder folder = (TaskFolder) component;
            if (folder.isExpanded()) {
                for (TaskComponent child : folder.getChildren()) {
                    updateTaskList(child, depth + 1);
                }
            }
        }
    }
}