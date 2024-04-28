/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class DetalhesLivrosController implements Initializable {
    @FXML
    private AnchorPane background;
    @FXML
    private VBox boxSidebar;

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
