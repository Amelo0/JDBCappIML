import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComidaDAO {

    // CREATE
    public void inserirComida(Comida comida) {
        String sql = "INSERT INTO Comida (Nome, Preco, Disponibilidade) VALUES (?, ?, ?)";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comida.getNome());
            stmt.setDouble(2, comida.getPreco());
            stmt.setInt(3, comida.getDisponibilidade());
            stmt.executeUpdate();

            System.out.println("Comida inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Comida> listarComidas() {
        String sql = "SELECT * FROM Comida";
        List<Comida> comidas = new ArrayList<>();

        try (Connection conn = BdConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Comida comida = new Comida();
                comida.setId(rs.getInt("ID"));
                comida.setNome(rs.getString("Nome"));
                comida.setPreco(rs.getDouble("Preco"));
                comida.setDisponibilidade(rs.getInt("Disponibilidade"));
                comidas.add(comida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comidas;
    }

    // UPDATE
    public void atualizarComida(Comida comida) {
        String sql = "UPDATE Comida SET Nome = ?, Preco = ?, Disponibilidade = ? WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comida.getNome());
            stmt.setDouble(2, comida.getPreco());
            stmt.setInt(3, comida.getDisponibilidade());
            stmt.setInt(4, comida.getId());
            stmt.executeUpdate();

            System.out.println("Comida atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletarComida(int id) {
        String sql = "DELETE FROM Comida WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Comida deletada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
