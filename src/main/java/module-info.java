module com.mycompany.gerenciadordebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.base;
    
    opens com.mycompany.gerenciadordebiblioteca to javafx.fxml;
    exports com.mycompany.gerenciadordebiblioteca;
    
}
