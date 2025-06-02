public class Jogos extends Produtos {
    private String genero;
    private String plataforma;
    private String publisher;

    public Jogos(String nome, double preco, int quantidade,
                 String genero, String plataforma, String publisher) {
        super(nome, preco, quantidade); // Chama o construtor da superclasse
        this.genero = genero;
        this.plataforma = plataforma;
        this.publisher = publisher;
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    @Override
    public void mostrar() {
        super.mostrar(); // Exibe dados de Produtos
        System.out.println("== Jogos ==");
        System.out.println("Gênero: " + genero);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Publisher: " + publisher);
    }
}
