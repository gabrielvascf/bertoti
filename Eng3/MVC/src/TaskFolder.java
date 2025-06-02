package src;

import java.util.ArrayList;
import java.util.List;

class TaskFolder extends TaskComponent {
    private String name;
    private List<TaskComponent> children = new ArrayList<>();
    private boolean expanded = true;

    public TaskFolder(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void add(TaskComponent task) {
        children.add(task);
    }

    @Override
    public void remove(TaskComponent task) {
        children.remove(task);
    }

    @Override
    public List<TaskComponent> getChildren() {
        return children;
    }

    @Override
    public String getDescription() {
        return "📁 " + name;
    }

    @Override
    public void sort(SortingStrategy strategy) {
        strategy.sort(children);
        for (TaskComponent child : children) {
            child.sort(strategy);
        }
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}