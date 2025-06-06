// Credential.java - Representa uma credencial
package gerenciador_senhas;

// Credential.java - Representa uma credencial com senha original (somente para testes)
public class Credential {
    private String servico;
    private String usuario;
    private String senhaCriptografada;

    public Credential(String servico, String usuario, String senhaCriptografada) {
        this.servico = servico;
        this.usuario = usuario;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getServico() {
        return servico;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }
}