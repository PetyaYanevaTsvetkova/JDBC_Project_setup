import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final String SEARCHED_NAME = "Mark";

    DatabaseConnection databaseConnection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<String> names = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        try {
            databaseConnection = DatabaseConnection.getInstance();
            String query = "SELECT * FROM customer WHERE name LIKE ?";
            preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, SEARCHED_NAME);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                names.add(resultSet.getString(2));
            }
            System.out.println(names);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @org.junit.jupiter.api.Test
    public void assertIfNameExists() {
        for (String name : names) {
            Assertions.assertEquals(name, SEARCHED_NAME);
        }
    }

    @AfterEach
    void afterEach() {
        try {
            preparedStatement.close();
            resultSet.close();
            databaseConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String query = "SELECT * FROM customer";
            preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
