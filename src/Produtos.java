public abstract class Produtos {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produtos(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // Método abstrato para mostrar detalhes do produto
    public abstract void mostrar();

    // Método abstrato para salvar produto em String (arquivo)
    public abstract String salvar();

    // Método estático para criar Produto a partir da String do arquivo
    public static Produtos fromString(String linha) {
        // Exemplo do formato:
        // Tipo;id;nome;preco;quantidade;outrosCampos...

        String[] parts = linha.split(";");
        if (parts.length < 5) return null;

        String tipo = parts[0];
        int id = Integer.parseInt(parts[1]);
        String nome = parts[2];
        double preco = Double.parseDouble(parts[3]);
        int quantidade = Integer.parseInt(parts[4]);

        if (tipo.equalsIgnoreCase("informatica")) {
            if (parts.length < 7) return null;
            String marca = parts[5];
            String modelo = parts[6];
            return new Informatica(id, nome, preco, quantidade, marca, modelo);

        } else if (tipo.equalsIgnoreCase("jogos")) {
            if (parts.length < 8) return null;
            String genero = parts[5];
            String plataforma = parts[6];
            String publisher = parts[7];
            return new Jogos(id, nome, preco, quantidade, genero, plataforma, publisher);
        }

        return null;
    }
}
