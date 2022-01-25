package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;

    public ConnectionProvider() {
    }

    public static Connection getConnection() {
        if (connection == null){
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank_system",
                        "postgres","Alireza1376");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }
}
