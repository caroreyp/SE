package basedeDatos;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
public class NumeroCompetencias 
{
	/**
	 * Es la variable que contiene el numero de tuplas almacenadas en al tabla competencia.
	 */
	private int numeroCompetenc;
	
	/**
	 * Es el metodo constructor de la clase
	 */
	public NumeroCompetencias()
	{
		List competencias = null;
		try 
	    {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			competencias= CompetenciaBD.listar(conector);
			conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    numeroCompetenc = 0;
	    if(competencias != null){
	    	numeroCompetenc = competencias.size();
	    }
	}

	/**
	 * Es el metodo que retorna el numero de competencias
	 * @return numeroCompetenc
	 */
	public int getNumeroCompetenc() {
		return numeroCompetenc;
	}

	/**
	 * Es el metodo que asigna el valor que se recibe como parametro a la variable numeroCompetenc
	 * @param numero
	 */
	public void setNumeroCompetenc(int numero) {
		this.numeroCompetenc = numero;
	}
}