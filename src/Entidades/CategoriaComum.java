package entidades;

public class CategoriaComum implements Categoria {
    private static double tarifaBase = 5.0;
    private static double precoKm = 1.0;

    @Override
    public double calcularPreco(double distanciaKm) {
        return tarifaBase + (precoKm * distanciaKm);
    }

    @Override
    public String getNome() {
        return "Comum";
    }   
}
