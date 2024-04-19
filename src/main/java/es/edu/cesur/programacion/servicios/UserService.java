package es.edu.cesur.programacion.servicios;

import java.sql.SQLException;
import java.util.List;

import es.edu.cesur.programacion.dao.UserDao;
import es.edu.cesur.programacion.dao.UserDaoImpl;
import es.edu.cesur.programacion.database.DatabaseConnection;
import es.edu.cesur.programacion.model.User;

/**
 * Para ilustrar cómo se puede utilizar la implementación UserDAOImpl en la
 * lógica de negocio, vamos a crear una clase de servicio denominada
 * UserService. Esta clase servirá como intermediaria entre la capa de
 * presentación de tu aplicación (por ejemplo, una interfaz de usuario o una
 * API) y la capa de acceso a datos proporcionada por UserDAOImpl. La clase
 * UserService encapsulará la lógica de negocio relacionada con los usuarios,
 * permitiendo realizar operaciones como registrar un nuevo usuario, buscar
 * usuarios, actualizar datos de un usuario, y eliminar usuarios, realizar
 * validaciones, manejo de excepciones y cualquier otra opetación relacionada
 * con el negocio.
 * 
 */
public class UserService {
	private UserDao userDAO;

	public UserService() {
		// Inicialización del DAO. Podría inyectarse también si estás usando algún
		// framework de inyección de dependencias.
		this.userDAO = new UserDaoImpl();
	}

	// Registra un nuevo usuario
	public void registerUser(User user) {
		try {
			// Aquí podrías agregar lógica de negocio adicional, como validación de datos.
			userDAO.addUser(user);
			System.out.println("Usuario registrado con éxito: " + user.getName());
		} catch (SQLException e) {
			System.out.println("Error al registrar el usuario: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error al registrar el usuario: " + e.getMessage());
		}
	}

	// Obtiene la lista de todos los usuarios
	public List<User> getAllUsers() {
		try {
			return userDAO.getAllUsers();
		} catch (Exception e) {
			System.out.println("Error al obtener los usuarios: " + e.getMessage());
			return null;
		}
	}

	// Busca un usuario por su ID
	public User getUserById(int userId) {
		try {
			return userDAO.getUserById(userId);
		} catch (Exception e) {
			System.out.println("Error al buscar el usuario con ID " + userId + ": " + e.getMessage());
			return null;
		}
	}

	// Actualiza los datos de un usuario
	public void updateUser(User user) {
		try {
			userDAO.updateUser(user);
			System.out.println("Usuario actualizado con éxito: " + user.getName());
		} catch (Exception e) {
			System.out.println("Error al actualizar el usuario: " + e.getMessage());
		}
	}

	// Elimina un usuario por su ID
	public void deleteUser(int userId) {
		try {
			userDAO.deleteUser(userId);
			System.out.println("Usuario eliminado con éxito.");
		} catch (Exception e) {
			System.out.println("Error al eliminar el usuario: " + e.getMessage());
		}
	}
	
	public void clouseConn() {
		DatabaseConnection.closeConnection();
	}

}
