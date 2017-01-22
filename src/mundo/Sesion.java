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
public class Sesion 
{
	private int idsesion;
	private String fechasesion;
	private String horainicial;
	private String horafinal;
	
	public Sesion()
	{
		
	}
	
	public Sesion(int idsesion, String fechasesion, String horainicial, String horafinal) {
		this.idsesion = idsesion;
		this.fechasesion = fechasesion;
		this.horainicial = horainicial;
		this.horafinal = horafinal;
	}
	
	public int getIdsesion() {
		return idsesion;
	}

	public void setIdsesion(int idsesion) {
		this.idsesion = idsesion;
	}

	public String getFechasesion() {
		return fechasesion;
	}

	public void setFechasesion(String fechasesion) {
		this.fechasesion = fechasesion;
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
}