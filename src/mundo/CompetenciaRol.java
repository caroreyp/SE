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
public class CompetenciaRol 
{
	private int idcompetencia;
	private int idrol;
	
	public CompetenciaRol()
	{
		
	}

	public CompetenciaRol(int idcompetencia, int idrol) {
		this.idcompetencia = idcompetencia;
		this.idrol = idrol;
	}

	public int getIdcompetencia() {
		return idcompetencia;
	}

	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
}