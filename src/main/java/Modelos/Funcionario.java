package Modelos;

public final class Funcionario {
    private String email;
    private String nome;
    private String cpf;
    private static Funcionario instance;

    private Funcionario(String cpf, String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public static Funcionario getFuncionario(String cpf, String nome, String email){
       if (instance == null) {
            instance = new Funcionario(cpf, nome, email);
        }
        return instance;
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
