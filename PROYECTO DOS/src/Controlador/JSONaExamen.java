package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONaExamen {
	
	StringBuilder texto = new StringBuilder();
	JSONObject preg;
	JSONObject smtt;
	JSONObject tftt;
	JSONObject rctt;
	JSONArray selmul;
	JSONArray tf;
	JSONArray rc;
	
	JSONObject jsonAlumno;
	
	int numeroDeLinea = 1;
	byte[] base64Decoded;

	public Exam generaExamen(File ubicacion) throws FileNotFoundException {
		Exam examen = new Exam();	
		Scanner scan = new Scanner(ubicacion);
		while (scan.hasNext()) {
			String text = scan.nextLine();
			if(!validaExamen(text)) {
				throw new IllegalArgumentException("Archivo modificado");
			}
			base64Decoded = DatatypeConverter.parseBase64Binary(text);
			String textoConvertido = new String(base64Decoded);
			
			switch (numeroDeLinea) {
			case 1: {
				preg = new JSONObject(textoConvertido);
				numeroDeLinea++;
				break;
			}
			case 2: {
				selmul = new JSONArray(textoConvertido);
				numeroDeLinea++;
				break;
			}
			case 3: {
				tf = new JSONArray(textoConvertido);
				numeroDeLinea++;
				break;
			}
			case 4: {
				rc = new JSONArray(textoConvertido);
				numeroDeLinea = 1;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + numeroDeLinea);
			}
			
		}
		examen.setTiempo(preg.getInt("Tiempo"));
		examen.setTotalPreguntas(preg.getInt("TotalPreguntas"));
		
		for (int i = 0; i < selmul.length(); i++) {
			JSONArray arr = selmul.getJSONObject(i).getJSONObject("Seleccion Multiple " + (i + 1)).getJSONArray("StrRespuesta");
			String[] alternativas = new String[arr.length()];
			for (int j = 0; j < arr.length(); j++) {
				alternativas[j] = arr.getString(j);
			}
			examen.agregaPregunta(new Selec_Mul_Pregunta(selmul.getJSONObject(i).getJSONObject("Seleccion Multiple " + (i + 1)).getString("Enunciado"), alternativas, selmul.getJSONObject(i).getJSONObject("Seleccion Multiple " + (i + 1)).getInt("IndexCorrecto"), selmul.getJSONObject(i).getJSONObject("Seleccion Multiple " + (i + 1)).getInt("peso")));
		}
		
		for (int i = 0; i < tf.length(); i++) {
			examen.agregaPregunta(new TFpreguntas(tf.getJSONObject(i).getJSONObject("Verdadero o Falso " + (i + 1)).getString("Enunciado"), tf.getJSONObject(i).getJSONObject("Verdadero o Falso " + (i + 1)).getBoolean("Respuesta"), tf.getJSONObject(i).getJSONObject("Verdadero o Falso " + (i + 1)).getInt("Peso")));
		}
		
		for (int i = 0; i < rc.length(); i++) {
			examen.agregaPregunta(new Resp_Cortas_Pregunta(rc.getJSONObject(i).getJSONObject("Respuesta Corta " + (i + 1)).getString("Enunciado"), rc.getJSONObject(i).getJSONObject("Respuesta Corta " + (i + 1)).getString("Respuesta"), rc.getJSONObject(i).getJSONObject("Respuesta Corta " + (i + 1)).getInt("Peso")));
		}
		return examen;
	}
	public static void main(String[] args) throws FileNotFoundException {
		//JSONObject jsoAlumno = new JSONObject("{\"puntajeUsuario\":[0,0,4],\"respuestaUsuario\":[\"sss\",\"Falso\",\"hola\"],\"respuestaExamen\":[\"ddd\",\"Verdadero\",\"hola\"],\"puntajeExamen\":[12,15,4]}");
		//System.out.println(jsoAlumno.getJSONArray("puntajeUsuario").getInt(0));
		JSONaExamen a = new JSONaExamen();
		//a.generaResultadoExam(new File("C:\\Users\\Anton\\git\\Proyecto2POORodriguez\\PROYECTO DOS\\Profesor\\Notas\\Notas Gabriel Ortega\\Notas Examen examenDePruebaNUEVO\\antonio rodriguez.json"));
		System.out.println(a.getEstadoExamen(new File("C:\\Users\\Anton\\git\\Proyecto2POORodriguez\\PROYECTO DOS\\Profesor\\Notas\\Notas Gabriel Ortega\\Notas Examen holamundo\\antonio rodriguez.json")));
	}
		
	public ModeloNotasAlumno generaResultadoExam(File ubicacion) throws FileNotFoundException{
		ModeloNotasAlumno alumno = new ModeloNotasAlumno();
		Scanner scan = new Scanner(ubicacion);
		while (scan.hasNext()) {
			String text = scan.nextLine();
			base64Decoded = DatatypeConverter.parseBase64Binary(text);
			String textoConvertido = new String(base64Decoded);
			jsonAlumno = new JSONObject(textoConvertido);
		}
		
		JSONArray arr = jsonAlumno.getJSONArray("puntajeUsuario");
		int[] puntajeA = new int[arr.length()];
		for(int i = 0; i < arr.length(); i++) {
			puntajeA[i] = arr.getInt(i);
			System.out.println(puntajeA[i] );
		}
		System.out.println();
		
		JSONArray arr2 = jsonAlumno.getJSONArray("puntajeExamen");
		int[] puntajeE = new int[arr2.length()];
		for (int i = 0; i < arr2.length(); i++) {
			puntajeE[i] = arr2.getInt(i);
			System.out.println(puntajeE[i]);
		}
		System.out.println();
		JSONArray arr3 = jsonAlumno.getJSONArray("respuestaUsuario");
		String[] respuestaA = new String[arr3.length()];
		for (int i = 0; i < arr3.length(); i++) {
			respuestaA[i] = arr3.getString(i);
			System.out.println(respuestaA[i]);
		}
		System.out.println();
		
		JSONArray arr4 = jsonAlumno.getJSONArray("respuestaExamen");
		String[] respuestaE = new String[arr4.length()];
		for (int i = 0; i < arr4.length(); i++) {
			respuestaE[i] = arr4.getString(i);
			System.out.println(respuestaE[i]);
		}
		System.out.println();
		
		alumno.setPuntajeAlumno(puntajeA);
		alumno.setPuntajeExamen(puntajeE);
		alumno.setRespuestaAlumno(respuestaA);
		alumno.setRespuestaExamen(respuestaE);
			
		return alumno;
	}
	
	public String getEstadoExamen(File ubicacion) throws FileNotFoundException {
		Scanner scan = new Scanner(ubicacion);
		while (scan.hasNext()) {
			String text = scan.nextLine();
			base64Decoded = DatatypeConverter.parseBase64Binary(text);
			String textoConvertido = new String(base64Decoded);
			jsonAlumno = new JSONObject(textoConvertido);
		}
		
		//System.out.println(jsonAlumno.getString("estadoExamen"));
		return jsonAlumno.getString("estadoExamen");
		//return null;
	}
	
	
	public static boolean validaExamen(String examen) {
		return examen.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$");

	}
}
