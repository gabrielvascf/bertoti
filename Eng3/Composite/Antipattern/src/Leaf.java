public class Leaf {
    private final String name;

    public Leaf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void doLeafOperation() {
        System.out.println("- Leaf: " + name);
    }
}
