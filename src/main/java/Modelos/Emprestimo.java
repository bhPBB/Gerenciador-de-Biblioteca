package Modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Emprestimo {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    private final StringProperty estado = new SimpleStringProperty();

    public Emprestimo(String id, String descricao, String estado) {
        this.id.set(id);
        this.descricao.set(descricao);
        this.estado.set(estado);
    }

    public String getId() {
        return id.getValue();
    }

    public String getDescricao() {
        return descricao.getValue();
    }

    public String getEstado() {
        return estado.getValue();
    }

}
