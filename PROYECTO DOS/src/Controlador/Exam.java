package Controlador;

import java.util.ArrayList;

/**
 * Clase que realiza el examen al usuario.
 * 
 * @author Antony
 *
 */
public class Exam {

	
///////////////////////////////////////////////////////////////////////////////////////////////	
	public  ArrayList<Selec_Mul_Pregunta> selecmulpreg = new ArrayList<Selec_Mul_Pregunta>();
	public ArrayList<TFpreguntas> tfpreg = new ArrayList<TFpreguntas>();
	public  ArrayList<Resp_Cortas_Pregunta> rcpreg = new ArrayList<Resp_Cortas_Pregunta>();
	public int tiempo;
	public int totalPreguntas;
	public int smTT;
	public int tfTT;
	public int rcTT;
	public int formaExamen;
///////////////////////////////////////////////////////////////////////////////////////////////		
	
	/**
	 * Agrega preguntas de seleccion multiple en el almacen de preguntas.
	 * 
	 * @param PreguntaSM Seleccion multiple
	 */
	public void agregaPregunta(Selec_Mul_Pregunta PreguntaSM) {
		selecmulpreg.add(PreguntaSM);
	}

	/**
	 * Agrega preguntas de verdadero o falso en el almacen de preguntas.
	 * 
	 * @param PreguntaTF True or false
	 */
	public void agregaPregunta(TFpreguntas PreguntaTF) {
		tfpreg.add(PreguntaTF);
	}

	/**
	 * Agrega preguntas con respuestas cortas en el almacen de preguntas.
	 * 
	 * @param PreguntaRC Resspuestas cortas
	 */
	public void agregaPregunta(Resp_Cortas_Pregunta PreguntaRC) {
		rcpreg.add(PreguntaRC);
	}
		
	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getTotalPreguntas() {
		return totalPreguntas;
	}

	public void setTotalPreguntas(int totalPreguntas) {
		this.totalPreguntas = totalPreguntas;
	}

	public int getSmTT() {
		return smTT;
	}

	public void setSmTT(int smTT) {
		this.smTT = smTT;
	}

	public int getTfTT() {
		return tfTT;
	}

	public void setTfTT(int tfTT) {
		this.tfTT = tfTT;
	}

	public int getRcTT() {
		return rcTT;
	}

	public void setRcTT(int rcTT) {
		this.rcTT = rcTT;
	}

	public int getFormaExamen() {
		return formaExamen;
	}

	public void setFormaExamen(int formaExamen) {
		this.formaExamen = formaExamen;
	}
	
}
