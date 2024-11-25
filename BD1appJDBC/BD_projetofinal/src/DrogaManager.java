import java.sql.*;
import java.util.Scanner;

public class DrogaManager implements Manager {

    private static final String URL = "jdbc:mysql://localhost:3306/iml_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "00000";

    @Override
    public void inserir(Scanner scanner) {
        System.out.print("Informe o nome da droga: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a disponibilidade da droga: ");
        int disponibilidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String sql = "INSERT INTO Droga (Nome, Disponibilidade) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, disponibilidade);
            stmt.executeUpdate();
            System.out.println("Droga inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM Droga";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de drogas:");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getString("Nome") +
                        " (Disponibilidade: " + rs.getInt("Disponibilidade") + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Scanner scanner) {
        System.out.print("Informe o ID da droga a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a nova disponibilidade: ");
        int disponibilidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String sql = "UPDATE Droga SET Nome = ?, Disponibilidade = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, disponibilidade);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Droga atualizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Scanner scanner) {
        System.out.print("Informe o ID da droga a ser deletada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String verificaSql = "SELECT COUNT(*) FROM Droga WHERE ID = ?";
        String deletaSql = "DELETE FROM Droga WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement verificaStmt = conn.prepareStatement(verificaSql)) {

            verificaStmt.setInt(1, id);
            ResultSet rs = verificaStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Nenhuma droga encontrada com o ID informado.");
                return;
            }

            try (PreparedStatement deletaStmt = conn.prepareStatement(deletaSql)) {
                deletaStmt.setInt(1, id);
                deletaStmt.executeUpdate();
                System.out.println("Droga deletada com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
