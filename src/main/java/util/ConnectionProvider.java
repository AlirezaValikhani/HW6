package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;

    public ConnectionProvider() {
    }

    public static Connection getConnection() {
        if (connection == null){
            try {
                Class.forName("org.postgresql.BankSystem");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres","Alireza1376");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
