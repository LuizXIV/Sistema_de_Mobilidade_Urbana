package entidades;

public class Veiculo {
    private String placa,
            modelo,
            cor;
    private int ano;
    private Categoria categoria;

    public Veiculo() {}
    
    public Veiculo(String placa, String modelo, String cor, int ano, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.categoria = categoria;
    }

    public String getCor() {return cor;}
    public void setCor(String cor) {this.cor = cor;}
    public int getAno() {return ano;}
    public void setAno(int ano) {this.ano = ano;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}
    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

}


