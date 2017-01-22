package mundo;

import java.util.ArrayList;

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
public class PuntajeRol 
{
	private double puntajerol;
	private int idrol;
	private String nombrerol;
	private ArrayList puntajescompetenciasprueba;

	public PuntajeRol(double puntajerol, int idrol, String nombrerol, ArrayList puntajescompetenciasprueba) {
		this.puntajerol = puntajerol;
		this.idrol = idrol;
		this.nombrerol = nombrerol;
		this.puntajescompetenciasprueba = puntajescompetenciasprueba;
	}

	public double getPuntajerol() {
		return puntajerol;
	}

	public void setPuntajerol(double puntajerol) {
		this.puntajerol = puntajerol;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getNombrerol() {
		return nombrerol;
	}

	public void setNombrerol(String nombrerol) {
		this.nombrerol = nombrerol;
	}

	public ArrayList getPuntajescompetenciasprueba() {
		return puntajescompetenciasprueba;
	}

	public void setPuntajescompetenciasprueba(ArrayList puntajescompetenciasprueba) {
		this.puntajescompetenciasprueba = puntajescompetenciasprueba;
	}
}