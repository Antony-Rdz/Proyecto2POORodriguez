package Controlador;

/**
 * Clase testeadora del examen
 * 
 * @author Antony
 *
 */
public class ExamDemo {
	
	Exam examem;
	
	/**
	 * Metodo principal que consta de un pequeño menú para el usuario. Aca tambien
	 * se agregan las preguntas que tendra el examen.
	 * 
	 * @param args Se ingresa por medio de consola
	 */
	public static Exam retornaexamen() {
		Exam examen = new Exam();
		
		
		//complementos menuExam = new complementos();
		// ----------------------------------------Agrega preguntas-----------------------------------------------
		examen.agregaPregunta(new TFpreguntas("Los cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridad ", false, 1));
		String pregElec[] = { "Chile", "Mexico", "Argentina", "Peru", "Colombia" };
		examen.agregaPregunta(new Selec_Mul_Pregunta("¿En qué país se encuentra ubicada la Casa Rosada? Los cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridadLos cojines de aire (air bags) reemplazan ventajosamente al cinturón de seguridad", pregElec, 2, 1));
		examen.agregaPregunta(new Resp_Cortas_Pregunta("¿Qué tipo de animal es la ballena?", "Mamifero", 1));
		examen.agregaPregunta(new TFpreguntas("El francés es idioma oficial en Haití", true, 1));
		String pregElec2[] = { "1942", "1947", "1943","1945","1945","1945" };
		examen.agregaPregunta(new Selec_Mul_Pregunta("¿Cuándo terminó la II Guerra Mundial?", pregElec2, 3, 1));
		examen.agregaPregunta(new Resp_Cortas_Pregunta("¿Cuál es el río más largo del mundo?", "Amazonas", 1));
		String pregElec3[] = { "Salidas de colegio ", "Carreteras", "Intersecciones en ciudad", "Caminos rurales" };
		examen.agregaPregunta(new Selec_Mul_Pregunta("La mayor parte de los accidentes de tránsito se produce en:", pregElec3, 2, 1));
		examen.agregaPregunta(new Resp_Cortas_Pregunta("¿Quién escribió La Odisea?", "Homero", 1));
		examen.agregaPregunta(new TFpreguntas("Japon fue el pais que uso la primera bomba atomica", true, 1));
		String pregElec4[] = { "Mario Vargas Llosa", "Gabriel Garcia Marquez", "Camilo Jose Cela","Mario Vargas Llosa" };
		examen.agregaPregunta(new Selec_Mul_Pregunta("¿Quién escribió Cien años de soledad?", pregElec4, 1, 1));
		
		// -------------------------------------------------------------------------------------------------------
		
		return examen;
		
	}	
}
