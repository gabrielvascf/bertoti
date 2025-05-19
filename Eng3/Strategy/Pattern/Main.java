import strategy.*;

public class Main {
    public static void main(String[] args) {
        var Passaro = new Animal();
        Passaro.setMovimentacao(new Voo());
        Passaro.mover();

        var Cachorro = new Animal();
        Cachorro.setMovimentacao(new Corrida());
        Cachorro.mover();
    }
}
