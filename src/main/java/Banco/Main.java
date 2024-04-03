package Banco;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         try {
            Database.conectar();
            Database.desconectar();
        } catch (SQLException ex) {
            System.out.print("Error occurred while UPDATE Operation: " + ex);
            throw ex;
        }
    }
}
