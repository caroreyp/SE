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
public class PreguntaEscala 
{
	private int idescala;
	private int idpregunta;
	private String respuesta;
	
	public PreguntaEscala()
	{
		
	}

	public PreguntaEscala(int idescala, int idpregunta, String respuesta) {
		this.idescala = idescala;
		this.idpregunta = idpregunta;
		this.respuesta = respuesta;
	}

	public int getIdescala() {
		return idescala;
	}

	public void setIdescala(int idescala) {
		this.idescala = idescala;
	}

	public int getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}