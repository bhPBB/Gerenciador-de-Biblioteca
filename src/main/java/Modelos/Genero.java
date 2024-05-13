package Modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Genero {
    private final StringProperty codigo = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();

    public Genero(String id, String nome) {
        this.codigo.set(id);
        this.descricao.set(nome);
    }

    public String getCodigo() {
        return codigo.getValue();
    }

    public String getDescricao() {
        return descricao.getValue();
    }

}
