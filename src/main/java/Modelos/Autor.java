package Modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Autor {
    private final StringProperty codigo = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty();

    public Autor(String id, String nome) {
        this.codigo.set(id);
        this.nome.set(nome);
    }

    public String getCodigo() {
        return codigo.getValue();
    }

    public String getNome() {
        return nome.getValue();
    }

}
