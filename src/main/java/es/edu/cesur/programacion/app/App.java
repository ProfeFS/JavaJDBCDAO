package es.edu.cesur.programacion.app;

import java.util.List;

import es.edu.cesur.programacion.model.User;
import es.edu.cesur.programacion.servicios.UserService;

public class App {

	public static void main(String[] args) {
		UserService gestorU = new UserService();

		List<User> usuarios = gestorU.getAllUsers();
		usuarios.forEach(System.out::println);
		
		gestorU.clouseConn(); //nos aseguramos de que la conexión se cierre al finalizar la app.

	}
	
	/**
	 * el cierre de la conn lo hacemos porque hemos diseñado la clase DAO de forma que use la misma conn
	 * para todos los métodos.
	 * 
	 * si cada mètodo gestionara su propia conn no fue se necesario cerrarla de forma manual.
	 */

}
