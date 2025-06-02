
public class Informatica extends Produtos{
    private String marca;
    private String modelo;
    
    public Informatica(String nome, double preco, int quantidade, String marca, String modelo) {
        super(nome, preco, quantidade);
        this.marca = marca;
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void mostrar(){
        super.mostrar();
        System.out.println("== Informatica ==");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
    }
}