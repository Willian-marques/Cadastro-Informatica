public class Administrador implements Autenticavel {
    private String usuario;
    private String senha;

    //Construtor da classe Administrador
    //Usado para quando é criado um novo Adm
    public Administrador(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    //Metodo exigido pela interface
    //Usado para comparar o login e senha
    @Override
    public boolean autenticar(String usuario, String senha) {
        if (this.usuario.equals(usuario) && this.senha.equals(senha)) {
            System.out.println("Administrador autenticado!");
            return true;
        } else {
            System.out.println("Usuário ou senha incorretos.");
            return false;
        }
}

}