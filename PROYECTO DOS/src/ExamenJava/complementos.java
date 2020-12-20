package ExamenJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Clase que brinda utilidades para el codigo.
 * 
 * @author Antony
 *
 */
public class complementos {
	/**
	 * Modulo de presentacion antes del examen. Pide un nombre al usuario y le
	 * explica las instrucciones
	 */
	@SuppressWarnings("resource")
	public void presentacion() {
		Boolean aux = true;
		System.out.print("\n\tBienvenido al Examen\n\n");
		System.out.print("Ingrese su nombre para continuar: ");
		while (aux){
			Exam.nombre = leerDeTeclado();
			char[] name = Exam.nombre.toCharArray();
			name[0] = Character.toUpperCase(name[0]);
			Exam.nombre = new String(name);
			if (esSoloLetras(Exam.nombre)) {
				aux = false;
			}else {
				System.out.print("El nombre tiene caracteres que NO son letras\nIngrese su nombre nuevamente: ");
			}	
		}
		//System.out.println("\n" +Exam.nombre+", el examen consiste de " + Exam.getTotalPreguntas()+ " preguntas.\n\n\"Verdadero o falso\" \n\"Selección multiple\" \n\"Respuesta corta\" \n\nCada respuesta correcta equivale a 1 punto,\nsiendo " + Exam.getTotalPreguntas()+ " el puntaje maximo.\nAl completar el examen se le entregara su resultado,\neste, será expresado en forma de porcentaje\ny será guardado para analizarlos posteriormente.");
		System.out.println("\nPresione \"Enter\" para iniciar");
		new Scanner(System.in).nextLine(); // Linea vacia;
	}

	/**
	 * Modulo que despide al usuario, bien si termino el examen o no.
	 * 
	 * @param puntaje Valor numerico obtenido tras realizar el examen.
	 * @param caso    Si es 1 es que completo el examen, 2 si no lo completó.
	 */
	public void despedida(int puntaje, int caso) {
		archivos tabla = new archivos();
		String opcion = "";
		tabla.escribirArchivo(new File("C:\\Users\\Anton\\eclipse-workspace\\Proyecto Uno\\src\\Resultados Examen\\Score.txt"),Exam.nombre, puntaje, caso); // Escribe en el archivo los datos
		System.out.print("\nTabla de resultados(1)\nSalir(0)\n"); // dependiendo si terminó el examen o no.
		System.out.print("_______________________\n");
		System.out.print("Escoja: ");
		boolean aux = true;
		while (aux) {
			opcion = leerDeTeclado();// Leo la opcion del usuario.
			switch (opcion) {
			case "1": { // 1 Si desea ver la tabla
				tabla.leerArchivo(new File("C:\\Users\\Anton\\eclipse-workspace\\Proyecto Uno\\src\\Resultados Examen\\Score.txt"));
				return;
			}
			case "0": { // 0 Si desea salir del programa.
				System.err.println("\nAdios....");
				System.exit(0);
			}
			default: // Por si es que ingresa valores que no van al caso.
				System.err.print("Ingrese SOLO '1' o '0': ");
			}
		}
	}

	/**
	 * Modulo que ayuda a facilitar la lectura desde teclado.
	 * 
	 * @return String
	 */
	public static String leerDeTeclado() {
		BufferedReader auxiliar = new BufferedReader(new InputStreamReader(System.in));
		String teclado = "";
		boolean aux = true;
		while (aux) {
			try {
				teclado = auxiliar.readLine();
				if ((teclado != null) && !teclado.isEmpty()) {
					aux = false;
				} else if (teclado == null) { // por si el usuario ingresa "Ctrl + z"
					System.err.println("Saliendo del programa...");
					System.exit(0);
				} else { // Por si el usuario presiona "Enter"
					System.err.print("Ingrese texto valido: ");
				}
			} catch (IOException e) { 
				System.err.println("Entrada no esperada...");
			}

		}
		return teclado;
	}
	
	/**
	 * Metodo para comprobar si los caracteres introducidos en una cadena son
	 * SOLO letras o no.
	 * @param cadena String a comprobar
	 * @return true si solo contiene letras, false en caso contrario
	 */
	public boolean esSoloLetras(String cadena){
		//Recorremos cada caracter de la cadena y comprobamos si son letras.
		//Para comprobarlo, lo pasamos a mayuscula y consultamos su numero ASCII.
		//Si está fuera del rango 65 - 90, es que NO son letras.
		//Para ser más exactos al tratarse del idioma español, tambien comprobamos
		//el valor 165 equivalente a la Ñ
		for (int i = 0; i < cadena.length(); i++){
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return false; //Se ha encontrado un caracter que no es letra
		}
		//Terminado el bucle sin que se haya retornado false, es que todos los caracteres son letras
		return true;
	}
}
