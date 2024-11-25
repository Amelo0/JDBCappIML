import java.sql.*;
import java.util.Scanner;

public class EventoManager implements Manager {

    private static final String URL = "jdbc:mysql://localhost:3306/iml_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "00000";

    @Override
    public void inserir(Scanner scanner) {
        System.out.print("Informe o nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a data do evento (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        Date data = Date.valueOf(dataStr); // Converte String para Date
        System.out.print("Informe o locatário do evento (ou deixe em branco): ");
        String locatario = scanner.nextLine();

        String sql = "INSERT INTO Evento (Data, Nome, Locatario) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, data);
            stmt.setString(2, nome);
            stmt.setString(3, locatario.isEmpty() ? null : locatario);
            stmt.executeUpdate();
            System.out.println("Evento inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM Evento";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de eventos:");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getString("Nome") +
                        " (Data: " + rs.getDate("Data") +
                        ", Locatário: " + rs.getString("Locatario") + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Scanner scanner) {
        System.out.print("Informe o ID do evento a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a nova data (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        Date data = Date.valueOf(dataStr);
        System.out.print("Informe o novo locatário (ou deixe em branco): ");
        String locatario = scanner.nextLine();

        String sql = "UPDATE Evento SET Data = ?, Nome = ?, Locatario = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, data);
            stmt.setString(2, nome);
            stmt.setString(3, locatario.isEmpty() ? null : locatario);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Evento atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Scanner scanner) {
        System.out.print("Informe o ID do evento a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String verificaSql = "SELECT COUNT(*) FROM Evento WHERE ID = ?";
        String deletaSql = "DELETE FROM Evento WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement verificaStmt = conn.prepareStatement(verificaSql)) {

            verificaStmt.setInt(1, id);
            ResultSet rs = verificaStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Nenhum evento encontrado com o ID informado.");
                return;
            }

            try (PreparedStatement deletaStmt = conn.prepareStatement(deletaSql)) {
                deletaStmt.setInt(1, id);
                deletaStmt.executeUpdate();
                System.out.println("Evento deletado com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
