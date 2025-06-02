package src;
import java.util.Date;

public class Task extends TaskComponent {
    private boolean completed;
    private Date dueDate = new Date();
    private int prioridade;

    public Task(String descricao, int prioridade) {
        super(descricao);
        this.prioridade = prioridade;
    }

    @Override
    public String getDescription() {
        String prazo = (dueDate != null) ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(dueDate) : "Sem prazo";
        String status = completed ? "✔️ Concluída" : "⏳ Pendente";
        return "📝 " + super.description + " (Prioridade: " + prioridade + ", Prazo: " + prazo + ", Status: " + status + ")";
    }
 
    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int getPriority() {
        return prioridade;
    }

    public void setPriority(int priority) {
        this.prioridade = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}