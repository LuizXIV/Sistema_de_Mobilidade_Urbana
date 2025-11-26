package servicos;

public class Pix implements MetodoPagamento{

    private String chavePix;

    public Pix(String chavePix) {
        this.setChavePix(chavePix);
    }

    @Override
    public boolean processarPagamento(double valor) {
        return true;
    }

    public String getChavePix() {return chavePix;}
    public void setChavePix(String chavePix) {this.chavePix = chavePix;}

}