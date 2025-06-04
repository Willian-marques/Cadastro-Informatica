public class Jogos extends Produtos {
    private String genero;
    private String plataforma;
    private String publisher;

    public Jogos(int id, String nome, double preco, int quantidade, String genero, String plataforma, String publisher) {
        super(id, nome, preco, quantidade);
        this.genero = genero;
        this.plataforma = plataforma;
        this.publisher = publisher;
    }

    // Getters e Setters
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    @Override
    public void mostrar() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Quantidade: " + getQuantidade());
        System.out.println("Gênero: " + genero);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Publisher: " + publisher);
    }

    @Override
    public String salvar() {
        // Formato para salvar no arquivo: Tipo;id;nome;preco;quantidade;genero;plataforma;publisher
        return "jogos;" + getId() + ";" + getNome() + ";" + getPreco() + ";" + getQuantidade() + ";" + genero + ";" + plataforma + ";" + publisher;
    }
}
