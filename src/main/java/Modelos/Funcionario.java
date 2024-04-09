package Modelos;

public class Funcionario {
    private String email;
    private String nome;
    private String cpf;

    public Funcionario(String cpf, String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
    
}
