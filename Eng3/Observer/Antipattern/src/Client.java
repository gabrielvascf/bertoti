public class Client {
    public static void main(String[] args) throws Exception {
        Subject subject = new Subject("initial");

        // Polling observer fará poucas iterações e terminará (para demo)
        Thread polling = new Thread(new PollingObserver(subject, 10));
        polling.start();

        // Simula mudanças no estado ao longo do tempo
        Thread.sleep(500);
        subject.setState("first-change");
        Thread.sleep(600);
        subject.setState("second-change");

        // espera terminar
        polling.join();
        System.out.println("Client: fim da demo do antipattern Observer (polling)");
    }
}
