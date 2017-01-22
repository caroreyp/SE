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
public class Competencia 
{
	private int idcompetencia;
	private String nombre;
	private String descripcion;
	
	public Competencia()
	{
		
	}

	public Competencia(int idcompetencia, String nombre, String descripcion) {
		this.idcompetencia = idcompetencia;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdcompetencia() {
		return idcompetencia;
	}

	public void setIdcompetencia(int idcompetencia) {
		this.idcompetencia = idcompetencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}