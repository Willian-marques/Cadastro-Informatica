import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Variáveis de controle de autenticação
    static boolean estaAutenticadoParaCadastroProduto = false;
    static boolean estaAutenticadoParaEdicaoProduto = false;

    public static void menuPrincipal() {
        System.out.println("\n==== Menu Principal ====");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Excluir/Alterar Produtos");
        System.out.println("4. Cadastro Admin");
        System.out.println("5. Sair");
        System.out.print("Digite sua opção: ");
    }

    public static void limparConsole() {
        for (int i = 0; i < 50; ++i)
            System.out.println();
    }

    public static int gerarNovoId(ArrayList<Produtos> lista) {
        int maxId = 0;
        for (Produtos p : lista) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    public static void cadastrarProduto(ArrayList<Produtos> lista) {
        Scanner scanner = new Scanner(System.in);

        limparConsole();
        System.out.println("\n== Cadastrar Produto ==");
        System.out.println("1. Produto Informática");
        System.out.println("2. Jogos");
        System.out.print("Digite sua opção: ");
        int op2 = scanner.nextInt();
        scanner.nextLine();

        int novoId = gerarNovoId(lista);

        switch (op2) {
            case 1:
                System.out.println("\n== Cadastro Produto Informática ==");
                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                System.out.print("Preço: R$ ");
                double preco = scanner.nextDouble();

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Marca: ");
                String marca = scanner.nextLine();

                System.out.print("Modelo: ");
                String modelo = scanner.nextLine();

                Informatica info = new Informatica(novoId, nome, preco, quantidade, marca, modelo);
                lista.add(info);
                System.out.println("Produto cadastrado com ID " + novoId);
                break;

            case 2:
                System.out.println("\n== Cadastro Jogos ==");
                System.out.print("Nome: ");
                nome = scanner.nextLine();

                System.out.print("Preço: R$ ");
                preco = scanner.nextDouble();

                System.out.print("Quantidade: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Gênero: ");
                String genero = scanner.nextLine();

                System.out.print("Plataforma: ");
                String plataforma = scanner.nextLine();

                System.out.print("Publisher: ");
                String publisher = scanner.nextLine();

                Jogos jogo = new Jogos(novoId, nome, preco, quantidade, genero, plataforma, publisher);
                lista.add(jogo);
                System.out.println("Produto cadastrado com ID " + novoId);
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    public static void listarProdutos(ArrayList<Produtos> listaProdutos) {
        Scanner scanner = new Scanner(System.in);

        limparConsole();
        System.out.println("\n==== Listar Produtos ====");
        System.out.println("1. Listar todos");
        System.out.println("2. Listar apenas Jogos");
        System.out.println("3. Listar apenas Informática");
        System.out.print("Digite sua opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

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
        System.out.print("Pressione Enter para voltar...");
        scanner.nextLine();
    }

    public static void salvarProdutos(ArrayList<Produtos> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.txt"))) {
            for (Produtos p : lista) {
                writer.write(p.salvar());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public static ArrayList<Produtos> carregarProdutos() {
        ArrayList<Produtos> lista = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("produtos.txt"))) {
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                Produtos p = Produtos.fromString(linha);
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            // Arquivo pode não existir ainda, não faz nada
        }
        return lista;
    }

    public static void editarOuExcluirProduto(ArrayList<Produtos> listaProdutos, ArrayList<Administrador> listaAdmins) {
        if (!estaAutenticadoParaEdicaoProduto) {
            if (autenticarAdministrador(listaAdmins)) {
                estaAutenticadoParaEdicaoProduto = true;
            } else {
                System.out.println("Acesso negado. Retornando ao menu...");
                return;
            }
        }

        Scanner scanner = new Scanner(System.in);

        if (listaProdutos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
            return;
        }

        limparConsole();
        System.out.println("\n=== Lista de Produtos ===");
        for (Produtos p : listaProdutos) {
            System.out.println("ID: " + p.getId() + " - " + p.getNome());
        }

        System.out.print("Digite o ID do produto para editar/excluir: ");
        int idEscolhido = scanner.nextInt();
        scanner.nextLine();

        Produtos produtoSelecionado = null;
        for (Produtos p : listaProdutos) {
            if (p.getId() == idEscolhido) {
                produtoSelecionado = p;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("\nProduto selecionado:");
        produtoSelecionado.mostrar();

        System.out.println("\n1. Editar");
        System.out.println("2. Excluir");
        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                if (produtoSelecionado instanceof Informatica) {
                    Informatica info = (Informatica) produtoSelecionado;

                    System.out.print("Novo nome (" + info.getNome() + "): ");
                    String nome = scanner.nextLine();
                    if (!nome.isEmpty()) info.setNome(nome);

                    System.out.print("Novo preço (" + info.getPreco() + "): ");
                    String precoStr = scanner.nextLine();
                    if (!precoStr.isEmpty()) info.setPreco(Double.parseDouble(precoStr));

                    System.out.print("Nova quantidade (" + info.getQuantidade() + "): ");
                    String qtdStr = scanner.nextLine();
                    if (!qtdStr.isEmpty()) info.setQuantidade(Integer.parseInt(qtdStr));

                    System.out.print("Nova marca (" + info.getMarca() + "): ");
                    String marca = scanner.nextLine();
                    if (!marca.isEmpty()) info.setMarca(marca);

                    System.out.print("Novo modelo (" + info.getModelo() + "): ");
                    String modelo = scanner.nextLine();
                    if (!modelo.isEmpty()) info.setModelo(modelo);

                } else if (produtoSelecionado instanceof Jogos) {
                    Jogos jogo = (Jogos) produtoSelecionado;

                    System.out.print("Novo nome (" + jogo.getNome() + "): ");
                    String nome = scanner.nextLine();
                    if (!nome.isEmpty()) jogo.setNome(nome);

                    System.out.print("Novo preço (" + jogo.getPreco() + "): ");
                    String precoStr = scanner.nextLine();
                    if (!precoStr.isEmpty()) jogo.setPreco(Double.parseDouble(precoStr));

                    System.out.print("Nova quantidade (" + jogo.getQuantidade() + "): ");
                    String qtdStr = scanner.nextLine();
                    if (!qtdStr.isEmpty()) jogo.setQuantidade(Integer.parseInt(qtdStr));

                    System.out.print("Novo gênero (" + jogo.getGenero() + "): ");
                    String genero = scanner.nextLine();
                    if (!genero.isEmpty()) jogo.setGenero(genero);

                    System.out.print("Nova plataforma (" + jogo.getPlataforma() + "): ");
                    String plataforma = scanner.nextLine();
                    if (!plataforma.isEmpty()) jogo.setPlataforma(plataforma);

                    System.out.print("Novo publisher (" + jogo.getPublisher() + "): ");
                    String publisher = scanner.nextLine();
                    if (!publisher.isEmpty()) jogo.setPublisher(publisher);
                }
                System.out.println("Produto editado com sucesso!");
                break;

            case 2:
                listaProdutos.remove(produtoSelecionado);
                System.out.println("Produto excluído com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }

        salvarProdutos(listaProdutos);
        System.out.print("Pressione Enter para voltar...");
        scanner.nextLine();
    }

    public static void salvarAdministradores(ArrayList<Administrador> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("admins.txt"))) { // sobrescreve o arquivo
            for (Administrador adm : lista) {
                writer.write(adm.getUsuario() + ";" + adm.getSenha());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar administradores: " + e.getMessage());
        }
    }

    public static ArrayList<Administrador> carregarAdministradores() {
        ArrayList<Administrador> admins = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("admins.txt"))) {
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    admins.add(new Administrador(dados[0], dados[1]));
                }
            }
        } catch (IOException e) {
            // Arquivo pode não existir, ok
        }
        return admins;
    }

    public static void cadastrarAdministrador(ArrayList<Administrador> admins) {
        Scanner scanner = new Scanner(System.in);

        limparConsole();
        System.out.println("== Cadastro de Novo Administrador == \n");

        if (!autenticarAdministrador(admins)) {
            System.out.println("Cadastro cancelado.");
            System.out.print("Pressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        System.out.print("Novo usuário: ");
        String novoUsuario = scanner.nextLine();

        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        // Verificar se usuário já existe
        for (Administrador adm : admins) {
            if (adm.getUsuario().equalsIgnoreCase(novoUsuario)) {
                System.out.println("Usuário já existe!");
                System.out.print("Pressione Enter para voltar...");
                scanner.nextLine();
                return;
            }
        }

        admins.add(new Administrador(novoUsuario, novaSenha));
        salvarAdministradores(admins);

        System.out.println("Administrador cadastrado com sucesso!");
        System.out.print("Pressione Enter para voltar...");
        scanner.nextLine();
    }

    public static boolean autenticarAdministrador(ArrayList<Administrador> admins) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Usuário administrador: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Administrador adm : admins) {
            if (adm.autenticar(usuario, senha)) {
                System.out.println("Autenticado com sucesso!");
                return true;
            }
        }
        System.out.println("Usuário ou senha incorretos.");
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Produtos> listaProdutos = carregarProdutos();
        ArrayList<Administrador> listaAdmins = carregarAdministradores();

        // Se não tiver admin, cria admin padrão
        if (listaAdmins.isEmpty()) {
            listaAdmins.add(new Administrador("admin", "1234"));
            salvarAdministradores(listaAdmins);
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            menuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // autenticar uma vez
                    if (!estaAutenticadoParaCadastroProduto) {
                        if (autenticarAdministrador(listaAdmins)) {
                            estaAutenticadoParaCadastroProduto = true;
                        } else {
                            System.out.println("Acesso negado. Retornando ao menu...");
                            break;
                        }
                    }

                    // loop para cadastrar vários produtos até o usuário desejar parar
                    boolean continuarCadastro = true;
                    while (continuarCadastro) {
                        cadastrarProduto(listaProdutos);
                        salvarProdutos(listaProdutos);

                        System.out.print("Cadastrar outro produto? (S/N): ");
                        String resp = scanner.nextLine().trim().toUpperCase();
                        if (!resp.equals("S")) {
                            continuarCadastro = false;
                        }
                    }

                    // Ao sair do cadastro, desautenticar para pedir senha da próxima vez
                    estaAutenticadoParaCadastroProduto = false;

                    break;

                case 2:
                    listarProdutos(listaProdutos);
                    break;

                case 3:
                    editarOuExcluirProduto(listaProdutos, listaAdmins);
                    break;

                case 4:
                    cadastrarAdministrador(listaAdmins);
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }
}