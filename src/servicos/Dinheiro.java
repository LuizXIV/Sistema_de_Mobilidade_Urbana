package servicos;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class Dinheiro implements MetodoPagamento{
	@Override
	public void processarPagamento(double valor, Passageiro passageiro) throws SaldoInsuficienteException, PagamentoRecusadoException {
		try {
			System.out.println("Pagamento em dinheiro de R$ " + valor + " processado com sucesso.");
		} catch (Exception e) {
			throw new PagamentoRecusadoException("Pagamento em dinheiro recusado.");
		}
	}

	@Override
	public String getTipoPagamento() {
		return "Dinheiro";
	}

}
