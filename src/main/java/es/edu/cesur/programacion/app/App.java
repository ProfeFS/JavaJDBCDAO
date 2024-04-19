package es.edu.cesur.programacion.app;

import java.util.List;

import es.edu.cesur.programacion.model.User;
import es.edu.cesur.programacion.servicios.UserService;

public class App {

	public static void main(String[] args) {
		UserService gestorU = new UserService();
		
		List<User> usuarios = gestorU.getAllUsers();		
		usuarios.forEach(System.out::println);

	}

}
