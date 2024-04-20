package ORM;
import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/jdbc-book";
    private static final String username = "root";
    private static final String password = "root";
    private static Connection connection = null;

    private DatabaseConnection(){}

    static public Connection getConnection() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.libri");
        if (connection == null)
            connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
