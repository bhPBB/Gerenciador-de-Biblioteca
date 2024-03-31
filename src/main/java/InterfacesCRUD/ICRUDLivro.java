/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesCRUD;

/**
 *
 * @author bruno
 */
public interface ICRUDLivro {
    //deletar Livro
    public void deletarLivro(ICRUDLivro livro);
    
    //Getter Id
    public int getId();
    
    //Getter e Setter nome
    public String getNome();
    public void setNome(String nome);
    
    //Getter e Setter genero
    public String getGenero();
    public void setGenero(String genero);
    
    //Getter e Setter autor
    public String getAutor();
    public void setAutor(String autor);
    
    //Getter e Setter qtdEstoque
    public int getQtdEstoque();
    public void setQtdEstoque(int qtdEstoque);
}
