package Modelos;

public class Funcionario {
    private String email;
    private String nome;
    private String senha;
    private String cpf;

    public Funcionario(String cpf, String nome, String email, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }
    
}
