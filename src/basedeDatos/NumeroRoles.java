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
public class NumeroRoles 
{
	/**
	 * Es la variable que contiene el numero de tuplas almacenadas en al tabla roles.
	 */
	private int numeroRoles;
	
	/**
	 * Es el metodo constructor de la clase
	 */
	public NumeroRoles()
	{
		List roles = null;
		try 
	    {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			roles= RolBD.listar(conector);
			conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    numeroRoles = 0;
	    if(roles != null){
	    	numeroRoles = roles.size();
	    }
	}

	/**
	 * Es el metodo que retorna el numero de roles
	 * @return numeroRoles
	 */
	public int getNumeroRoles() {
		return numeroRoles;
	}

	/**
	 * Es el metodo que asigna el valor que se recibe como parametro a la variable numeroRoles
	 * @param numero
	 */
	public void setNumeroRoles(int numero) {
		this.numeroRoles = numero;
	}
}