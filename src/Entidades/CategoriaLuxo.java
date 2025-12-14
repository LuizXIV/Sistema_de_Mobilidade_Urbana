package entidades;

public class CategoriaLuxo implements Categoria {
    private static double tarifaBase = 9.0;
    private static double precoKm = 2.2;

    @Override
    public double calcularPreco(double distanciaKm) {
        return tarifaBase + (precoKm * distanciaKm);
    }

    @Override
    public String getNome() {
        return "Luxo";
    }
}
