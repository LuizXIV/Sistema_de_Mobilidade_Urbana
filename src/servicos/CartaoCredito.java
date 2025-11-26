package servicos;

import java.util.Date;

public class CartaoCredito implements MetodoPagamento{
    private String numeroCartao, nome, titular, cvv;
    Date validade;




    public CartaoCredito(String numeroCartao, String nome, String titular, String cvv, Date validade) {
        this.setNumeroCartao(numeroCartao);
        this.nome = nome;
        this.titular = titular;
        this.cvv = cvv;
        this.validade = validade;

    }


    public String setNome() {return nome;}

    public String setTitular() {return titular;}

    public String setCvv() {return cvv;}

    public Date setValidade() {return validade;}


    public void getNome() {
    }
    public void getTitular() {

    }
    public void getCvv() {

    }
    public void getValidade() {

    }



    @Override
    public boolean processarPagamento(double valor) {
        return true;
    }

    public String getNumeroCartao() {return numeroCartao;}
    public void setNumeroCartao(String numeroCartao) {this.numeroCartao = numeroCartao;}