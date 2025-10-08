public class Adapter implements Target {
    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // Converte a chamada do cliente para a interface do Adaptee
        System.out.println("Adapter: convertendo request() para specificRequest()");
        adaptee.specificRequest();
    }
}
