package ExamenJava;

import java.util.ArrayList;
import java.util.Iterator;

public class admin {
	
	
	private String claveAdmin = "melopomelo";
	public int intentos = 3;
	private static ArrayList<profesor> profesores = new ArrayList<profesor>();

	
	public String getClaveAdmin() {
		return claveAdmin;
	}


	public void setClaveAdmin(String claveAdmin) {
		this.claveAdmin = claveAdmin;
	}


	public int getIntentos() {
		return intentos;
	}


	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}


	public static ArrayList<profesor> getProfesores() {
		return profesores;
	}


	public void setProfesores(ArrayList<profesor> profesores) {
		this.profesores = profesores;
	}

	public void agregarProfesore() {
		boolean bucle = true;
		while(bucle) {
			System.out.print("Ingrese un RUT para agregar a la lista de Profesores: ");
			String rutProfesor = complementos.leerDeTeclado();
			if(profesorExiste(rutProfesor)) {
				System.out.println("El RUT ingresado ya existe");
			}else {
				profesores.add(new profesor(rutProfesor));
				System.out.println("RUT añadido correctamente\n");
				System.out.print("Quiere agregar otro profesor?(si / no): ");
				String respuesta = complementos.leerDeTeclado().toUpperCase();
				if(respuesta.equalsIgnoreCase("NO") || respuesta.equalsIgnoreCase("SI")) {
					bucle = false;
				}else {
					System.err.println("Respuesta no valida");
				}
			}	
		}
	}
	
	public void verProfesores() {
		System.out.print("\n");
		for (profesor std : profesores) {
			System.out.println(std);
		}
		System.out.print("\n");
	}
	
	public boolean profesorExiste(String rutProfesor) {	
		Iterator<profesor> iterador = profesores.iterator();
		while (iterador.hasNext()) {
			if (rutProfesor.equalsIgnoreCase(iterador.next().getRutProfesor())) {
				return true;
			}
		}
		return false;
	}
	
	
}
