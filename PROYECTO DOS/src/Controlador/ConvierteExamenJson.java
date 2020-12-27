package Controlador;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvierteExamenJson {

	JSONObject Pregunta;

	JSONArray SM = new JSONArray();
	JSONArray TF = new JSONArray(); 
	JSONArray RC = new JSONArray();
	
	JSONObject selecMult;
	JSONObject verdFalso;
	JSONObject respCorta;

	archivos archivos;

	JSONArray alternativasSelecMul;
	int totalPreguntasSelecMul = 1;
	int totalPreguntasVerdFals = 1;
	int totalPreguntasRespCort = 1;

	public static void main(String[] args) {
		JSONArray selmul = new JSONArray("[{\"Seleccion Multiple 1\":{\"Enunciado\":\"direccion+\\\"\\\\\\\\\\\"+ProfesorGUI.nombreDelArchivo\",\"IndexCorrecto\":0,\"StrRespuesta\":[\"sadfsad\",\"asdfasdf\",\"asdfs\"],\"peso\":12,\"RespCorrecta\":\"sadfsad\"}},{\"Seleccion Multiple 2\":{\"Enunciado\":\"dfsgfdg\",\"IndexCorrecto\":0,\"StrRespuesta\":[\"sdfgsdfg\",\"sdfgsdfg\",\"sdfgsdfg\"],\"peso\":2,\"RespCorrecta\":\"sdfgsdfg\"}},{\"Seleccion Multiple 3\":{\"Enunciado\":\"sdfgdfsgsdf\",\"IndexCorrecto\":2,\"StrRespuesta\":[\"sdfgsdfg\",\"dsfgsdfgdsfg\",\"dfgsdfg\"],\"peso\":2,\"RespCorrecta\":\"dfgsdfg\"}},{\"Seleccion Multiple 4\":{\"Enunciado\":\"jhtgjhtyjkyu\",\"IndexCorrecto\":0,\"StrRespuesta\":[\"tyuityi\",\"yuityui\",\"uytui\"],\"peso\":2,\"RespCorrecta\":\"tyuityi\"}},{\"Seleccion Multiple 5\":{\"Enunciado\":\"hgfjhgn\",\"IndexCorrecto\":4,\"StrRespuesta\":[\"gfngfhn\",\"fghngfnghn\",\"gfhnkloi\",\"sdfgsdfg\",\"dsgfsdfg\"],\"peso\":2,\"RespCorrecta\":\"dsgfsdfg\"}}]");
		JSONArray tf = new JSONArray("[{\"Verdadero o Falso 1\":{\"Enunciado\":\"gsdfgdsfg\",\"Peso\":2,\"Respuesta\":true}},{\"Verdadero o Falso 2\":{\"Enunciado\":\"sdfgdshf\",\"Peso\":3,\"Respuesta\":true}},{\"Verdadero o Falso 3\":{\"Enunciado\":\"jhkhgjkuyi\",\"Peso\":3,\"Respuesta\":false}},{\"Verdadero o Falso 4\":{\"Enunciado\":\"iou\",\"Peso\":2,\"Respuesta\":true}}]");
		JSONArray rc = new JSONArray("[{\"Respuesta Corta 1\":{\"Enunciado\":\"dgsfsdfg\",\"Peso\":1,\"Respuesta\":\"sdfgsdfg\"}},{\"Respuesta Corta 2\":{\"Enunciado\":\"sdfgsdfg\",\"Peso\":3,\"Respuesta\":\"dsgfsdfg\"}},{\"Respuesta Corta 3\":{\"Enunciado\":\"ghjgfjg\",\"Peso\":2,\"Respuesta\":\"ghfjgfh\"}}]");
		
		System.out.println("Selec Multi:");
		for (int i = 0; i < selmul.length(); i++) {
			System.out.println("enunciado: "+selmul.getJSONObject(i).getJSONObject("Seleccion Multiple "+(i+1)).getString("Enunciado"));
			System.out.println("index: "+selmul.getJSONObject(i).getJSONObject("Seleccion Multiple "+(i+1)).getInt("IndexCorrecto"));
			JSONArray arr = selmul.getJSONObject(i).getJSONObject("Seleccion Multiple "+(i+1)).getJSONArray("StrRespuesta");
			System.out.println("Opciones:");
			for (int j = 0; j < arr.length(); j++) {
				System.out.println(arr.getString(j));
			}
			System.out.println("peso: "+selmul.getJSONObject(i).getJSONObject("Seleccion Multiple "+(i+1)).getInt("peso"));
			System.out.println("RespCorrecta: "+selmul.getJSONObject(i).getJSONObject("Seleccion Multiple "+(i+1)).getString("RespCorrecta"));
			System.out.println("\n");
		}	
		System.out.println("\n");
		
		System.out.println("verdadero falso:");
		for (int i = 0; i < tf.length(); i++) {
			System.out.println("enunciado: "+tf.getJSONObject(i).getJSONObject("Verdadero o Falso "+(i+1)).getString("Enunciado"));
			System.out.println("respuesta: "+tf.getJSONObject(i).getJSONObject("Verdadero o Falso "+(i+1)).getBoolean("Respuesta"));
			System.out.println("respuesta: "+tf.getJSONObject(i).getJSONObject("Verdadero o Falso "+(i+1)).getInt("Peso"));
			System.out.println("\n");
		}
		System.out.println("\n");
		
		System.out.println("Respuesta Corta:");
		for (int i = 0; i < rc.length(); i++) {
			System.out.println("enunciado: "+rc.getJSONObject(i).getJSONObject("Respuesta Corta "+(i+1)).getString("Enunciado"));
			System.out.println("respuesta: "+rc.getJSONObject(i).getJSONObject("Respuesta Corta "+(i+1)).getString("Respuesta"));
			System.out.println("respuesta: "+rc.getJSONObject(i).getJSONObject("Respuesta Corta "+(i+1)).getInt("Peso"));
			System.out.println("\n");
		}
		System.out.println("\n");	
	}
	
	
	public void guardarenArchivo(String direccion, String Tiempo,int totalPreguntas) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		int time;
		if(Tiempo.equals("")) {
			time = -1;
		}else {
			time = Integer.parseInt(Tiempo);
		}
		
		Pregunta.put("TotalPreguntas", totalPreguntas);
		Pregunta.put("Tiempo", time);
		
		archivos.escribirArchivo(new File(direccion+".json"), Pregunta.toString());
	}
	
	public void guardarenArchivo(String direccion, Selec_Mul_Pregunta pregunta, String nombreDelArchivo) {
		archivos = new archivos();
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
		
		//archivos.escribirArchivo(new File(direccion + "\\" + nombreDelArchivo + ".json"), Pregunta.toString());
		totalPreguntasSelecMul++;
	}

	public void guardarenArchivo(String direccion, TFpreguntas pregunta, String nombreDelArchivo) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		verdFalso = new JSONObject();

		verdFalso.put("Enunciado", pregunta.getEnunciado());
		verdFalso.put("Respuesta", pregunta.getRespuestaTF());
		verdFalso.put("Peso", pregunta.getPeso());

		Pregunta.put("Verdadero o Falso " + Integer.toString(totalPreguntasVerdFals), verdFalso);
		//archivos.escribirArchivo(new File(direccion + "\\" + nombreDelArchivo + ".json"), Pregunta.toString());
		TF.put(Pregunta);
		totalPreguntasVerdFals++;
	}

	public void guardarenArchivo(String direccion, Resp_Cortas_Pregunta pregunta, String nombreDelArchivo) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		respCorta = new JSONObject();

		respCorta.put("Enunciado", pregunta.getEnunciado());
		respCorta.put("Respuesta", pregunta.getRespuestaCorta());
		respCorta.put("Peso", pregunta.getPeso());

		Pregunta.put("Respuesta Corta " + Integer.toString(totalPreguntasRespCort), respCorta);
		//archivos.escribirArchivo(new File(direccion + "\\" + nombreDelArchivo + ".json"), Pregunta.toString());
		RC.put(Pregunta);
		totalPreguntasRespCort++;
	}
	
	public void guardaArreglo(String direccion) {
		archivos.escribirArchivo(new File(direccion+".json"), SM.toString());
		archivos.escribirArchivo(new File(direccion+".json"), TF.toString());
		archivos.escribirArchivo(new File(direccion+".json"), RC.toString());
	}

	public void guardaTotalPreguntasSM(String direccion, ArrayList<Selec_Mul_Pregunta> pregunta) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		
		Pregunta.put("TotalSM", pregunta.size());
		archivos.escribirArchivo(new File(direccion+".json"), Pregunta.toString());
	}
	
	public void guardaTotalPreguntasTF(String direccion, ArrayList<TFpreguntas> pregunta) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		
		Pregunta.put("TotalTF", pregunta.size());
		archivos.escribirArchivo(new File(direccion+".json"), Pregunta.toString());
		
	}
	
	public void guardaTotalPreguntasRC(String direccion, ArrayList<Resp_Cortas_Pregunta> pregunta) {
		archivos = new archivos();
		Pregunta = new JSONObject();
		
		Pregunta.put("TotalRC", pregunta.size());
		archivos.escribirArchivo(new File(direccion+".json"), Pregunta.toString());
	}
}
