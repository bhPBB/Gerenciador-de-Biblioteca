package ClassesTeste;

import InterfacesCRUD.ICRUDLivro;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruno
 */
public class LivroTeste implements ICRUDLivro {
    private static int ultimoId = 0;
    private final int id;
    private int qtdEstoque;
    private String nome, autor, genero;

    public LivroTeste(int qtdEstoque, String nome, String autor, String genero) {
        this.qtdEstoque = qtdEstoque;
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        
        // O id é atualizado automaticamente
        LivroTeste.ultimoId++;
        this.id = ultimoId;
    }

    @Override
    public void deletarLivro(ICRUDLivro livro) {
        //Nessa classe esse método não faz nada, mas nas classes de verdade, vai apagar o livro do banco de dados.
        System.out.println("O livro " + livro.getNome() + " (ID: " + livro.getId() + ") foi deletado.");
    }

    @Override
    public int getId() {
        return id;
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
    public String getGenero() {
        return genero;
    }

    @Override
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    @Override
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}
