package Modelos;

public class Emprestimo {
    private String id;
    private String descricao;
    private String estado;

    public Emprestimo(String id, String descricao, String estado) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
    }
    
    String getId(){
        return this.id;
    }

}
