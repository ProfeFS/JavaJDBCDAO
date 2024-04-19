package es.edu.cesur.programacion.dao;

import java.sql.SQLException;
import java.util.List;

import es.edu.cesur.programacion.model.User;

/*
 * Para implementar el patrón DAO (Data Access Object) para operaciones CRUD
 * en una base de datos PostgreSQL con JDBC, necesitas definir una interfaz DAO 
 * y una implementación concreta de esa interfaz. 
 */

public interface UserDao {

	Integer addUser(User user) throws SQLException; // Crea un nuevo usuario

	List<User> getAllUsers() throws SQLException; // Devuelve todos los usuarios

	User getUserById(int userId) throws SQLException; // Busca un usuario por su ID

	void updateUser(User user) throws SQLException; // Actualiza los datos de un usuario

	void deleteUser(int userId) throws Exception; // Elimina un usuario por su ID

}
