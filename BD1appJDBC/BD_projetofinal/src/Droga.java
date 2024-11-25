public class Droga {
    private int id;
    private String nome;
    private int disponibilidade;

    // Construtor padrão
    public Droga() {}

    // Construtor com parâmetros
    public Droga(int id, String nome, int disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(int disponibilidade) { this.disponibilidade = disponibilidade; }
}
