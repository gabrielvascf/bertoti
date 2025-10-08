public class Client {
    public static void main(String[] args) {
        CompositeNode root = new CompositeNode("Root");

        // Adiciona folhas e subcomposites
        root.add(new Leaf("Leaf A"));
        CompositeNode sub = new CompositeNode("Subtree 1");
        sub.add(new Leaf("Leaf 1.1"));
        root.add(sub);

        // Percorre os filhos sem uma interface comum: usa instanceof e casts (antipattern)
        traverseAndOperate(root, "");
    }

    private static void traverseAndOperate(Object node, String indent) {
        if (node instanceof Leaf) {
            // cast e chamada específica
            ((Leaf) node).doLeafOperation();
        } else if (node instanceof CompositeNode) {
            CompositeNode comp = (CompositeNode) node;
            System.out.println(indent + "+ Composite: " + comp.getName());
            for (Object child : comp.getChildren()) {
                // o cliente precisa saber quais tipos podem aparecer
                traverseAndOperate(child, indent + "  ");
            }
        } else {
            System.out.println(indent + "? Tipo desconhecido: " + node.getClass());
        }
    }
}
