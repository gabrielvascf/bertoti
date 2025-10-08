public class Client {
    public static void main(String[] args) {
        // Cria a raiz
        CompositeNode root = new CompositeNode("Root");

        // Folhas diretas
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        // Subcomposite 1
        CompositeNode sub1 = new CompositeNode("Subtree 1");
        sub1.add(new Leaf("Leaf 1.1"));
        sub1.add(new Leaf("Leaf 1.2"));

        // Subcomposite 2
        CompositeNode sub2 = new CompositeNode("Subtree 2");
        sub2.add(new Leaf("Leaf 2.1"));

        // Nível mais profundo
        CompositeNode deep = new CompositeNode("Deep Branch");
        deep.add(new Leaf("Leaf D.1"));
        sub2.add(deep);

        // Monta a árvore
        root.add(sub1);
        root.add(sub2);

        // Executa a operação em toda a estrutura
        root.operation("");
    }
}
