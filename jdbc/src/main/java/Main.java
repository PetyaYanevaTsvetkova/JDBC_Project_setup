import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection databaseConnection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            databaseConnection = DatabaseConnection.getInstance();

            statement = databaseConnection.getConnection().createStatement();
            String query = "SELECT * FROM customer";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                statement.close();
                resultSet.close();
                databaseConnection.getConnection().close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            statement = databaseConnection.getConnection().createStatement();
            String query = "SELECT * FROM customer";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
