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
public class PuntajeCompetencia 
{
	private double puntajecompetencia;
	private int idcompetencia;
	private String nombrecompetencia;
	private ArrayList puntajesescalasprueba;

	public PuntajeCompetencia(double puntajecompetencia, int idcompetencia, String nombrecompetencia, ArrayList puntajesescalasprueba) {
		this.puntajecompetencia = puntajecompetencia;
		this.idcompetencia = idcompetencia;
		this.nombrecompetencia = nombrecompetencia;
		this.puntajesescalasprueba = puntajesescalasprueba;
	}

	public double getPuntajecompetencia() {
		return puntajecompetencia;
	}

	public void setPuntajecompetencia(double puntajecompetencia) {
		this.puntajecompetencia = puntajecompetencia;
	}

	public int getIdcompetencia() {
		return idcompetencia;
	}

	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}

	public String getNombrecompetencia() {
		return nombrecompetencia;
	}

	public void setNombrecompetencia(String nombrecompetencia) {
		this.nombrecompetencia = nombrecompetencia;
	}

	public ArrayList getPuntajesescalasprueba() {
		return puntajesescalasprueba;
	}

	public void setPuntajesescalasprueba(ArrayList puntajesescalasprueba) {
		this.puntajesescalasprueba = puntajesescalasprueba;
	}
}