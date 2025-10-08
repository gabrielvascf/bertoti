Antipattern do Composite

Este exemplo mostra uma implementação ruim do Composite onde não existe uma interface
comum. Os filhos são armazenados como `Object` e o cliente precisa usar
`instanceof` e casts para operar na árvore — isso é frágil e difícil de manter.

Arquivos em `Composite/Antipattern/src`:
- `Leaf.java` — nó folha com método específico `doLeafOperation()`.
- `CompositeNode.java` — nó composto que armazena filhos como `Object`.
- `Client.java` — percorre a árvore usando `instanceof` e casts.

Como compilar e executar:

```bash
cd Composite/Antipattern
javac -d bin src/*.java
java -cp bin Client
```

Saída esperada:

+ Composite: Root
  - Leaf: Leaf A
  + Composite: Subtree 1
    - Leaf: Leaf 1.1
