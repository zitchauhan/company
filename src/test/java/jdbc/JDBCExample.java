package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class JDBCExample {

	@Test
	public void jdbcConnection() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// JDBC URL for connecting to the database
			String url = "jdbc:sqlserver://localhost:1433;databaseName=jdbcTest;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

			// Database credentials
			String username = "ssms";
			String password = "ssms";

			// Establish the connection
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successful!");

			// Create a statement object
			statement = connection.createStatement();

			// Execute the SQL query
			resultSet = statement.executeQuery("SELECT * FROM LoginPageElements");

			/*
			 * List<ResultSet> rs = new ArrayList<>(); rs.add(resultSet);
			 * 
			 * System.out.println(rs);
			 * 
			 */

			// Process the result set
			while (resultSet.next()) {
				// Retrieve data from the result set
				String column1Value = resultSet.getString("LocatorType");
				System.out.println("LocatorType: " + column1Value);

				String column2Value = resultSet.getString("LocatorValue");
				System.out.println("LocatorValue: " + column2Value);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection, statement, and result set
			try {
				if (resultSet != null)
					resultSet.close();

				if (statement != null)
					statement.close();

				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
