import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDatabase {

    private static final String url = "jdbc:sqlite:local_database.db";

    public static void main( String[] args){
        LocalDatabase.init();
    }

    public static void init(){
        try (Connection connection = DriverManager.getConnection(url)) {
            // Create a table (if not exists)
            createTable(connection);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS states (" +
                "hash VARCHAR PRIMARY KEY AUTOINCREMENT," +
                "winchance FLOAT"; 
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.executeUpdate();
        }
    }

    private static void insertData(Connection connection, String hash, float winchance) throws SQLException {
        String insertDataSQL = "INSERT INTO users (hash, winchance) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, hash);
            statement.setFloat(2, winchance);
            statement.executeUpdate();
        }
    }

    private static void displayData(Connection connection) throws SQLException {
        String selectDataSQL = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(selectDataSQL);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("ID\tName\tAge");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println(id + "\t" + name + "\t" + age);
            }
        }
    }
}

