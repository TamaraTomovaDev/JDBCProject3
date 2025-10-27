package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConfig {
// === Database configuratie ===
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/accountdb?useSSL=false";
    private static final String JDBC_USER = "intec";
    private static final String JDBC_PASSWORD = "intec-123";

    // Een verbinding maken met de MySQL-database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
