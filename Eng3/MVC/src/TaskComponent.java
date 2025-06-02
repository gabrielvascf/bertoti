package src;

import java.util.List;

abstract class TaskComponent {
    protected String description;

    public TaskComponent(String description) {
        this.description = description;
    }

    public void add(TaskComponent task) {
        throw new UnsupportedOperationException();
    }

    public void remove(TaskComponent task) {
        throw new UnsupportedOperationException();
    }

    public List<TaskComponent> getChildren() {
        throw new UnsupportedOperationException();
    }
    
    public int getPriority() {
        throw new UnsupportedOperationException();
    }

    public boolean isCompleted() {
        return false;
    }

    public void setCompleted(boolean completed) {
        throw new UnsupportedOperationException();
    }

    public void sort(SortingStrategy strategy) {
        // Implementação padrão para folhas
    }

    public abstract String getDescription();
}