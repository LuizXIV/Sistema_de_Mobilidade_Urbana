package servicos;

import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public interface MetodoPagamento {
    public boolean processarPagamento(double valor) throws SaldoInsuficienteException, PagamentoRecusadoException;
}