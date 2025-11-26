package entidades;

public class Categoria {

    private double tarifaBase, precoPorKm;


    public Categoria(double tarifaBase, double precoPorKm) {
        this.tarifaBase = tarifaBase;
        this.precoPorKm = precoPorKm;
    }


    public double getTarifaBase() {return tarifaBase;}

    public double getPrecoPorKm() {return precoPorKm;}



    public static final Categoria comum = new Categoria(5.0, 1.0);
    public static final Categoria luxo = new Categoria(9.0, 2.2);


}