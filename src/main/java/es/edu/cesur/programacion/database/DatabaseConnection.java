package es.edu.cesur.programacion.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.edu.cesur.programacion.config.ConfigManager;

public class DatabaseConnection {
	private static Connection connection = null;

	static String host = ConfigManager.getProperty("app.db.host");
	static String driver = ConfigManager.getProperty("app.db.driver");
	static String dbName = ConfigManager.getProperty("app.db.dbname");
	static String user = ConfigManager.getProperty("app.db.username");
	static String port = ConfigManager.getProperty("app.db.port");
	static String url = "jdbc:" + driver + "://" + host + ":" + port + "/" + dbName;
	static String password = ConfigManager.getProperty("app.db.password");
	// String url = "jdbc:postgresql://localhost:5432/db_users";

	// Constructor privado para prevenir la instanciación
	private DatabaseConnection() {
	}

	// Método público estático para obtener la instancia de la conexión
	public static Connection getConnection() {

		try {
			if (connection == null || connection.isClosed()) {
				// Registrando el driver JDBC, si es necesario (depende del entorno/JDK)
				DriverManager.registerDriver(new org.postgresql.Driver());
				// Estableciendo la conexión a la base de datos
				connection = DriverManager.getConnection(url, user, password);
			}
			return connection;

		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database", e);
		}

	}

	// Método para cerrar la conexión si es necesario (puede ser llamado al cerrar
	// la aplicación)
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null; // Asegúrate de reiniciar la conexión a null
			} catch (SQLException e) {
				throw new RuntimeException("Error closing the database connection", e);
			}
		}
	}
}