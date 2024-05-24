package Modelos;

public class Funcionario {
    private String email;
    private String nome;
    private String cpf;
    private byte[] foto;
    private static Funcionario instance;
    
    public Funcionario(){
        //Construtor vazio
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto() {
        return foto;
    }

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

    public static Funcionario logout(){
        instance = null;
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
