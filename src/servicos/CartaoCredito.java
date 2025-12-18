package servicos;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class CartaoCredito implements MetodoPagamento{
    private String numeroCartao, bandeira, validade, cvv;

    public CartaoCredito(String numeroCartao, String bandeira, String cvv, String validade) {
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
        this.cvv = cvv;
        this.validade = validade;

    }

    @Override
    public void processarPagamento(double valor, Passageiro passageiro)
            throws SaldoInsuficienteException, PagamentoRecusadoException {
        if (numeroCartao == null || numeroCartao.isEmpty() || cvv == null || cvv.isEmpty() || bandeira == null || bandeira.isEmpty() || validade == null || validade.isEmpty()) {
            throw new PagamentoRecusadoException("Dados do cartão inválidos.");
        }
        System.out.println("Pagamento via Cartão de Crédito de R$ " + valor + " processado com sucesso");
    }

    @Override
    public String getTipoPagamento() {
        return "Cartão de Crédito";
    }

}
