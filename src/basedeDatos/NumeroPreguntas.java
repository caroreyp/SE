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
public class NumeroPreguntas 
{
	/**
	 * Es la variable que contiene el numero de tuplas almacenadas en al tabla pregunta.
	 */
	private int numeroPreguntas;
	
	/**
	 * Es el metodo constructor de la clase
	 */
	public NumeroPreguntas()
	{
		List preguntas = null;
		try 
	    {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			preguntas= PreguntaBD.listar(conector);
			conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    numeroPreguntas = 0;
	    if(preguntas != null){
		    numeroPreguntas = preguntas.size();
	    }
	}

	/**
	 * Es el metodo que retorna el numero de preguntas
	 * @return numeroPreguntas
	 */
	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}

	/**
	 * Es el metodo que asigna el valor que se recibe como parametro a la variable numeroPreguntas
	 * @param numero
	 */
	public void setNumeroPreguntas(int numero) {
		this.numeroPreguntas = numero;
	}
}