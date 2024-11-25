import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/iml_db";
    private static final String USER = "root";
    private static final String PASSWORD = "00000";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
