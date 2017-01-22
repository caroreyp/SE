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
public class PruebaCandidato 
{
	private int idprueba;
	private int idcandidato;
	private int idresultado;
	private int idconvocator;
	
	public PruebaCandidato()
	{
		
	}

	public PruebaCandidato(int idprueba, int idcandidato, int idresultado, int idconvocator) {
		this.idprueba = idprueba;
		this.idcandidato = idcandidato;
		this.idresultado = idresultado;
		this.idconvocator = idconvocator;
	}

	public int getIdprueba() {
		return idprueba;
	}

	public void setIdprueba(int idprueba) {
		this.idprueba = idprueba;
	}

	public int getIdcandidato() {
		return idcandidato;
	}

	public void setIdcandidato(int idcandidato) {
		this.idcandidato = idcandidato;
	}

	public int getIdresultado() {
		return idresultado;
	}

	public void setIdresultado(int idresultado) {
		this.idresultado = idresultado;
	}

	public int getIdconvocator() {
		return idconvocator;
	}

	public void setIdconvocator(int idconvocator) {
		this.idconvocator = idconvocator;
	}
}