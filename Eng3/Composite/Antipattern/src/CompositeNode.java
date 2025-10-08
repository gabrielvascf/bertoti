import java.util.ArrayList;
import java.util.List;

/**
 * Antipattern: não usar uma interface comum. Filhos são armazenados como Object
 * e o cliente precisa usar instanceof/casts para operar — código frágil.
 */
public class CompositeNode {
    private final String name;
    private final List<Object> children = new ArrayList<>();

    public CompositeNode(String name) {
        this.name = name;
    }

    public void add(Object o) {
        children.add(o);
    }

    public void remove(Object o) {
        children.remove(o);
    }

    public List<Object> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }
}
