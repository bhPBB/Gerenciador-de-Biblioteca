package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardListarFuncionarioController{

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
    
    private Funcionario modelo;

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
        modelo = new Funcionario();
        modelo.setCpf(cpf);
        modelo.setNome(nome);
        modelo.setEmail(getEmail(cpf));
        modelo.setFoto(getFoto(cpf));
    }
    
    private String getEmail(String cpf){
        String email = null;
        String query = "SELECT EMAIL FROM FUNCIONARIO WHERE CPF = '" + cpf + "'";
        try{
            ResultSet rs = Database.executarSelect(query);
            if(rs.next())
                email = rs.getString("EMAIL");
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex);
        }
        return email;
    }
    
    private byte[] getFoto(String cpf){
        byte[] foto = null;
        String query = "SELECT foto FROM funcionario WHERE cpf LIKE '" + cpf + "'";

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
    
}
