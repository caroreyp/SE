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
public class CompetenciaEscala 
{
	private int idcompetencia;
	private int idescala;
	
	public CompetenciaEscala()
	{
		
	}

	public CompetenciaEscala(int idcompetencia, int idescala) {
		this.idcompetencia = idcompetencia;
		this.idescala = idescala;
	}

	public int getIdcompetencia() {
		return idcompetencia;
	}

	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}

	public int getIdescala() {
		return idescala;
	}

	public void setIdescala(int idescala) {
		this.idescala = idescala;
	}
}