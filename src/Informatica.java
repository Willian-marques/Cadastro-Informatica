public class Informatica extends Produtos {
    private String marca;
    private String modelo;

    public Informatica(int id, String nome, double preco, int quantidade, String marca, String modelo) {
        super(id, nome, preco, quantidade);
        this.marca = marca;
        this.modelo = modelo;
    }

    // Getters e Setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    @Override
    public void mostrar() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Quantidade: " + getQuantidade());
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
    }

    @Override
    public String salvar() {
        // Formato para salvar no arquivo: Tipo;id;nome;preco;quantidade;marca;modelo
        return "informatica;" + getId() + ";" + getNome() + ";" + getPreco() + ";" + getQuantidade() + ";" + marca + ";" + modelo;
    }
}
