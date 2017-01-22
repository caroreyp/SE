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
public class Caracteristica 
{
	private int idcaracteristica;
	private int idescala;
	private String nombre;
	private String descripcion;
	
	public Caracteristica()
	{
		
	}

	public Caracteristica(int idcaracteristica, int idescala, String nombre, String descripcion) {
		this.idcaracteristica = idcaracteristica;
		this.idescala = idescala;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdcaracteristica() {
		return idcaracteristica;
	}

	public void setIdcaracteristica(int idcaracteristica) {
		this.idcaracteristica = idcaracteristica;
	}

	public int getIdescala() {
		return idescala;
	}

	public void setIdescala(int idescala) {
		this.idescala = idescala;
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