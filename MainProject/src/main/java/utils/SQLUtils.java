package utils;

import java.sql.*;

public class SQLUtils {

    public static int countDataAmountFromTable(String tableName, String... dataArgTypesToBeCounted) {
        try (Connection conn = DriverManager.getConnection(ApplicationProperties.JDBC_URL)) {
            String query = "SELECT COUNT(*) FROM [ProjectDB].[dbo].[" + tableName + "]";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
