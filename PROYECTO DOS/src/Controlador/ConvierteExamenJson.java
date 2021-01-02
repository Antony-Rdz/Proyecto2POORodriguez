package Controlador;

import java.io.File;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvierteExamenJson {

	JSONObject Pregunta;
	byte[] bytes;
	String codificado;
	
	JSONArray SM = new JSONArray();
	JSONArray TF = new JSONArray(); 
	JSONArray RC = new JSONArray();
	
	JSONObject selecMult;
	JSONObject verdFalso;
	JSONObject respCorta;
	
	JSONObject resultadoAlumno;
	
	JSONArray arrRtaUser;
	JSONArray arrRtaExam;
	JSONArray arrPuntaje;
	JSONArray arrPtjExam;

	archivos archivos;

	JSONArray alternativasSelecMul;
	int totalPreguntasSelecMul = 1;
	int totalPreguntasVerdFals = 1;
	int totalPreguntasRespCort = 1;

	/**
	 * 
	 * Guarda la informacion del examen, el tiempo, el total de preguntas 
	 * y la forma de este en el archivo
	 * 
	 * @param direccion
	 * @param Tiempo
	 * @param totalPreguntas
	 * @param formaExamen
	 */
	public void guardarenArchivo(String direccion, String Tiempo,int totalPreguntas,int formaExamen) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		int time;
		if(Tiempo.equals("")) {
			time = -1;
		}else {
			time = Integer.parseInt(Tiempo);
		}
		Pregunta.put("formaExamen", formaExamen);
		Pregunta.put("TotalPreguntas", totalPreguntas);
		Pregunta.put("Tiempo", time);
		
		bytes = Pregunta.toString().getBytes();
		codificado = DatatypeConverter.printBase64Binary(bytes);
		
		archivos.escribirArchivo(new File(direccion+".json"), codificado);
	}
	
	/**
	 * Guarda la pregunta en un arreglo json para despues guardarla en 
	 * un archivo.
	 * 
	 * @param direccion
	 * @param pregunta
	 * @param nombreDelArchivo
	 */
	public void guardarenArreglo(String direccion, Selec_Mul_Pregunta pregunta, String nombreDelArchivo) {
		Pregunta = new JSONObject();
		selecMult = new JSONObject();
		alternativasSelecMul = new JSONArray();

		for (int i = 0; i < pregunta.getopciones().length; i++) {
			alternativasSelecMul.put(pregunta.getopciones()[i]);
		}

		
		selecMult.put("Enunciado", pregunta.getEnunciado());
		selecMult.put("StrRespuesta", alternativasSelecMul);
		selecMult.put("RespCorrecta", pregunta.getRespuestaCorecta());
		selecMult.put("IndexCorrecto", pregunta.getIndex_respuesta());
		selecMult.put("peso", pregunta.getPeso());

		Pregunta.put("Seleccion Multiple " + Integer.toString(totalPreguntasSelecMul), selecMult);
		SM.put(Pregunta);
		totalPreguntasSelecMul++;
	}

	/**
	 * Guarda la pregunta en un arreglo json para despues guardarla en 
	 * un archivo.
	 * 
	 * @param direccion
	 * @param pregunta
	 * @param nombreDelArchivo
	 */
	public void guardarenArreglo(String direccion, TFpreguntas pregunta, String nombreDelArchivo) {
		Pregunta = new JSONObject();
		verdFalso = new JSONObject();

		verdFalso.put("Enunciado", pregunta.getEnunciado());
		verdFalso.put("Respuesta", pregunta.getRespuestaTF());
		verdFalso.put("Peso", pregunta.getPeso());

		Pregunta.put("Verdadero o Falso " + Integer.toString(totalPreguntasVerdFals), verdFalso);
		TF.put(Pregunta);
		totalPreguntasVerdFals++;
	}

	/**
	 * Guarda la pregunta en un arreglo json para despues guardarla en 
	 * un archivo.
	 * @param direccion
	 * @param pregunta
	 * @param nombreDelArchivo
	 */
	public void guardarenArreglo(String direccion, Resp_Cortas_Pregunta pregunta, String nombreDelArchivo) {
		Pregunta = new JSONObject();
		respCorta = new JSONObject();

		respCorta.put("Enunciado", pregunta.getEnunciado());
		respCorta.put("Respuesta", pregunta.getRespuestaCorta());
		respCorta.put("Peso", pregunta.getPeso());

		Pregunta.put("Respuesta Corta " + Integer.toString(totalPreguntasRespCort), respCorta);
		RC.put(Pregunta);
		totalPreguntasRespCort++;
	}
	
	/**
	 * 
	 * Guarda en un archivo todas las preguntas generadas posteriormente con los "guardaenArreglo()"
	 * 
	 * @param direccion
	 */
	public void guardaArregloEnArchivo(String direccion) {
		bytes = SM.toString().getBytes();
		codificado = DatatypeConverter.printBase64Binary(bytes);
		archivos.escribirArchivo(new File(direccion+".json"), codificado);
		
		bytes = TF.toString().getBytes();
		codificado = DatatypeConverter.printBase64Binary(bytes);
		archivos.escribirArchivo(new File(direccion+".json"), codificado);
		
		bytes = RC.toString().getBytes();
		codificado = DatatypeConverter.printBase64Binary(bytes);
		archivos.escribirArchivo(new File(direccion+".json"), codificado);
	}

	/**
	 * 
	 * Guarda la nota de cada alumno que da el examen
	 * en earchivos unitarios con su respectivo nombre
	 * 
	 * @param notas
	 * @param notasExam
	 * @param respuestasUsuario
	 * @param respuestasExam
	 * @param direccion
	 */
	public void guardaNotasAlumno(int[]notas,int[]notasExam, String[]respuestasUsuario,String[]respuestasExam,String direccion) {
		archivos = new archivos();	
		resultadoAlumno = new JSONObject();
		arrRtaUser = new JSONArray();
		arrRtaExam = new JSONArray();
		arrPuntaje = new JSONArray();
		arrPtjExam = new JSONArray();
		
		for (int i : notas) {
			arrPuntaje.put(i);
		}
		for (int i : notasExam) {
			arrPtjExam.put(i);
		}
		for (String string : respuestasUsuario) {
			arrRtaUser.put(string);
		}
		for (String string : respuestasExam) {
			arrRtaExam.put(string);
		}
		
		resultadoAlumno.put("respuestaUsuario", arrRtaUser);
		resultadoAlumno.put("respuestaExamen", arrRtaExam);
		resultadoAlumno.put("puntajeUsuario", arrPuntaje);
		resultadoAlumno.put("puntajeExamen", arrPtjExam);
		resultadoAlumno.put("estadoExamen", "Resuelto");
		
		bytes = resultadoAlumno.toString().getBytes();
		codificado = DatatypeConverter.printBase64Binary(bytes);
		archivos.escribirArchivo(new File(direccion+".json"), codificado);
	}
}
