package Controlador;

import java.util.ArrayList;

/**
 * Clase que realiza el examen al usuario.
 * 
 * @author Antony
 *
 */
public class Exam {
	static String nombre = null;
	//public static ArrayList<almacen> almacenDePregunta = new ArrayList<almacen>(); // un arraylist que almacena todo															// tipo de preguntas.
	public static ArrayList<estudiante> datosEstudiantes = new ArrayList<estudiante>();

	public  ArrayList<Selec_Mul_Pregunta> selecmulpreg = new ArrayList<Selec_Mul_Pregunta>();
	public ArrayList<TFpreguntas> tfpreg = new ArrayList<TFpreguntas>();
	public  ArrayList<Resp_Cortas_Pregunta> rcpreg = new ArrayList<Resp_Cortas_Pregunta>();
	public ArrayList<Object> Inventario = new ArrayList<Object>();

	public static int preg_buenas; // contador de buenas
	public static int preg_malas; // contador de malas
	public static int preg_sinTerminar;
	public static int numPreg = 1, puntajeTotal = 0, Intentos = 5;
	
	/**
	 * Agrega preguntas de seleccion multiple en el almacen de preguntas.
	 * 
	 * @param PreguntaSM Seleccion multiple
	 */
	public void agregaPregunta(Selec_Mul_Pregunta PreguntaSM) {
		//Inventario.add(PreguntaSM);
		selecmulpreg.add(PreguntaSM);
		/*
		 * almacen auxiliar = new almacen(PreguntaSM); almacenDePregunta.add(auxiliar);
		 */
	}

	/**
	 * Agrega preguntas de verdadero o falso en el almacen de preguntas.
	 * 
	 * @param PreguntaTF True or false
	 */
	public void agregaPregunta(TFpreguntas PreguntaTF) {
		//Inventario.add(PreguntaTF);
		tfpreg.add(PreguntaTF);
		/*
		 * almacen auxiliar = new almacen(PreguntaTF); almacenDePregunta.add(auxiliar);
		 */
	}

	/**
	 * Agrega preguntas con respuestas cortas en el almacen de preguntas.
	 * 
	 * @param PreguntaRC Resspuestas cortas
	 */
	public void agregaPregunta(Resp_Cortas_Pregunta PreguntaRC) {
		//Inventario.add(PreguntaRC);
		rcpreg.add(PreguntaRC);
		/*
		 * almacen auxiliar = new almacen(PreguntaRC); almacenDePregunta.add(auxiliar);
		 */
	}
	
	public int getCantidadRespuestasSM() {
		
		return 0;
	}

	/**
	 * Devuelve la cantidad de respuestas buenas.
	 * 
	 * @return int
	 */
	public static int getBuenas() {
		return preg_buenas;
	}

	/**
	 * Setea la cantidad de preguntas buenas
	 * 
	 * @param buenas valor numerico.
	 */
	public static void setBuenas(int buenas) {
		preg_buenas = buenas;
	}

	/**
	 * Devuelve la cantidad de respuestas malas.
	 * 
	 * @return int
	 */
	public static int getMalas() {
		return preg_malas;
	}

	/**
	 * Setea la cantidad de preguntas malas
	 * 
	 * @param malas valor numerico.
	 */
	public static void setMalas(int malas) {
		preg_malas = malas;
	}

	/**
	 * Devuelve la cantidad de preguntas que tiene el examen, se usa en la clase
	 * complementos
	 * 
	 * @return int
	 */
	/*public static int getTotalPreguntas() {
		return almacenDePregunta.size();
	}*/

	public int darExam() {
		for (int i = 0; i < selecmulpreg.size(); i++) {
			if(selecmulpreg.get(i).buscar()) {
				puntajeTotal += selecmulpreg.get(i).getPeso();
			}
		}
		for (int i = 0; i < tfpreg.size(); i++) {
			if(tfpreg.get(i).buscar()) {
				puntajeTotal += tfpreg.get(i).getPeso();
			}
		}
		for (int i = 0; i < rcpreg.size(); i++) {
			if(rcpreg.get(i).buscar()) {
				puntajeTotal += rcpreg.get(i).getPeso();
			}
		}
		return (int) (((double) puntajeTotal / (selecmulpreg.size()+ rcpreg.size()+tfpreg.size())) * 100);
	}

