package mundo;

/**
 * Universidad Pedagogica y Tecnologica de Colombia
 * Facultad de Ingenieria
 * Escuela de Ingenieria de Sistemas y Computacion
 * Grupo de Investigacion de Software
 * Sistema Experto para la Seleccion de Personal
 * Caso de Estudio: Desarrollo de Software
 * @author Hilda Carolina Rey Pizza
 * Trabajo de Grado
 * 2012
 */
public class PuntajeEscala 
{
	private double puntajeescala;
	private int idescala;
	private String nombreescala;

	public PuntajeEscala(double puntajeescala, int idescala, String nombreescala) {
		this.puntajeescala = puntajeescala;
		this.idescala = idescala;
		this.nombreescala = nombreescala;
	}

	public double getPuntajeescala() {
		return puntajeescala;
	}

	public void setPuntajeescala(double puntajeescala) {
		this.puntajeescala = puntajeescala;
	}

	public int getIdescala() {
		return idescala;
	}

	public void setIdescala(int idescala) {
		this.idescala = idescala;
	}

	public String getNombreescala() {
		return nombreescala;
	}

	public void setNombreescala(String nombreescala) {
		this.nombreescala = nombreescala;
	}
}