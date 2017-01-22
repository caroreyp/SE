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
public class SesionAdministrador 
{
	private int idsesion;
	private int idadministrador;
	
	public SesionAdministrador()
	{
		
	}
	
	public SesionAdministrador(int idsesion, int idadministrador)
	{
		this.idsesion = idsesion;
		this.idadministrador = idadministrador;
	}

	public int getIdsesion() {
		return idsesion;
	}

	public void setIdsesion(int idsesion) {
		this.idsesion = idsesion;
	}

	public int getIdadministrador() {
		return idadministrador;
	}

	public void setIdadministrador(int idadministrador) {
		this.idadministrador = idadministrador;
	}
}