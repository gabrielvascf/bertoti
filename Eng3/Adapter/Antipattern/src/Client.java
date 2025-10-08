public class Client {
    public static void main(String[] args) {
        // Cliente espera um Target. Aqui usamos o BadAdapter que adapta por herança.
        Target target = new BadAdapter();
        target.request();
    }
}
