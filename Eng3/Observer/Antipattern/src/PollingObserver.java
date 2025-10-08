/**
 * Antipattern: observar usando polling ativo em vez de notificações.
 * Polling consome CPU e cria latência; o padrão Observer usa notificações push.
 */
public class PollingObserver implements Runnable {
    private final Subject subject;
    private final int iterations;

    public PollingObserver(Subject subject, int iterations) {
        this.subject = subject;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        String last = subject.getState();
        for (int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(200); // espera curta
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            String cur = subject.getState();
            if (!cur.equals(last)) {
                System.out.println("PollingObserver: detectou mudança de '" + last + "' para '" + cur + "'");
                last = cur;
            } else {
                System.out.println("PollingObserver: sem mudança (poll)");
            }
        }
        System.out.println("PollingObserver: término do polling (antipattern)");
    }
}
