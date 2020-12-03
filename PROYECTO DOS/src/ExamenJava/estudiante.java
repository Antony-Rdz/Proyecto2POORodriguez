package ExamenJava;

import java.util.*;

public class estudiante implements Comparable <estudiante>{
	private String nombre;
	private int nota;
	private int preg_buenas;
	private int preg_malas;
	
	
	public estudiante(String nombre, int nota, int preg_buenas, int preg_malas) {
		this.nombre = nombre;
		this.nota = nota;
		this.preg_buenas = preg_buenas;
		this.preg_malas = preg_malas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getPreg_buenas() {
		return preg_buenas;
	}

	public void setPreg_buenas(int preg_buenas) {
		this.preg_buenas = preg_buenas;
	}

	public int getPreg_malas() {
		return preg_malas;
	}

	public void setPreg_malas(int preg_malas) {
		this.preg_malas = preg_malas;
	}

	public String toString() {
		return "Alumno: "+nombre+ " | Puntaje: "+nota+" | Preguntas Correctas: "+preg_buenas+" | Preguntas Incorrectas: "+preg_malas;
	}
	
	@Override
	public int compareTo(estudiante std) {
		if(std.getNota() > this.getNota()) {
			return 1;
		}else if(std.getNota() < this.getNota()) {
			return -1;
		}
		return 0;
	}
	
	@SuppressWarnings("unused")
	private class compararEstudiantes implements Comparator<estudiante>{
		 public int compare(estudiante estudiante1, estudiante estudiante2) {
		        return estudiante1.getNombre().compareToIgnoreCase(estudiante2.getNombre());
		 }
	}
	
	
}
