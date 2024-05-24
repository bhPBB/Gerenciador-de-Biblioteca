
package Modelos;

public class Cliente {
    
    private String nome;
    private String cpf;
    private String cep;
    private String email;
    private byte[] foto;
    
    public Cliente(){
        // Construtor vazio para permitir configuração posterior
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getFoto() {
        return foto;
    }
    
    
}
