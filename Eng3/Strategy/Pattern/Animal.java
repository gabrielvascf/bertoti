import strategy.*;
public class Animal {
    Movimentacao Movimentacao;
    public void setMovimentacao(Movimentacao mov) {
        Movimentacao = mov;
    }
    public void mover() {
        Movimentacao.mover();
    }
}
