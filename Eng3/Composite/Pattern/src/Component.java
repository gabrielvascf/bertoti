public interface Component {
    /**
     * Executa a operação (neste exemplo, imprime a estrutura).
     * @param indent indentação usada para representar a profundidade na árvore
     */
    void operation(String indent);

    default void add(Component c) {
        throw new UnsupportedOperationException("add not supported");
    }

    default void remove(Component c) {
        throw new UnsupportedOperationException("remove not supported");
    }

    default Component getChild(int i) {
        throw new UnsupportedOperationException("getChild not supported");
    }
}
