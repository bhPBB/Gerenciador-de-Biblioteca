package ClassesTeste;

import InterfacesCRUD.ICRUDCliente;

public class ClienteTeste implements ICRUDCliente {
    private String CPF, nome, cidade, estado;
    private int numEmprestimos;
    private boolean caloteiro;

    public ClienteTeste(String CPF, String nome, String cidade, String estado) {
        this.CPF = CPF;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        
        // Quando o cliente é criado, ele não tem empréstimos nem é caloteiro ainda.
        this.numEmprestimos = 0;
        this.caloteiro = false;
    }
  
    @Override
    public void deletarCliente(ICRUDCliente cliente)
    {
        //Nessa classe esse método não faz nada, mas nas classes de verdade, vai apagar o cliente do banco de dados.
        System.out.println("O cliente " + cliente.getNome() + " (CPF: " + cliente.getCPF() + ") foi deletado.");
    }
    
    @Override
    public String getCPF() {
        return this.CPF;
    }

    @Override
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCidade() {
        return this.cidade;
    }

    @Override
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String getEstado() {
        return this.estado;
    }

    @Override
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int getNumEmprestimos() {
        return this.numEmprestimos;
    }

    @Override
    public void setNumEmprestimos(int numEmprestimos) {
        this.numEmprestimos = numEmprestimos;
                
    }

    @Override
    public boolean ehCaloteiro() {
        return this.caloteiro;
    }

    @Override
    public void ehCaloteiro(boolean ehCaloteiro) {
        caloteiro = ehCaloteiro;
    }

    
}
