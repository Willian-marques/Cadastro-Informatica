public class Produtos {

    private static int proximoId = 1;
    //Contador compartilhado para as outras 
    //classes que herdarao a classe produtos
    
    private final int id; 
    //ID é gerado automaticamente

    private String nome;
    private double preco;
    private int quantidade;

    public Produtos(String nome, double preco, int quantidade) {
        this.id = proximoId++; //Cria o Id automatico 
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }   

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void mostrar(){
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Preço: " + preco);
        System.out.println("Quantidade: " + quantidade);

    }
}