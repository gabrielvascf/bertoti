package src;
import java.util.List;
// Strategy para ordenação de tarefas
interface SortingStrategy {
    void sort(List<TaskComponent> tasks);
}