package Modelos;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Genero {
    private final StringProperty codigo = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    private final ObjectProperty<Image> editar = new SimpleObjectProperty();
    private final ObjectProperty<Image> apagar = new SimpleObjectProperty();

    public Genero(String id, String nome) {
        this.codigo.set(id);
        this.descricao.set(nome);
        editar.set(new Image(getClass().getResourceAsStream("/Imagens/edit-regular-12.png")));
        apagar.set(new Image(getClass().getResourceAsStream("/Imagens/trash-regular-12.png")));
    }

    public String getCodigo() {
        return codigo.getValue();
    }

    public String getDescricao() {
        return descricao.getValue();
    }
  
    public Image getEditar() {
        return editar.getValue();
    }
    
    public Image getApagar() {
        return apagar.getValue();
    }

}
