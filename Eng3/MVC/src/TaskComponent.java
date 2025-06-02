package src;

import java.util.List;

public interface TaskComponent {
    void add(TaskComponent task);

    void remove(TaskComponent task);

    List<TaskComponent> getChildren();

    int getPriority();

    boolean isCompleted();

    void setCompleted(boolean completed);

    void sort(SortingStrategy strategy);

    String getDescription();
}
