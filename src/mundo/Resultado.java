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
public class Resultado 
{
	private int idresultado;
	private double puntaje;
	private String observaciones;
	private String fechaprueba;
	private String nombredia;
	private String horainicial;
	private String horafinal;
	private int duracionprueba;
	private String revisado;
	private String comentarios;
	
	public Resultado()
	{
		
	}

	public Resultado(int idresultado, double puntaje, String observaciones, String fechaprueba, String nombredia, String horainicial, String horafinal, int duracionprueba, String revisado, String comentarios) {
		this.idresultado = idresultado;
		this.puntaje = puntaje;
		this.observaciones = observaciones;
		this.fechaprueba = fechaprueba;
		this.nombredia = nombredia;
		this.horainicial = horainicial;
		this.horafinal = horafinal;
		this.duracionprueba = duracionprueba;
		this.revisado = revisado;
		this.comentarios = comentarios;
	}

	public int getIdresultado() {
		return idresultado;
	}

	public void setIdresultado(int idresultado) {
		this.idresultado = idresultado;
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFechaprueba() {
		return fechaprueba;
	}

	public void setFechaprueba(String fechaprueba) {
		this.fechaprueba = fechaprueba;
	}

	public String getNombredia() {
		return nombredia;
	}

	public void setNombredia(String nombredia) {
		this.nombredia = nombredia;
	}

	public String getHorainicial() {
		return horainicial;
	}

	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}

	public String getHorafinal() {
		return horafinal;
	}

	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
	}

	public int getDuracionprueba() {
		return duracionprueba;
	}

	public void setDuracionprueba(int duracionprueba) {
		this.duracionprueba = duracionprueba;
	}

	public String getRevisado() {
		return revisado;
	}

	public void setRevisado(String revisado) {
		this.revisado = revisado;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}