package entidades;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private String nome, cpf, senha, email, telefone;
    private List<Integer> avaliacoes = new ArrayList<>();

    public Usuario() {}
    
    public Usuario(String nome, String cpf, String senha, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.setTelefone(telefone);
    }

    public void adicionarAvaliacao(int estrelas) {
        if (estrelas < 1 || estrelas > 5) throw new IllegalArgumentException("Nota inválida");
        avaliacoes.add(estrelas);
    }


    public double getMediaAvaliacao() {
        double soma = 0;
        for (double nota : avaliacoes) {
            soma += nota;
        }

        int qtdElementos = avaliacoes.size();

        double media = 0;
        if (qtdElementos > 0) {
            media = soma / qtdElementos;
        }

        return media;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

}
