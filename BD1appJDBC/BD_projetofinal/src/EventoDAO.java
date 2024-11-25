import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    // CREATE
    public void inserirEvento(Evento evento) {
        String sql = "INSERT INTO Evento (Data, Nome, Locatario) VALUES (?, ?, ?)";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, evento.getData());
            stmt.setString(2, evento.getNome());
            stmt.setString(3, evento.getLocatario());
            stmt.executeUpdate();

            System.out.println("Evento inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Evento> listarEventos() {
        String sql = "SELECT * FROM Evento";
        List<Evento> eventos = new ArrayList<>();

        try (Connection conn = BdConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("ID"));
                evento.setData(rs.getDate("Data"));
                evento.setNome(rs.getString("Nome"));
                evento.setLocatario(rs.getString("Locatario"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // UPDATE
    public void atualizarEvento(Evento evento) {
        String sql = "UPDATE Evento SET Data = ?, Nome = ?, Locatario = ? WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, evento.getData());
            stmt.setString(2, evento.getNome());
            stmt.setString(3, evento.getLocatario());
            stmt.setInt(4, evento.getId());
            stmt.executeUpdate();

            System.out.println("Evento atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletarEvento(int id) {
        String sql = "DELETE FROM Evento WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Evento deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
