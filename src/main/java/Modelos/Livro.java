package Modelos;


public class Livro {
    
    private String nome;
    private int id;
    private int idFunci;
    private int qtde;
    
    public Livro(String nome, int id, int idFunci, int qtde){
        this.nome = nome;
        this.id = id;
        this.idFunci = idFunci;
        this.qtde = qtde;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getId(){
        return id;
    }
    
    public int getIdFunci(){
        return idFunci;
    }
    
    public int getQtde(){
        return qtde;
    }
    
    
}
