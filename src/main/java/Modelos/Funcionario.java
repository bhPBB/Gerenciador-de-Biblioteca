package Modelos;

public class Funcionario {
    private String email;
    private String nome;
    private String senha;
    private int cpf;

    public Funcionario(String email, String nome, String senha, int cpf) {
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

    public int getCpf() {
        return cpf;
    }
    
}
