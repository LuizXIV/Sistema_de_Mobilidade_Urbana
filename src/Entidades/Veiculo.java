package entidades;

public class Veiculo {
    private String placa,
            modelo,
            cor;
    private int ano;
    private Categoria categoria;

    public Veiculo(String placa, String modelo, String cor, int ano, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.categoria = categoria;
    }
}




