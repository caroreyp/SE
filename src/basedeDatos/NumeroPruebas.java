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
public class NumeroPruebas 
{
	/**
	 * Es la variable que contiene el numero de tuplas almacenadas en al tabla prueba.
	 */
	private int numeroPruebas;
	
	/**
	 * Es el metodo constructor de la clase
	 */
	public NumeroPruebas()
	{
		List pruebas = null;
		try 
	    {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			pruebas= PruebaBD.listar(conector);
			conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    numeroPruebas = 0;
	    if(pruebas != null){
	    	numeroPruebas = pruebas.size();
	    }
	}

	/**
	 * Es el metodo que retorna el numero de pruebas
	 * @return numeroPruebas
	 */
	public int getNumeroPruebas() {
		return numeroPruebas;
	}

	/**
	 * Es el metodo que asigna el valor que se recibe como parametro a la variable numeroPruebas
	 * @param numero
	 */
	public void setNumeroPruebas(int numero) {
		this.numeroPruebas = numero;
	}
}