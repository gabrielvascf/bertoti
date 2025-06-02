package src;

import java.util.List;

class PrioritySortStrategy implements SortingStrategy {
    @Override
    public void sort(List<TaskComponent> tasks) {
        tasks.sort((t1, t2) -> {
            int p1 = t1 instanceof Task ? ((Task) t1).getPriority() : Integer.MIN_VALUE;
            int p2 = t2 instanceof Task ? ((Task) t2).getPriority() : Integer.MIN_VALUE;
            return Integer.compare(p2, p1); // Prioridade maior primeiro
        });
    }
}