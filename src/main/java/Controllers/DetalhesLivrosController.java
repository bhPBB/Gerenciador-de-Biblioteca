/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class DetalhesLivrosController implements Initializable {
    
    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView imagemCapaLivro;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelGenero;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private Label labelTitulo;

    @FXML
    void cursorMaozinha(MouseEvent event) {

    }

    @FXML
    void cursorPadrao(MouseEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializando a sidebar
            
        try 
        {    
            var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sidebar.fxml"));
            
            AnchorPane anchorSideBar = fxmlLoader.load();
            
            background.getChildren().add(anchorSideBar);
        } 
        catch (IOException ex) 
        {
            System.out.println("Erro ao carregar a sidebar: " + ex.getMessage());
        }
            
    }    
    
}
