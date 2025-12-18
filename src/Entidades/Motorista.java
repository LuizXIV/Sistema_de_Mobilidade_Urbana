package entidades;

import excecoes.MotoristaInvalidoException;

public class Motorista extends Usuario {

    private String cnh;
    private StatusMotorista status;
    private Veiculo veiculo;

    public Motorista() {}

    public Motorista(String nome, String cpf, String senha, String email, String telefone, String cnh, Veiculo veiculo) {
        super(nome, cpf, senha, email, telefone);
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.status = StatusMotorista.OFFLINE;
    }

    public void ficarOnline() throws MotoristaInvalidoException {
        if (veiculo == null) {
            throw new MotoristaInvalidoException("Motorista precisa cadastrar um veículo antes de ficar online.");
        } else {
            this.status = StatusMotorista.ONLINE;
            System.out.println("Motorista " + getNome() + " está ONLINE.");
        }
    }

    public void ficarOffline() {
        this.status = StatusMotorista.OFFLINE;
    }

    public boolean aceitarCorrida(Categoria categoria) {
        if (this.status != StatusMotorista.ONLINE) {
            return false;
        }

        System.out.println("Motorista " + getNome() + " aceitou a solicitação.");
        return true;
    }

    public boolean rejeitarCorrida() {
        System.out.println("Motorista " + getNome() + " rejeitou a solicitação.");
        return true;
    }

    public void avaliarPassageiro(Passageiro passageiro, int estrelas) {
        passageiro.adicionarAvaliacao(estrelas);
    }

    public StatusMotorista getStatus() {return status;}
    public void setVeiculo(Veiculo veiculo) {this.veiculo = veiculo;}
    public Veiculo getVeiculo() {return veiculo;}
    public void setStatus(StatusMotorista status) {this.status = status;}
    public String getCnh() {return cnh;}
    public void setCnh(String cnh) {this.cnh = cnh;}

}
