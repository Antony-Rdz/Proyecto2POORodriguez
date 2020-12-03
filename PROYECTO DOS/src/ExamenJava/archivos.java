package ExamenJava;

import java.io.*;

/**
 * Clase que facilita la lectura y escritura en archivos.
 * 
 * @author Antony
 *
 */
public class archivos {

	/**
	 * Modulo que lee el archivo donde se encuentra
	 * la tabla de puntuaciones. 
	 *
	 * @param direccion Ubicacion del archivo.
	 */
	public void leerArchivo(File direccion) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(direccion));
			String texto = "";
			String bfRead = null;
			while ((bfRead = bf.readLine()) != null) {
				texto = texto + bfRead + "\n";
			}
			System.out.println("\n"+texto);
			bf.close();
		} catch (Exception e) {
			System.out.println("No se encontró archivo..");
		}
	}

	/**
	 * Escribe los resultados del examen en un archivo de texto.
	 * 
	 * @param direccion	Ubicacion del archivo.
	 * @param nombre	Nombre del usuario.
	 * @param puntaje	Resultado de su examen, "0" si no lo hizo.
	 * @param caso		"1" Si completo el examen "2" si no lo logro.
	 */
	public void escribirArchivo(File direccion, String nombre, int puntaje,int caso) {
		try {
			if (!direccion.exists()) { //Si el archivo no existe crea el archivo y escribe el encabezado de la tabla.
				direccion.createNewFile();
				BufferedWriter archivoEscribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(direccion, true), "utf-8"));
				archivoEscribe.write("| NOMBRE | PUNTAJE | CORRECTAS | INCORRECTAS |" + "\r\n");
				archivoEscribe.write("---------+---------+-----------+--------------" + "\r\n");
				archivoEscribe.close();
			}
			switch (caso) {
				case 1: {	// 1 Si logró completar el examen.
					BufferedWriter archivoEscribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(direccion, true), "utf-8"));
					archivoEscribe.write("| "+nombre + " |  " + puntaje + "% "+" |   "+Exam.preg_buenas+"   |   "+Exam.preg_malas+"   |"+"\r\n");
					archivoEscribe.close();
					break;
				}
				case 2: {	// 2 Si no completo el examen y se gastaron sus intentos.
					BufferedWriter archivoEscribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(direccion, true), "utf-8"));
					archivoEscribe.write("| "+nombre+" | ----NO COMPLETO " + Exam.preg_sinTerminar+ " DE "+Exam.getTotalPreguntas()+"----"+"\r\n");
					archivoEscribe.close(); 
					break;
				}
				default:
					throw new IllegalArgumentException("Valor inesperado: " + caso);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
