package servicos;

public class Dinheiro implements MetodoPagamento{
    private int valorRecebido;

    public Dinheiro(int valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valorRecebido >= valor) {
            return true;
        } else {
            return false;
        }
    }

}