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
public class ResultadoRol 
{
	private int idrol;
	private int idresultado;
	
	public ResultadoRol()
	{
		
	}

	public ResultadoRol(int idrol, int idresultado) {
		this.idrol = idrol;
		this.idresultado = idresultado;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public int getIdresultado() {
		return idresultado;
	}

	public void setIdresultado(int idresultado) {
		this.idresultado = idresultado;
	}
}