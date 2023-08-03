package Core.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    public static Database getInstance() {
        return instance;
    }

    public Database() {
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/NGI","root","shirin1234");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
