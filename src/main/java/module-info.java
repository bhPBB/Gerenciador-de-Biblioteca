module com.mycompany.gerenciadordebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.base;
    
    opens GerenciadorDeBiblioteca to javafx.fxml;
    exports GerenciadorDeBiblioteca;
}
