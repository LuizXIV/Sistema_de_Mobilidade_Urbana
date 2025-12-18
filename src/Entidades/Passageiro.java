package entidades;

import java.util.ArrayList;
import java.util.List;

import excecoes.PassageiroPendenteException;
import servicos.MetodoPagamento;

public class Passageiro extends Usuario{
    private List<MetodoPagamento> metodosPagamento = new ArrayList<>();

    public Passageiro() {}
    
    public Passageiro(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }

    public Corrida solicitarCorrida(Passageiro passageiro, String partida, String destino,double distancia, Categoria categoria) {
        return new Corrida(passageiro, partida, destino, distancia, categoria);
    }

    public void checarPodeSolicitarCorrida() throws PassageiroPendenteException {
        if (metodosPagamento.isEmpty()) {
            throw new PassageiroPendenteException("Passageiro precisa adicionar um método de pagamento antes de solicitar uma corrida.");
        }
    }

    public void adicionarMetodoPagamento(MetodoPagamento metodo) {
        this.metodosPagamento.add(metodo);
    }

    public void avaliarMotorista(Motorista motorista, int estrelas) {
        motorista.adicionarAvaliacao(estrelas);
    }

    public MetodoPagamento getMetodoPagamento(int index) {
        return metodosPagamento.get(index);
    }

    public List<MetodoPagamento> getMetodosPagamento() {
        return metodosPagamento;
    }

}
