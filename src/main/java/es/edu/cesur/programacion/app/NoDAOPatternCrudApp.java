package es.edu.cesur.programacion.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoDAOPatternCrudApp {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/db_users";
		String user = "postgres";
		String pass = "1234";

		// Queries para los statements
		String querySelect = "SELECT * FROM users";

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			if (conn != null) {
				System.out.println("Conectado con éxito");
			}

			// CRUD
			// 1 Read
			PreparedStatement stmt = conn.prepareStatement(querySelect); //cargo la sentencia con la consula.
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " +  rs.getInt("id") + ", NOmbre: " + rs.getString("name"));
			}
			
			// 2 Create
			
			String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
			PreparedStatement insertStmt = conn.prepareStatement(insertSQL); //cargo la sentencia con la consula.
			insertStmt.setString(1, "Anna");
			insertStmt.setString(2, "anna@hola.com");

			//int insertCount = insertStmt.executeUpdate();
			//System.out.println("Registros agregados: " + insertCount);
			
		
			//UPDATE
			String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
			updateStmt.setString(1, "Juana");
			updateStmt.setInt(2, 26);
			
//			int updateCount = updateStmt.executeUpdate();
//			System.out.println("Se han actualizado " + updateCount + " registros" );
			
			//DELETE
			String deleteSQL = "DELETE FROM users WHERE id = ?";
			PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
			deleteStmt.setInt(1, 25);
			int deleteCount = deleteStmt.executeUpdate();
			System.out.println("Se han eliminado " + deleteCount + " registros" );
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
