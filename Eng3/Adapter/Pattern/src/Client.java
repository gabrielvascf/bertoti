public class Client {
    public static void main(String[] args) {
        // Cliente espera um Target
        Target target = new Adapter(new Adaptee());
        // Usa a interface Target; internamente o Adapter chama o Adaptee
        target.request();
    }
}
