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
public class SesionCandidato 
{
	private int idsesion;
	private int idcandidato;
	
	public SesionCandidato()
	{
		
	}

	public SesionCandidato(int idsesion, int idcandidato) {
		this.idsesion = idsesion;
		this.idcandidato = idcandidato;
	}

	public int getIdsesion() {
		return idsesion;
	}

	public void setIdsesion(int idsesion) {
		this.idsesion = idsesion;
	}

	public int getIdcandidato() {
		return idcandidato;
	}

	public void setIdcandidato(int idcandidato) {
		this.idcandidato = idcandidato;
	}
}