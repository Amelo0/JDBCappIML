import java.sql.*;
import java.util.Scanner;

public class PacienteManager implements Manager {

    private static final String URL = "jdbc:mysql://localhost:3306/iml_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "00000";

    @Override
    public void inserir(Scanner scanner) {
        System.out.print("Informe o nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a idade do paciente: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();

        String sql = "INSERT INTO Paciente (Nome, Idade, Cpf) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, cpf);
            stmt.executeUpdate();
            System.out.println("Paciente inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM Paciente";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de pacientes:");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getString("Nome") +
                        " (Idade: " + rs.getInt("Idade") + ", CPF: " + rs.getString("Cpf") + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Scanner scanner) {
        System.out.print("Informe o ID do paciente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe a nova idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Informe o novo CPF: ");
        String cpf = scanner.nextLine();

        String sql = "UPDATE Paciente SET Nome = ?, Idade = ?, Cpf = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, cpf);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Scanner scanner) {
        System.out.print("Informe o ID do paciente a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String verificaSql = "SELECT COUNT(*) FROM Paciente WHERE ID = ?";
        String deletaSql = "DELETE FROM Paciente WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement verificaStmt = conn.prepareStatement(verificaSql)) {

            verificaStmt.setInt(1, id);
            ResultSet rs = verificaStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Nenhum paciente encontrado com o ID informado.");
                return;
            }

            try (PreparedStatement deletaStmt = conn.prepareStatement(deletaSql)) {
                deletaStmt.setInt(1, id);
                deletaStmt.executeUpdate();
                System.out.println("Paciente deletado com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
