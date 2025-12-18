package servicos;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class Pix implements MetodoPagamento{

	private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento(double valor, Passageiro passageiro)
            throws SaldoInsuficienteException, PagamentoRecusadoException {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new PagamentoRecusadoException("Chave Pix inválida.");
        }
        System.out.println("Pagamento via Pix de R$ " + valor + " processado com sucesso");
    }

    @Override
    public String getTipoPagamento() {
        return "Pix";
    }

    

}
