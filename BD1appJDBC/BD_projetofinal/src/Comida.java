public class Comida {
    private int id;
    private String nome;
    private double preco;
    private int disponibilidade;

    // Construtor padrão
    public Comida() {}

    // Construtor com parâmetros
    public Comida(int id, String nome, double preco, int disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(int disponibilidade) { this.disponibilidade = disponibilidade; }
}
