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
public class Permiso 
{
	private int idpermiso;
	private int idcandidato;
	private int idadmin;
	private String fechapermiso;
	private String horapermiso;
	
	public Permiso()
	{
		
	}

	public Permiso(int idpermiso, int idcandidato, int idadmin, String fechapermiso, String horapermiso) {
		this.idpermiso = idpermiso;
		this.idcandidato = idcandidato;
		this.idadmin = idadmin;
		this.fechapermiso = fechapermiso;
		this.horapermiso = horapermiso;
	}

	public int getIdpermiso() {
		return idpermiso;
	}

	public void setIdpermiso(int idpermiso) {
		this.idpermiso = idpermiso;
	}

	public int getIdcandidato() {
		return idcandidato;
	}

	public void setIdcandidato(int idcandidato) {
		this.idcandidato = idcandidato;
	}

	public int getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}

	public String getFechapermiso() {
		return fechapermiso;
	}

	public void setFechapermiso(String fechapermiso) {
		this.fechapermiso = fechapermiso;
	}

	public String getHorapermiso() {
		return horapermiso;
	}

	public void setHorapermiso(String horapermiso) {
		this.horapermiso = horapermiso;
	}

}