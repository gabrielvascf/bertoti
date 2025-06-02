package src;

import java.util.ArrayList;
import java.util.List;

class TaskModel {
    private TaskComponent root = new TaskFolder("Todas as Tarefas");
    private List<TaskObserver> observers = new ArrayList<>();
    private SortingStrategy sortingStrategy = new PrioritySortStrategy();

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.taskListChanged();
        }
    }

    public void addTask(TaskComponent task) {
        root.add(task);
        sortTasks();
        notifyObservers();
    }

    public void removeTask(TaskComponent task) {
        root.remove(task);
        notifyObservers();
    }

    public TaskComponent getRoot() {
        return root;
    }

    public void setSortingStrategy(SortingStrategy strategy) {
        this.sortingStrategy = strategy;
        sortTasks();
        notifyObservers();
    }

    public SortingStrategy getSortingStrategy() {
        return sortingStrategy;
    }

    private void sortTasks() {
        sortRecursively(root, sortingStrategy);
    }

    private void sortRecursively(TaskComponent component, SortingStrategy strategy) {
        component.sort(strategy);
        if (component instanceof TaskFolder) {
            for (TaskComponent child : ((TaskFolder) component).getChildren()) {
                sortRecursively(child, strategy);
            }
        }
    }

    public TaskComponent getComponentByIndex(int index) {
        List<TaskComponent> flatList = new ArrayList<>();
        flatten(root, flatList);
        if (index >= 0 && index < flatList.size()) {
            return flatList.get(index);
        }
        return null;
    }

    private void flatten(TaskComponent component, List<TaskComponent> flatList) {
        flatList.add(component);
        if (component instanceof TaskFolder) {
            TaskFolder folder = (TaskFolder) component;
            // Sempre adiciona a pasta, mas só expande os filhos se estiver expandida
            if (folder.isExpanded()) {
                for (TaskComponent child : folder.getChildren()) {
                    flatten(child, flatList);
                }
            }
            // Se não estiver expandida, não adiciona os filhos, mas a pasta ainda está na flatList
        }
    }
}