package entidades;

public class Local {
    private String localPartida, localDestino;
    private double distancia;

    public Local(String localPartida, String localDestino, double distancia) {
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.distancia = distancia;

    }

    public String getLocalPartida() {return localPartida;}

    public String getLocalDestino() {return localDestino;}

    public Double getDistancia() {return distancia;}

    public void setLocalPartida(String localPartida) {this.localPartida = localPartida;}

    public void setLocalDestino(String localDestino) {this.localDestino = localDestino;}

    public void setDistancia(double distancia) {this.distancia = distancia;}


}