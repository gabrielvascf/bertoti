package src;

import java.util.Date;
import java.util.List;

class DueDateSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<TaskComponent> tasks) {
        tasks.sort((t1, t2) -> {
            Date d1 = t1 instanceof Task ? ((Task) t1).getDueDate() : new Date(0);
            Date d2 = t2 instanceof Task ? ((Task) t2).getDueDate() : new Date(0);
            return d1.compareTo(d2);
        });
    }
}