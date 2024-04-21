package com.mycompany.gerenciadordebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static Parent root;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboard"));
        stage.setScene(scene);
        stage.show();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlUrl = App.class.getResource("/fxml/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }
    
    public static void mudarDeTela(ActionEvent e, String tela) throws IOException{
        try {
        root = loadFXML(tela);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } 
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}