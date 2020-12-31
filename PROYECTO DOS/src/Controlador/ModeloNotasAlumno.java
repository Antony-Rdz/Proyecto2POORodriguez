package Controlador;

public class ModeloNotasAlumno {

	String[] respuestaAlumno;
	String[] respuestaExamen;
	int[] puntajeAlumno;
	int[] puntajeExamen;
	
	public ModeloNotasAlumno() {
		
	}
	
	public String[] getRespuestaAlumno() {
		return respuestaAlumno;
	}
	public void setRespuestaAlumno(String[] respuestaAlumno) {
		this.respuestaAlumno = respuestaAlumno;
	}
	public String[] getRespuestaExamen() {
		return respuestaExamen;
	}
	public void setRespuestaExamen(String[] respuestaExamen) {
		this.respuestaExamen = respuestaExamen;
	}
	public int[] getPuntajeAlumno() {
		return puntajeAlumno;
	}
	public void setPuntajeAlumno(int[] puntajeAlumno) {
		this.puntajeAlumno = puntajeAlumno;
	}
	public int[] getPuntajeExamen() {
		return puntajeExamen;
	}
	public void setPuntajeExamen(int[] puntajeExamen) {
		this.puntajeExamen = puntajeExamen;
	}
	
	public int getPuntajeTotalExam() {
		int total = 0;
		for (int i : puntajeExamen) {
			total += i;
		}
		return total;
	}
	
	public int getPuntajeTotalAlumno() {
		int total = 0;
		for (int i : puntajeAlumno) {
			total += i;
		}
		return total;
	}
	
	
	
	
}
