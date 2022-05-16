import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainTryWithRes {
    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

