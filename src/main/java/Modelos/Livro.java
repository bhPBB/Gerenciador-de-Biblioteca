package Modelos;

public class Livro {

    private String nome;
    private int id;
    private int idFunci;
    private int qtde;
    private byte[] imagem;
    private String autores;
    private String generos;

    public Livro() {
        // Construtor vazio para permitir configuração posterior
    }

    public Livro(String nome, int id, int idFunci, int qtde) {
        this.nome = nome;
        this.id = id;
        this.idFunci = idFunci;
        this.qtde = qtde;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public int getIdFunci() {
        return idFunci;
    }

    public int getQtde() {
        return qtde;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public String getAutores() {
        return autores;
    }

    public String getGeneros() {
        return generos;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFunci(int idFunci) {
        this.idFunci = idFunci;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }
}
