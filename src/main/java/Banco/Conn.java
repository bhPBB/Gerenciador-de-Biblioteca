package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
     public static Connection connectToDatabase() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // O meu database chama teste, ajustem o de vcs nessa linha
            con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca?user=root&password="); 
            //System.out.println("Conexão estabelecida com sucesso.");
        } catch (ClassNotFoundException ex) {
           // System.out.println("Driver do Banco não encontrado.");
        } catch (SQLException ex) {
            //System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
        return con;
    }
}


