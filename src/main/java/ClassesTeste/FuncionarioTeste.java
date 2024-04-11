package ClassesTeste;

import InterfacesCRUD.ICRUDFuncionario;

public class FuncionarioTeste implements ICRUDFuncionario {
    private static int ultimoId = 0;
    private final int id;
    private String CPF, nome, senha, email;

    public FuncionarioTeste(String CPF, String nome, String email, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        
        // O id é atualizado automaticamente
        FuncionarioTeste.ultimoId++;
        this.id = ultimoId;
    }
    
    //Metodo para o teste da função getId funcionar
    public static void resetarUltimoId() {
        ultimoId = 0;
    }
    
    @Override
    public void deletarFuncionario(ICRUDFuncionario funcionario) {
        //Nessa classe esse método não faz nada, mas nas classes de verdade, vai apagar o funcionário do banco de dados.
        System.out.println("O funcionário " + funcionario.getNome() + " (ID: " + funcionario.getId() + ") foi deletado.");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCPF() {
        return CPF;
    }

    @Override
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
