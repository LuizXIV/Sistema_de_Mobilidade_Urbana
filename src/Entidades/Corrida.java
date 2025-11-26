package entidades;
import servicos.MetodoPagamento;


public class Corrida  {
    private String id;
    private Passageiro passageiro;
    private Motorista motorista;
    private StatusCorrida status;
    private double precoFinal;
    private MetodoPagamento metodoPagamento;
    private Categoria categoria;
    private Local partida;
    private Local destino;
    private double distancia;
    private double precoEstimado;

    public Corrida(Local partida, Local destino, Categoria categoria) {
        this.categoria = categoria;
        this.distancia = partida.getDistancia();
        this.partida = partida;
        this.destino = destino;
    }



    public double calcularCorrida() {
        return categoria.getTarifaBase() + categoria.getPrecoPorKm() * distancia;
    }

}