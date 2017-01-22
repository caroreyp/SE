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
public class Administrador 
{
	private int idadmin;
	private String documentoidentidad;
	private String nombres;
	private String apellidos;
	private String password;
	private String numerotelefonico;
	private String correoelectronico;

	public Administrador()
	{
		
	}

	public Administrador(int idadmin, String documentoidentidad, String nombres, String apellidos, String password, String numerotelefonico, String correoelectronico) {
		this.idadmin = idadmin;
		this.documentoidentidad = documentoidentidad;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.password = password;
		this.numerotelefonico = numerotelefonico;
		this.correoelectronico = correoelectronico;
	}

	public int getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}

	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumerotelefonico() {
		return numerotelefonico;
	}

	public void setNumerotelefonico(String numerotelefonico) {
		this.numerotelefonico = numerotelefonico;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
}