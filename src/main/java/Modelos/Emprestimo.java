package Modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Emprestimo {
    private final StringProperty livro = new SimpleStringProperty();
    private final StringProperty cliente = new SimpleStringProperty();
    private final StringProperty funcionario = new SimpleStringProperty();
    private final StringProperty dataEmprestimo = new SimpleStringProperty();
    private final StringProperty dataDevolucao = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<Image> editar = new SimpleObjectProperty();
    private final ObjectProperty<Image> apagar = new SimpleObjectProperty();

    public Emprestimo(String livro, String cliente, String funcionario, LocalDate dataEmprestimo, LocalDate dataDevolucao, String status) {
        this.livro.set(livro);
        this.cliente.set(cliente);
        this.funcionario.set(funcionario);
        this.dataEmprestimo.set(setData(dataEmprestimo));
        this.dataDevolucao.set(setData(dataDevolucao));
        editar.set(new Image(getClass().getResourceAsStream("/Imagens/edit-regular-12.png")));
        apagar.set(new Image(getClass().getResourceAsStream("/Imagens/trash-regular-12.png")));
        
        if(status.contains("Fechado")){
            this.status.set(status);
        } else{
            this.status.set(setStatus(dataDevolucao));
        }
        
    }

    public String getLivro() {
        return livro.getValue();
    }

    public String getCliente() {
        return cliente.getValue();
    }

    public String getFuncionario() {
        return funcionario.getValue();
    }

    public String getDataEmprestimo() {
        return dataEmprestimo.getValue();
    }

    public String getDataDevolucao() {
        return dataDevolucao.getValue();
    }

    public String getStatus() {
        return status.getValue();
    }
    
    public Image getEditar() {
        return editar.getValue();
    }
    
    public Image getApagar() {
        return apagar.getValue();
    }

    private String setStatus(LocalDate dataDevolucao) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);

        if(dias >= 0){
            return (dias + " dia(s) restante(s)");
        }else{
            return (dias*-1 + " dia(s) atrasado");
        }
    }

    private String setData(LocalDate data){
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatacao);
        return dataFormatada;
    }

}
