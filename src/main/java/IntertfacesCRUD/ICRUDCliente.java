/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IntertfacesCRUD;

/**
 *
 * @author bruno
 */
public interface ICRUDCliente {
    //criar e deletar cliente
    public ICRUDCliente criarCliente(String CPF, String nome, String cidade, String estado);
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
