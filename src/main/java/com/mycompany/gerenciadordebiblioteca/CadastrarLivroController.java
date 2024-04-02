/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gerenciadordebiblioteca;

import ClassesTeste.LivroTeste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class CadastrarLivroController {

    @FXML
    private TextField inputTitulo, inputGenero, inputAutor, inputQtdEstoque;
    @FXML
    private Label labelErro;
    
    private LivroTeste l;
    
    @FXML
    private void cadastrarLivro(ActionEvent e)
    {
        try
        {
            var qtdEstoque = Integer.parseInt(inputQtdEstoque.getText());
            var nome = inputTitulo.getText();
            var autor = inputAutor.getText();
            var genero = inputGenero.getText();
            
            l = new LivroTeste(qtdEstoque, nome, autor, genero);
            
            System.out.println("ID do livro: " + l.getId());
            System.out.println("Título do livro: " + l.getNome());
        }
        catch (NumberFormatException n)
        {
            final var ERRO_QTD_ESTOQUE = "Qtd. em Estoque precisa ser um número!";
            
            labelErro.setText(ERRO_QTD_ESTOQUE);
            
            System.out.println(ERRO_QTD_ESTOQUE);
        }
        catch (Exception erro)
        {
            labelErro.setText("Erro desconhecido.");
            System.out.println(erro);
        }    
    }
}
