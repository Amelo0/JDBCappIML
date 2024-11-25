import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrogaDAO {

    // CREATE
    public void inserirDroga(Droga droga) {
        String sql = "INSERT INTO Droga (Nome, Disponibilidade) VALUES (?, ?)";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, droga.getNome());
            stmt.setInt(2, droga.getDisponibilidade());
            stmt.executeUpdate();

            System.out.println("Droga inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Droga> listarDrogas() {
        String sql = "SELECT * FROM Droga";
        List<Droga> drogas = new ArrayList<>();

        try (Connection conn = BdConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Droga droga = new Droga();
                droga.setId(rs.getInt("ID"));
                droga.setNome(rs.getString("Nome"));
                droga.setDisponibilidade(rs.getInt("Disponibilidade"));
                drogas.add(droga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drogas;
    }

    // UPDATE
    public void atualizarDroga(Droga droga) {
        String sql = "UPDATE Droga SET Nome = ?, Disponibilidade = ? WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, droga.getNome());
            stmt.setInt(2, droga.getDisponibilidade());
            stmt.setInt(3, droga.getId());
            stmt.executeUpdate();

            System.out.println("Droga atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletarDroga(int id) {
        String sql = "DELETE FROM Droga WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Droga deletada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
