package servicos;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public interface MetodoPagamento {
    public void processarPagamento(double valor, Passageiro passageiro) throws SaldoInsuficienteException, PagamentoRecusadoException;

    public String getTipoPagamento();
}
