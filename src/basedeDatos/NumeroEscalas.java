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
public class NumeroEscalas 
{
	/**
	 * Es la variable que contiene el numero de tuplas almacenadas en al tabla escala.
	 */
	private int numeroEscalas;
	
	/**
	 * Es el metodo constructor de la clase
	 */
	public NumeroEscalas()
	{
		List escalas = null;
		try 
	    {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			escalas= EscalaBD.listar(conector);
			conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    numeroEscalas = 0;
	    if(escalas != null){
	    	numeroEscalas = escalas.size();
	    }
	}

	/**
	 * Es el metodo que retorna el numero de escalas
	 * @return numeroRoles
	 */
	public int getNumeroEscalas() {
		return numeroEscalas;
	}

	/**
	 * Es el metodo que asigna el valor que se recibe como parametro a la variable numeroEscalas
	 * @param numero
	 */
	public void setNumeroEscalas(int numero) {
		this.numeroEscalas = numero;
	}
}