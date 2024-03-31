module com.mycompany.gerenciadordebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.gerenciadordebiblioteca to javafx.fxml;
    exports com.mycompany.gerenciadordebiblioteca;
}