	/*public int darExam() {
		
		for(int i = 0; i< Inventario.size();i++) {
			if(Inventario.get(i) instanceof Selec_Mul_Pregunta) {
				if(((Selec_Mul_Pregunta) Inventario.get(i)).buscar()) {
					puntajeTotal += selecmulpreg.get(i).getPeso();
				}
			}
			if(Inventario.get(i) instanceof Resp_Cortas_Pregunta) {
				if(((Resp_Cortas_Pregunta) Inventario.get(i)).buscar()) {
					puntajeTotal += rcpreg.get(i).getPeso();
				}
			}
			if(Inventario.get(i) instanceof TFpreguntas) {
				if(((TFpreguntas) Inventario.get(i)).buscar()) {
					puntajeTotal += tfpreg.get(i).getPeso();
				}
			}
		}
		return (int) (((double) puntajeTotal / (selecmulpreg.size()+ rcpreg.size()+tfpreg.size())) * 100);
	}*/
	
	/**
	 * Metodo que realiza el examen, realiza las preguntas en el orden que fueron
	 * agregadas retorna el puntaje obtenido tras su ejecucion.
	 * 
	 * @return int
	 */

	/*-public int darExam() {
		Iterator<almacen> iteradorAlmacen = almacenDePregunta.iterator();	
		while (iteradorAlmacen.hasNext()) { // mientras que hallan preguntas. 
			System.out.print(numPreg + ") ");
			almacen pregunta = (almacen) iteradorAlmacen.next(); // 
			if (!(almacenDePregunta.isEmpty())) { // si el almacen de preguntas es distinto de vacio
				if (pregunta.getNuevaPreguntaRC() != null) {	//si la preguntaRC es distinta de null, o sea si no es preguntaTF o preguntaSM
					if (pregunta.getNuevaPreguntaRC().buscar() == true) { //si es igual a "true" respondio correcto.
						puntajeTotal += pregunta.getNuevaPreguntaRC().getPeso();
					}
				}
				if (pregunta.getNuevaPreguntaTF() != null) {	//si la preguntaTF es distinta de null, o sea si no es preguntaRC o preguntaSM
					if (pregunta.getNuevaPreguntaTF().buscar() == true) { //si es igual a "true" respondio correcto.
						puntajeTotal += pregunta.getNuevaPreguntaTF().getPeso();
					}
				}
				if (pregunta.getNuevaPreguntaSM() != null) {	//si la preguntaSM es distinta de null, o sea si no es preguntaTF o preguntaRC
					if (pregunta.getNuevaPreguntaSM().buscar() == true) { //si es igual a "true" respondio correcto.
						puntajeTotal += pregunta.getNuevaPreguntaSM().getPeso();
					} 
				}
			}
			numPreg++;
		}
		datosEstudiantes.add(new estudiante(nombre, (int) (((double) puntajeTotal / almacenDePregunta.size()) * 100), preg_buenas, preg_malas));
		return (int) (((double) puntajeTotal / almacenDePregunta.size()) * 100); // retorna el puntaje final.
	}*/

	/**
	 * Subclase de Exam que funciona como almacen universal de las preguntas sin
	 * importar el tipo que tengan.
	 * 
	 * @author Antony
	 *
	 */
	public class almacen {
		private Selec_Mul_Pregunta nuevaPreguntaSM;
		private TFpreguntas nuevaPreguntaTF;
		private Resp_Cortas_Pregunta nuevaPreguntaRC;

		/**
		 * Metodo constructor
		 * 
		 * @param nuevaPreguntaSM Selección multiple
		 */
		public almacen(Selec_Mul_Pregunta nuevaPreguntaSM) {
			this.nuevaPreguntaSM = nuevaPreguntaSM;
		}

		/**
		 * Metodo constructor
		 * 
		 * @param nuevaPreguntaTF True or false
		 */
		public almacen(TFpreguntas nuevaPreguntaTF) {
			this.nuevaPreguntaTF = nuevaPreguntaTF;
		}

		/**
		 * Metodo constructor
		 * 
		 * @param nuevaPreguntaRC Respuesta corta
		 */
		public almacen(Resp_Cortas_Pregunta nuevaPreguntaRC) {
			this.nuevaPreguntaRC = nuevaPreguntaRC;
		}

		/**
		 * Devuelve una pregunta de selección multiple
		 * 
		 * @return Selec_Mul_Pregunta
		 */
		public Selec_Mul_Pregunta getNuevaPreguntaSM() {
			return this.nuevaPreguntaSM;
		}

		/**
		 * Devuelve una pregunta de true or false
		 * 
		 * @return TFpreguntas
		 */
		public TFpreguntas getNuevaPreguntaTF() {
			return this.nuevaPreguntaTF;
		}

		/**
		 * Devuelve una pregunta con respuesta corta
		 * 
		 * @return Resp_Cortas_Pregunta
		 */
		public Resp_Cortas_Pregunta getNuevaPreguntaRC() {
			return this.nuevaPreguntaRC;
		}
	}
}
