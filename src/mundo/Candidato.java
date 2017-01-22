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
public class Candidato {
	
	private int idcandidato;
	private String documentoidentidad;
	private String nombres;
	private String apellidos;
	private String genero;
	private String fechanacimiento;
	private int dianacimiento;
	private int mesnacimiento;
	private int añonacimiento;
	private String numerotelefonico;
	private String correoelectronico;

	public Candidato()
	{
		
	}

	public Candidato(int idcandidato, String documentoidentidad, String nombres, String apellidos, String genero, String fechanacimiento, int dianacimiento, int mesnacimiento, int añonacimiento, String numerotelefonico, String correoelectronico) {
		this.idcandidato = idcandidato;
		this.documentoidentidad = documentoidentidad;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechanacimiento = fechanacimiento;
		this.dianacimiento = dianacimiento;
		this.mesnacimiento = mesnacimiento;
		this.añonacimiento = añonacimiento;
		this.numerotelefonico = numerotelefonico;
		this.correoelectronico = correoelectronico;
	}

	public int getIdcandidato() {
		return idcandidato;
	}

	public void setIdcandidato(int idcandidato) {
		this.idcandidato = idcandidato;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public int getDianacimiento() {
		return dianacimiento;
	}

	public void setDianacimiento(int dianacimiento) {
		this.dianacimiento = dianacimiento;
	}

	public int getMesnacimiento() {
		return mesnacimiento;
	}

	public void setMesnacimiento(int mesnacimiento) {
		this.mesnacimiento = mesnacimiento;
	}

	public int getAñonacimiento() {
		return añonacimiento;
	}

	public void setAñonacimiento(int añonacimiento) {
		this.añonacimiento = añonacimiento;
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