package com.mycompany.gerenciadordebiblioteca;

import Controllers.BotaoHeaderController;
import Controllers.SidebarController;
import Controllers.TituloHeaderController;
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
import javafx.scene.layout.AnchorPane;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static Parent root;

    //Para as telas de detalhes
    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboard"));
        stage.setTitle("Gerenciador de Biblioteca");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/Imagens/icon.png")));
        stage.setScene(scene);
        stage.show();
        App.stage = stage;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlUrl = App.class.getResource("/fxml/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }
    
    public static void mudarDeTela(String tela) throws IOException {
        root = loadFXML(tela);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setCursorMaozinha(MouseEvent event) {
        var n = (Node) event.getSource();
        n.setCursor(Cursor.HAND);
    }
    public static void setCursorPadrao(MouseEvent event) {
        var n = (Node) event.getSource();
        n.setCursor(Cursor.DEFAULT);
    }
    public static void setCursorCarregando(MouseEvent event) {
        var n = (Node) event.getSource();
        n.setCursor(Cursor.WAIT);
    }
    
    public static void trocarEstilo(MouseEvent event, String estiloAnterior, String estiloNovo) {
        var n = (Node) event.getSource();
        n.getStyleClass().remove(estiloAnterior);
        n.getStyleClass().add(estiloNovo);
    }
    
    public static void inicialzarSidebarHeader(String telaAtual, String TituloTela, String textoBotao, String telaBotao, AnchorPane backgroundTela) throws IOException {
        
        inicializarSidebar(telaAtual, backgroundTela);
        
        inicializarTituloHeader(TituloTela, backgroundTela);
        
        inicializarBotaoHeader(textoBotao, telaBotao, backgroundTela);
    }
    
    public static void inicializarSidebar(String telaAtual, AnchorPane backgroundTela) throws IOException {
        //Carrega a sidebar    
        var sidebarLoader = new FXMLLoader(App.class.getResource("/fxml/sidebar.fxml"));
        AnchorPane anchorSideBar = sidebarLoader.load();
        
        SidebarController sc = sidebarLoader.getController();
        sc.inicializar(telaAtual);
        
        backgroundTela.getChildren().add(anchorSideBar);
    }
    
    public static void inicializarTituloHeader(String titulo, AnchorPane backgroundTela) throws IOException {
         //Define o título da página 
        var tituloLoader = new FXMLLoader(App.class.getResource("/fxml/tituloHeader.fxml"));
        AnchorPane anchorTituloTela = tituloLoader.load();
        
        TituloHeaderController tc = tituloLoader.getController();
        tc.setTitulo(titulo);
        
        anchorTituloTela.setLayoutX(240);
        backgroundTela.getChildren().add(anchorTituloTela);
    }
    
     public static void inicializarBotaoHeader(String textoBotao, String telaBotao, AnchorPane backgroundTela) throws IOException {
        //Carrega o botao do header
        var botaoHeaderLoader = new FXMLLoader(App.class.getResource("/fxml/botaoHeader.fxml"));
        AnchorPane anchorBotaoHeader = botaoHeaderLoader.load();

        BotaoHeaderController bc = botaoHeaderLoader.getController();
        bc.setIrPara(textoBotao, telaBotao);
        
        anchorBotaoHeader.setLayoutX(1200);
        backgroundTela.getChildren().add(anchorBotaoHeader);
    }
     
    public static void main(String[] args) {
        launch();
    }
}