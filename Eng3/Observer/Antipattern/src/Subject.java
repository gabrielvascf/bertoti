public class Subject {
    private String state;

    public Subject(String initial) {
        this.state = initial;
    }

    public synchronized String getState() {
        return state;
    }

    public synchronized void setState(String state) {
        this.state = state;
    }
}
