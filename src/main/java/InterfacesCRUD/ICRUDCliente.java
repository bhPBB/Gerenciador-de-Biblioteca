package InterfacesCRUD;

public interface ICRUDCliente {
    //deletar cliente
    public void deletarCliente(ICRUDCliente cliente);
    
    //Getter e Setter CPF
    public String getCPF();
    public void setCPF(String CPF);
    
    //Getter e Setter nome
    public String getNome();
    public void setNome(String nome);
    
    //Getter e Setter cidade
    public String getCidade();
    public void setCidade(String cidade);
    
    //Getter e Setter estado
    public String getEstado();
    public void setEstado(String estado);
    
    //Getter e Setter numEmprestimos
    public int getNumEmprestimos();
    public void setNumEmprestimos(int numEmprestimos);
    
    //Getter e Setter caloteiro
    public boolean ehCaloteiro();
    public void ehCaloteiro(boolean ehCaloteiro);
}
