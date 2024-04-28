package com.mycompany.gerenciadordebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static Parent root;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setTitle("Gerenciador de Biblioteca");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/Imagens/icon1.png")));
        stage.setScene(scene);
        stage.show();
        App.stage = stage;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlUrl = App.class.getResource("/fxml/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }
    
    public static void mudarDeTela(String tela) throws IOException{
        try {
        root = loadFXML(tela);
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
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
    
    public static void setCursorMaozinha(MouseEvent event) {
        var n = (Node) event.getSource();
        n.setCursor(Cursor.HAND);
    }
    public static void setCursorPadrao(MouseEvent event) {
        var n = (Node) event.getSource();
        n.setCursor(Cursor.DEFAULT);
    }
    
    public static void trocarEstilo(MouseEvent event, String estiloAnterior, String estiloNovo) {
        var n = (Node) event.getSource();
        n.getStyleClass().remove(estiloAnterior);
        n.getStyleClass().add(estiloNovo);
    }
    
    public static void main(String[] args) {
        launch();
    }

}