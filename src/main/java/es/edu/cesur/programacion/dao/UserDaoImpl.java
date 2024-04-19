package es.edu.cesur.programacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.edu.cesur.programacion.database.DatabaseConnection;
import es.edu.cesur.programacion.model.User;

public class UserDaoImpl implements UserDao {

	private Connection conn; // Variable para mantener la conexión

	public UserDaoImpl() {
		this.conn = DatabaseConnection.getConnection(); // Inicializa la conexión cuando se crea una instancia del DAO
	}

	@Override
	public Integer addUser(User user) throws SQLException {
		String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
		Integer idcreado =null;

		try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, "Anna");
			insertStmt.setString(2, "anna@hola.com");
			int insertCount = insertStmt.executeUpdate();
			ResultSet rs = insertStmt.getGeneratedKeys();

			if (insertCount != 0) {
				System.out.println("se ha cerado el usuario en la bdd con id: " + rs.getInt("id"));
				idcreado = rs.getInt("id");				
			}
		}
		return idcreado;

	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		
		String querySelect = "SELECT * FROM users";
		PreparedStatement stmt = conn.prepareStatement(querySelect); //cargo la sentencia con la consula.
		ResultSet rs= stmt.executeQuery();
		
		while(rs.next()) {
			System.out.println("ID: " +  rs.getInt("id") + ", NOmbre: " + rs.getString("name"));
			User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
			users.add(u);
		}
		return users;
	}

	@Override
	public User getUserById(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
		PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
		updateStmt.setString(1, "Juana");
		updateStmt.setInt(2, 26);
		
		int updateCount = updateStmt.executeUpdate();
		System.out.println("Se han actualizado " + updateCount + " registros" );

	}

	@Override
	public void deleteUser(int userId) throws SQLException {
		String deleteSQL = "DELETE FROM users WHERE id = ?";
		PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
		deleteStmt.setInt(1, 25);
		int deleteCount = deleteStmt.executeUpdate();
		System.out.println("Se han eliminado " + deleteCount + " registros" );
	}
	
	

}
