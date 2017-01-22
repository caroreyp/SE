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
public class Pregunta 
{
	private int idpregunta;
	private int ordennumerico;
	private String texto;
	
	public Pregunta()
	{
		
	}

	public Pregunta(int idpregunta, int ordennumerico, String texto) {
		this.idpregunta = idpregunta;
		this.ordennumerico = ordennumerico;
		this.texto = texto;
	}

	public int getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public int getOrdennumerico() {
		return ordennumerico;
	}

	public void setOrdennumerico(int ordennumerico) {
		this.ordennumerico = ordennumerico;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}