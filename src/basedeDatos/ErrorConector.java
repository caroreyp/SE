package basedeDatos;

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
public class ErrorConector extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de ErrorConector<br>	 *
	 * @param mensaje Mensaje de error
	 */
	public ErrorConector( String mensaje ) {
		super( mensaje );
	}

	/**
	 * Constructor de ErrorConector<br>	 *
	 * @param mensaje Mensaje de error
	 * @param detalles Detalles del error
	 */
	public ErrorConector( String mensaje, String detalles ) {
		super( mensaje + "\nDetalles: " + detalles );
	}

}