package src;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class TaskController {
    private static TaskModel model;
    private TaskView view;
    private List<TaskComponent> flatList = new ArrayList<>();

    public TaskController() {
        model = new TaskModel();
        view = new TaskView();
        model.addObserver(view);

        setupListeners();
        view.setVisible(true);
    }

    private void setupListeners() {
        view.addAddTaskListener(e -> addNewTask());
        view.addAddFolderListener(e -> addNewFolder());
        view.addRemoveListener(e -> removeSelected());
        view.addToggleCompleteListener(e -> toggleComplete());
        view.addSortChangeListener(e -> changeSortingStrategy());
    }

    private void addNewTask() {
        String description = JOptionPane.showInputDialog(view, "Descrição da tarefa:");
        if (description != null && !description.trim().isEmpty()) {
            String prioridadeStr = JOptionPane.showInputDialog(view, "Prioridade da tarefa (número inteiro):");
            int prioridade = 0;
            try {
                prioridade = Integer.parseInt(prioridadeStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Prioridade inválida. Usando 0 como padrão.");
            }
            Task task = new Task(description, prioridade);
            int index = view.getSelectedIndex();
            TaskComponent parent = null;
            if (index != -1) {
                TaskComponent selected = getComponentByIndex(index);
                if (selected instanceof TaskFolder) {
                    parent = selected;
                }
            }
            if (parent != null) {
                parent.add(task);
                model.setSortingStrategy(model.getSortingStrategy());
            } else {
                model.addTask(task);
            }
        }
    }

    private void addNewFolder() {
        String name = JOptionPane.showInputDialog(view, "Nome da pasta:");
        if (name != null && !name.trim().isEmpty()) {
            TaskFolder folder = new TaskFolder(name);
            model.addTask(folder);
        }
    }

    private void removeSelected() {
        int index = view.getSelectedIndex();
        if (index != -1) {
            TaskComponent toRemove = getComponentByIndex(index);
            if (toRemove != null) {
                model.removeTask(toRemove);
            }
        }
    }

    private void toggleComplete() {
        int index = view.getSelectedIndex();
        if (index != -1) {
            TaskComponent comp = getComponentByIndex(index);
            if (comp instanceof Task) {
                Task task = (Task) comp;
                task.setCompleted(!task.isCompleted());
                model.notifyObservers();
            }
            view.getSortCombo().getSelectedItem();
        }
    }

    private void changeSortingStrategy() {
        String selected = (String) view.getSortCombo().getSelectedItem();
        if ("Prioridade".equals(selected)) {
            model.setSortingStrategy(new PrioritySortStrategy());
        } else if ("Prazo".equals(selected)) {
            model.setSortingStrategy(new DueDateSortStrategy());
        }
    }

    private void buildFlatList(TaskComponent component) {
        flatList.add(component);
        if (component instanceof TaskFolder) {
            for (TaskComponent child : component.getChildren()) {
                buildFlatList(child);
            }
        }
    }

    private TaskComponent getComponentByIndex(int index) {
        flatList.clear();
        buildFlatList(model.getRoot());
        return index < flatList.size() ? flatList.get(index) : null;
    }

    public static TaskModel getModel() {
        return model;
    }
}