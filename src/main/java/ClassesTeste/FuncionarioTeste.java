/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassesTeste;

import InterfacesCRUD.ICRUDFuncionario;

/**
 *
 * @author bruno
 */
public class FuncionarioTeste implements ICRUDFuncionario {
    private static int ultimoId = 0;
    private final int id;
    private String CPF, nome, senha;

    private FuncionarioTeste(String CPF, String nome, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.senha = senha;
        
        // O id é atualizado automaticamente
        FuncionarioTeste.ultimoId++;
        this.id = ultimoId;
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
