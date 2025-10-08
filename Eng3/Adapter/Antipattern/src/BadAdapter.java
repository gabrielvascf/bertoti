/**
 * Antipattern: adaptar por herança em vez de composição.
 * Isso cria acoplamento indesejado e impede a flexibilidade.
 */
public class BadAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        System.out.println("BadAdapter: usando herança para adaptar (antipattern)");
        // chama diretamente o método específico herdado
        specificRequest();
    }
}
