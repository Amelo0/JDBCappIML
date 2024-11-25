import java.sql.Date;

public class Evento {
    private int id;
    private Date data;
    private String nome;
    private String locatario;

    // Construtor padrão
    public Evento() {}

    // Construtor com parâmetros
    public Evento(int id, Date data, String nome, String locatario) {
        this.id = id;
        this.data = data;
        this.nome = nome;
        this.locatario = locatario;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocatario() { return locatario; }
    public void setLocatario(String locatario) { this.locatario = locatario; }
}
