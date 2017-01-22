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
public class Convocatoria 
{
	private int idconvocatoria;
	private int idadmin;
	private String nombre;
	private String fechainicial;
	private String horainicial;
	private String fechafinal;
	private String horafinal;
	private String finalizada;
	
	public Convocatoria()
	{
		
	}

	public Convocatoria(int idconvocatoria, int idadmin, String nombre, String fechainicial, String horainicial, String fechafinal, String horafinal, String finalizada) {
		this.idconvocatoria = idconvocatoria;
		this.idadmin = idadmin;
		this.nombre = nombre;
		this.fechainicial = fechainicial;
		this.horainicial = horainicial;
		this.fechafinal = fechafinal;
		this.horafinal = horafinal;
		this.finalizada = finalizada;
	}

	public int getIdconvocatoria() {
		return idconvocatoria;
	}

	public void setIdconvocatoria(int idconvocatoria) {
		this.idconvocatoria = idconvocatoria;
	}

	public int getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechainicial() {
		return fechainicial;
	}

	public void setFechainicial(String fechainicial) {
		this.fechainicial = fechainicial;
	}

	public String getHorainicial() {
		return horainicial;
	}

	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}

	public String getFechafinal() {
		return fechafinal;
	}

	public void setFechafinal(String fechafinal) {
		this.fechafinal = fechafinal;
	}

	public String getHorafinal() {
		return horafinal;
	}

	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
	}

	public String getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(String finalizada) {
		this.finalizada = finalizada;
	}
}