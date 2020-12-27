package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

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
	public String leerArchivo(File direccion) {
		String texto = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(direccion));
			
			String bfRead = null;
			while ((bfRead = bf.readLine()) != null) {
				texto = texto + bfRead + "\n";
			}
			bf.close();
		} catch (Exception e) {
			System.out.println("No se encontró archivo..");
		}
		return texto;
	}


	public void escribirArchivo(File direccion, String pregunta) {

		try {
			if (!direccion.exists()) { // Si el archivo no existe crea el archivo.
				direccion.createNewFile();
			}
			BufferedWriter archivoEscribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(direccion, true), "utf-8"));
			archivoEscribe.write(pregunta + "\n");
			archivoEscribe.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
