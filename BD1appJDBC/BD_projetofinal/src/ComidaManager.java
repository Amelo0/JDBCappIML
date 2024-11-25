import java.sql.*;
import java.util.Scanner;

public class ComidaManager implements Manager {

    private static final String URL = "jdbc:mysql://localhost:3306/iml_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "00000";

    @Override
    public void inserir(Scanner scanner) {
        System.out.print("Informe o nome da comida: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o preço da comida: ");
        double preco = scanner.nextDouble();
        System.out.print("Informe a disponibilidade da comida: ");
        int disponibilidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String sql = "INSERT INTO Comida (Nome, Preco, Disponibilidade) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, disponibilidade);
            stmt.executeUpdate();
            System.out.println("Comida inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM Comida";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de comidas:");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getString("Nome") +
                        " (Preço: " + rs.getDouble("Preco") +
                        ", Disponibilidade: " + rs.getInt("Disponibilidade") + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Scanner scanner) {
        System.out.print("Informe o ID da comida a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o novo preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Informe a nova disponibilidade: ");
        int disponibilidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String sql = "UPDATE Comida SET Nome = ?, Preco = ?, Disponibilidade = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, disponibilidade);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Comida atualizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Scanner scanner) {
        System.out.print("Informe o ID da comida a ser deletada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String verificaSql = "SELECT COUNT(*) FROM Comida WHERE ID = ?";
        String deletaSql = "DELETE FROM Comida WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement verificaStmt = conn.prepareStatement(verificaSql)) {

            verificaStmt.setInt(1, id);
            ResultSet rs = verificaStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Nenhuma comida encontrada com o ID informado.");
                return;
            }

            try (PreparedStatement deletaStmt = conn.prepareStatement(deletaSql)) {
                deletaStmt.setInt(1, id);
                deletaStmt.executeUpdate();
                System.out.println("Comida deletada com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
