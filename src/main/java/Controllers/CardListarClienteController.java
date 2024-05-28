package Controllers;

import Banco.Database;
import Modelos.Cliente;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CardListarClienteController {

    @FXML
    private ImageView deletar;

    @FXML
    private ImageView editar;

    @FXML
    private ImageView foto;

    @FXML
    private Label nome;
    
    @FXML
    private Label cpf;
    
    private Cliente modelo;

    // Método para criar e exibir um card com as informações do cliente
    @FXML
    public void criarCard(String nome, String cpf) {
        // Define o nome do cliente no label
        this.nome.setText(nome);
        this.cpf.setText(cpf);
                
        try {
            byte[] imageBytes = getFoto(cpf);
            if (imageBytes != null) {
                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                Image foto = new Image(inputStream);
                this.foto.setImage(foto);
            } else {
                System.out.println("Nenhuma imagem encontrada para o cliente: " + nome);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao definir a imagem no ImageView: " + ex.getMessage());
        }
        
        // Configure o modelo
        modelo = new Cliente();
        modelo.setNome(nome);
        modelo.setFoto(getFoto(cpf));
        modelo.setEmail(getEmail(cpf));
        modelo.setCep(getCep(cpf));
        modelo.setCpf(cpf);
    }
    
    public void info(ActionEvent event) {
        try {
            URL fxmlUrl = getClass().getResource("/fxml/detalhesClientes.fxml");
            if (fxmlUrl == null) {
                System.out.println("ERRO: 'detalhesClientes.fxml' não encontrado.");
                return;
            } else {
                System.out.println("SUCESSO: 'detalhesClientes.fxml' encontrado.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            AnchorPane fxml = fxmlLoader.load();

            // Passa as informações ao controller
            DetalhesClienteController c = fxmlLoader.getController();
            if (modelo != null) {
                c.setDetalhes(modelo);
            } else {
                System.out.println("ERRO: O modelo está vazio.");
            }

            // Renderiza a view
            Scene cena = new Scene(fxml);
            App.getStage().setScene(cena);
            App.getStage().show();
        } catch (IOException ex) {
            System.out.print("ERRO: Não foi possível carregar 'detalhesLivros.fxml'. " + ex.getMessage());
        }
    }
    
    private String getEmail(String cpf){
        String email = null;
        String query = "SELECT email FROM cliente WHERE cpf LIKE '" + cpf + "'";
        try{
            ResultSet rs = Database.executarSelect(query);
            if(rs.next())
                email = rs.getString("email");
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex);
        }
        return email;
    }
    
    private String getCep(String cpf){
        String cep = null;
        String query = "SELECT cep FROM cliente WHERE cpf LIKE '" + cpf + "'";
        try{
            ResultSet rs = Database.executarSelect(query);
            if(rs.next())
                cep = rs.getString("cep");
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex);
        }
        return cep;
    }

    private byte[] getFoto(String cpf){
        byte[] foto = null;
        String query = "SELECT foto FROM cliente WHERE cpf LIKE '" + cpf + "'";
        
        try{
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {
                // Recupera a imagem da coluna bytea
                foto = rs.getBytes("foto");
            }
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
            
        }
        return foto;
    }
    
    @FXML
    public void deletar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir este item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                String id = cpf.getText();
                String query = "DELETE FROM cliente WHERE cpf = '" + id + "'";
                Database.executarQuery(query);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao excluir o item: " + ex.getMessage());
            }
        }
    }
    
}
