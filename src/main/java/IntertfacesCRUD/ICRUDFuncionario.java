/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IntertfacesCRUD;

/**
 *
 * @author bruno
 */
public interface ICRUDFuncionario {
    //criar e deletar cliente
    public ICRUDFuncionario criarFuncionario(String CPF, String nome, String senha);
    public void deletarFuncionario(ICRUDFuncionario funcionario);
    
    //Getter Id
    public int getId();
    
    //Getter e Setter CPF
    public String getCPF();
    public void setCPF(String CPF);
    
    //Getter e Setter nome
    public String getNome();
    public void setNome(String nome);
    
    //Getter e Setter senha
    public String getSenha();
    public void setSenha(String senha);
}
