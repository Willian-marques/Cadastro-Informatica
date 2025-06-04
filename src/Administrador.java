public class Administrador {
    private String usuario;
    private String senha;

    public Administrador(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }

    public boolean autenticar(String user, String pass) {
        return usuario.equals(user) && senha.equals(pass);
    }
}
