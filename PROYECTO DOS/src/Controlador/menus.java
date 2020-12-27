package Controlador;

import java.util.Iterator;

public class menus {
	//private static ArrayList<String> profesores = new ArrayList<String>();
	admin admin0 = new admin();
	
	public void menuProfesor() {
		int eleccion = 0;
		do {
			System.out.print("Que desea hacer?\n 1)Iniciar Sesion\n2)Volver\nELIJA: ");
			eleccion = Integer.parseInt(complementos.leerDeTeclado());
			while (eleccion > 2) {
				System.err.println("Opcion fuera de rango\n");
				System.out.print("Que desea hacer?\n 1)Ingresar\n2)Volver\nELIJA: ");
				eleccion = Integer.parseInt(complementos.leerDeTeclado());
			}
			switch (eleccion) {
			case 1: {
				System.out.println("Ingrese rut de profesor");
				String rut = complementos.leerDeTeclado();
				if (ingresoProfesor(rut)) {
					System.out.println("Bienvenido\n");

				} else {
					System.err.println("RUT INCORRECTO");
				}
				break;
			}
			}
		} while (eleccion != 2);

	}

	public static boolean ingresoProfesor(String rutIngresado) {
		Iterator<profesor> iterador = admin.getProfesores().iterator();
		while (iterador.hasNext()) {
			if (rutIngresado.equalsIgnoreCase(iterador.next().getRutProfesor())) {
				return true;
			}
		}
		return false;
	}
	
	public void menuAdmin() {
		boolean bucle = true;
		while(bucle) {
			System.out.println("INGRESE CLAVE DE ADMINISTRADOR");
			if(admin0.getClaveAdmin().equalsIgnoreCase(complementos.leerDeTeclado())) {
				bucle = false;
			}else {
				admin0.intentos--;
				System.err.println("CLAVE INCORRECTA intentos: "+admin0.intentos+"\n");
			}
			if (admin0.intentos<1) {
				return;
			}
		}
		int eleccion = 0;
		do {
			System.out.print("Que desea hacer?\n 1)Agregar profesores\n2)Ver profesores\n3)Volver\nELIJA: ");
			eleccion = Integer.parseInt(complementos.leerDeTeclado());
			while (eleccion > 3) {
				System.err.println("Opcion fuera de rango\n");
				System.out.print("Que desea hacer?\n 1)Agregar profesores\n2)Ver profesores\n3)Volver\nELIJA: ");
				eleccion = Integer.parseInt(complementos.leerDeTeclado());
			}
			switch (eleccion) {
			case 1: {
				admin0.agregarProfesore();
				break;
			}
			case 2:{
				admin0.verProfesores();
			}
			}
		} while (eleccion != 3);
		
	}
}
