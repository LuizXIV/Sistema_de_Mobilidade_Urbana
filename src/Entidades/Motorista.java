package entidades;

public class Motorista extends Usuario {

    private int cnh;
    private StatusMotorista status;
    private Veiculo veiculo;

    public Motorista(String nome, String cpf, String senha, String email, String telefone, int cnh, Veiculo veiculo, StatusMotorista status) {
        super(nome, cpf, senha, email, telefone);
        this.setCnh(cnh);
        this.veiculo = veiculo;
        this.status = status;
    }

    public void ficarOnline() {
        this.status = StatusMotorista.ONLINE;
        System.out.println("Motorista " + getNome() + " está ONLINE.");
    }

    public void ficarOffline() {
        this.status = StatusMotorista.OFFLINE;
    }

    public void avaliarPassageiro(Passageiro passageiro, int estrelas) {
        passageiro.adicionarAvaliacao(estrelas);
    }

    public StatusMotorista getStatus() {return status;}
    public Veiculo getVeiculo() {return veiculo;}
    public void setStatus(StatusMotorista status) {this.status = status;}
    public int getCnh() {return cnh;}
    public void setCnh(int cnh) {this.cnh = cnh;}

}