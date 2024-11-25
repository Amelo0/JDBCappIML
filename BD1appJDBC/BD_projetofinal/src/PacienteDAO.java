import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // CREATE
    public void inserirPaciente(Paciente paciente) {
        String sql = "INSERT INTO Paciente (Nome, Idade, Cpf) VALUES (?, ?, ?)";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getCpf());
            stmt.executeUpdate();

            System.out.println("Paciente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Paciente> listarPacientes() {
        String sql = "SELECT * FROM Paciente";
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = BdConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("ID"));
                paciente.setNome(rs.getString("Nome"));
                paciente.setIdade(rs.getInt("Idade"));
                paciente.setCpf(rs.getString("Cpf"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    // UPDATE
    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE Paciente SET Nome = ?, Idade = ?, Cpf = ? WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getCpf());
            stmt.setInt(4, paciente.getId());
            stmt.executeUpdate();

            System.out.println("Paciente atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletarPaciente(int id) {
        String sql = "DELETE FROM Paciente WHERE ID = ?";

        try (Connection conn = BdConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Paciente deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
