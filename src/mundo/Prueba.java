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
public class Prueba 
{
	private int idprueba;
	private String nombre;
	private String tipoprueba;
	private int tiempomaximo;
	
	public Prueba()
	{
		
	}

	public Prueba(int idprueba, String nombre, String tipoprueba, int tiempomaximo) {
		this.idprueba = idprueba;
		this.nombre = nombre;
		this.tipoprueba = tipoprueba;
		this.tiempomaximo = tiempomaximo;
	}

	public int getIdprueba() {
		return idprueba;
	}

	public void setIdprueba(int idprueba) {
		this.idprueba = idprueba;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoprueba() {
		return tipoprueba;
	}

	public void setTipoprueba(String tipoprueba) {
		this.tipoprueba = tipoprueba;
	}

	public int getTiempomaximo() {
		return tiempomaximo;
	}

	public void setTiempomaximo(int tiempomaximo) {
		this.tiempomaximo = tiempomaximo;
	}
}