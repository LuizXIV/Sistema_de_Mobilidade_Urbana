package entidades;
import java.util.List;

import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;
import servicos.MetodoPagamento;


public class Corrida  {
    private Passageiro passageiro;
    private Motorista motorista;
    private StatusCorrida status;
    private double precoFinal;
    private MetodoPagamento metodoPagamento;
    private Categoria categoria;
    private String partida;
    private String destino;
    private double distancia;
    private boolean motoristaAvaliado = false;
    private boolean passageiroAvaliado = false;

    public Corrida() {}
    
    public Corrida(Passageiro passageiro, String partida, String destino, double distancia, Categoria categoria) {
        this.passageiro = passageiro;
        this.categoria = categoria;
        this.distancia = distancia;
        this.partida = partida;
        this.destino = destino;
        this.precoFinal = categoria.calcularPreco(distancia);
        this.status = StatusCorrida.SOLICITADA;
    }

    public void setMetodoPagamento(MetodoPagamento forma) {
        this.metodoPagamento = forma;
    }

    public void atribuirMotorista(List<Motorista> motoristas) throws EstadoInvalidoDaCorridaException {
        if (this.status != StatusCorrida.SOLICITADA) {
            throw new EstadoInvalidoDaCorridaException("Só é possível atribuir motorista a uma corrida SOLICITADA.");
        }

        for (Motorista m : motoristas) {
            if (m.getStatus() != StatusMotorista.ONLINE) {
                continue; 
            }
            boolean aceitou = m.aceitarCorrida(this.categoria);

            if (aceitou) {
                this.motorista = m;
                this.status = StatusCorrida.ACEITA;
                
                m.setStatus(StatusMotorista.EM_CORRIDA); 
                
                System.out.println("Corrida aceita pelo motorista: " + m.getNome());
                return; 
            }
        }

        throw new EstadoInvalidoDaCorridaException("Nenhum motorista disponível (ONLINE e livre) foi encontrado.");
    }

    public void iniciarCorrida() throws EstadoInvalidoDaCorridaException {
        if (this.status != StatusCorrida.ACEITA) {
            throw new EstadoInvalidoDaCorridaException("Só é possível iniciar viagem quando ACEITA");
        }
        this.status = StatusCorrida.EM_ANDAMENTO;
        if (motorista != null){
            try {
                motorista.setStatus(StatusMotorista.EM_CORRIDA);
            } catch (Exception e) {
                System.out.println("Erro ao atualizar status do motorista: " + e.getMessage());
            }
        }
        System.out.println("Corrida iniciada de " + partida + " para " + destino + ".");
    }

    public void finalizarCorrida() throws EstadoInvalidoDaCorridaException {
        if (this.status != StatusCorrida.EM_ANDAMENTO) {
            throw new EstadoInvalidoDaCorridaException("Só é possível finalizar uma corrida que está EM ANDAMENTO.");
        }
        this.status = StatusCorrida.FINALIZADA;
        this.precoFinal = categoria.calcularPreco(distancia);
        if (motorista != null){
            try {
                motorista.setStatus(StatusMotorista.ONLINE);
            } catch (Exception e) {
                System.out.println("Erro ao atualizar status do motorista: " + e.getMessage());
            }
        }
        System.out.println("Corrida finalizada. Preço final: R$" + precoFinal);
    }

    public void cancelar() throws EstadoInvalidoDaCorridaException {
        if (this.status == StatusCorrida.FINALIZADA || this.status == StatusCorrida.EM_ANDAMENTO) {
            throw new EstadoInvalidoDaCorridaException("Não é possível cancelar uma corrida que já foi FINALIZADA ou EM ANDAMENTO.");
        }
        this.status = StatusCorrida.CANCELADA;
        if (motorista != null){
            try {
                motorista.setStatus(StatusMotorista.ONLINE);
            } catch (Exception e) {
                System.out.println("Erro ao atualizar status do motorista: " + e.getMessage());
            }
        }
        System.out.println("Corrida cancelada.");
    }

    public void processarPagamento() throws SaldoInsuficienteException, EstadoInvalidoDaCorridaException, PagamentoRecusadoException {
        if (this.status != StatusCorrida.FINALIZADA) {
            throw new EstadoInvalidoDaCorridaException("Só é possível processar pagamento de uma corrida FINALIZADA.");
        }
        try{
            metodoPagamento.processarPagamento(precoFinal, passageiro);
        } catch (SaldoInsuficienteException | PagamentoRecusadoException e){
            this.status = StatusCorrida.PENDENTE_PAGAMENTO;
            throw e;
        }
        System.out.println("Pagamento de R$" + precoFinal + " processado via " + metodoPagamento.getTipoPagamento() + ".");
    }

    public boolean isMotoristaAvaliado() {
        return motoristaAvaliado;
    }

    public void setMotoristaAvaliado(boolean motoristaAvaliado) {
        this.motoristaAvaliado = motoristaAvaliado;
    }

    public boolean isPassageiroAvaliado() {
        return passageiroAvaliado;
    }

    public void setPassageiroAvaliado(boolean passageiroAvaliado) {
        this.passageiroAvaliado = passageiroAvaliado;
    }



    public Passageiro getPassageiro() { return passageiro; }
    public Motorista getMotorista() { return motorista; }
    public StatusCorrida getStatus() { return status; }
    public Categoria getCategoria() { return categoria; }
    public double getDistanciaKm() { return distancia; }
    public String getOrigem() { return partida; }
    public String getDestino() { return destino; }
    public String getValor() { return String.format("%.2f", precoFinal); }

}
