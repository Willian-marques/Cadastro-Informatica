import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n====Menu====");
        System.out.println("1.Cadastrar Produto");
        System.out.println("2.Listar Produtos");
        System.out.println("3.Listar produtos especificos");
        System.out.println("4.Excluir/Alterar Produtos");
        System.out.println("5.Cadastro admin");
        System.out.println("6.Sair");

    }

    public static void cadastrarProduto(ArrayList<Produtos> lista) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("\n==Cadastrar Produto==");
        System.out.println("1.Cadastrar Produto informatica");
        System.out.println("2.Cadastrar Jogos");

        System.out.print("Digite sua opção: ");
        int op2 = scanner.nextInt();

        switch (op2) {
            case 1:
                System.out.println("\n===Cadastro produto informatica===");

                System.out.print("Informe o nome: ");
                scanner.nextLine();
                String nome = scanner.nextLine();
                
                
                System.out.print("Informe o preço em R$: ");
                double preco = scanner.nextDouble();

                scanner.nextLine();

                System.out.print("Informe a quantidade: ");
                int quantidade = scanner.nextInt();  

                scanner.nextLine();
                
                System.out.print("Informe a marca: ");
                String marca = scanner.nextLine();

                System.out.print("Informe o modelo: ");
                String modelo = scanner.nextLine();

                Informatica info = new Informatica(nome, preco, quantidade, marca, modelo);
                lista.add(info);

                break;

            case 2:
                System.out.println("\n===Cadastro Jogos===");
                
                
                System.out.print("Informe o nome: ");
                scanner.nextLine();
                nome = scanner.nextLine();

                

                System.out.print("Informe o preço em R$: ");
                preco = scanner.nextDouble();

                scanner.nextLine();

                System.out.print("Informe a quantidade: ");
                quantidade = scanner.nextInt();

                scanner.nextLine();

                System.out.print("Informe o genero: ");
                String genero = scanner.nextLine();
                

                System.out.print("Informe a plataforma: ");
                String plataforma = scanner.nextLine();


                System.out.print("Informe a Publisher: ");
                String publisher = scanner.nextLine();

                Jogos jogo = new Jogos(nome, preco, quantidade, genero, plataforma, publisher);
                lista.add(jogo);

                break;

            default:
                System.out.println("Digite uma opção valida.");
                break;
        }
    }

    public static void listarProdutos(ArrayList<Produtos> listaProdutos) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n====Menu====");
        System.out.println("1. Listar todos");
        System.out.println("2. Listar apenas Jogos");
        System.out.println("3. Listar apenas Informática");

        System.out.print("Digite sua opção: ");
        int op = scanner.nextInt();

        switch (op) {
            case 1:
                System.out.println("\n=== Todos os Produtos ===");
                for (Produtos p : listaProdutos) {
                    p.mostrar();
                    System.out.println();
                }
                break;

            case 2:
                System.out.println("\n=== Jogos ===");
                for (Produtos p : listaProdutos) {
                    if (p instanceof Jogos) {
                        p.mostrar();
                        System.out.println();
                    }
                }
                break;

            case 3:
                System.out.println("\n=== Informática ===");
                for (Produtos p : listaProdutos) {
                    if (p instanceof Informatica) {
                        p.mostrar();
                        System.out.println();
                    }
                }
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrador admin = new Administrador("Admin", "1234");
        ArrayList<Produtos> listaProdutos = new ArrayList<>();
            
        
        int op1 = 0;

        while (op1 != 6) {
                menuPrincipal();
                System.out.print("Escolha uma opção: ");
                op1 = scanner.nextInt();
            

            switch (op1) {
                case 1:
                    cadastrarProduto(listaProdutos);

                    break;
                case 2:
                    listarProdutos(listaProdutos);

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                default:
                    break;
            }

        }
    }
}
