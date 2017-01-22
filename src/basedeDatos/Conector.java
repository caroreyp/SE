package basedeDatos;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
public class Conector {
	
	/**
	 * Es la variable que indica el Driver utilizado para la conexion
	 */
	protected String driver;

	/**
	 * Es la variable que indica la URL de conexion a la base de datos
	 */
	protected String url;

	/**
	 * Es la variable que indica el Usuario de acceso a la base de datos
	 */
	protected String user;

	/**
	 * Es la variable que indica la Contraseña de acceso a la base de datos
	 */
	protected String password;

    /**
	 * Es la variable que indica la Direccion del servidor de base de datos
	 */
	protected String host;

	/**
	 * Es la variable que indica el Nombre de la base de datos
	 */
	protected String dataBase;

	/**
	 * Es la variable que indica el Puerto utilizado para la conexion de la base de datos
	 */
	protected String port;	

	/**
	 * Es la variable que indica el Objeto de conexion con la base de datos
	 */
	protected Connection conector;

	/**
	 * Es la variable que indica el Objeto de escucha de la sesion de la base de datos
	 */
	protected Statement escuchador;
	
	/**
	 * Es el metodo constructor de la clase.
	 */
	public Conector(){
		dataBase = "personalsw";
		host = "localhost";
		ArrayList parametrosbd = parametrosConectorBaseDatos();
		if(parametrosbd != null)
		{
			if(parametrosbd.size()>0)
			{
				dataBase =(String)parametrosbd.get(0);
				host = (String)parametrosbd.get(1);
			}
		}
		driver = "com.mysql.jdbc.Driver";
		port = "3306";
		url = "jdbc:mysql://" + host +":" + port +"/" + dataBase;
		user = "root";
		password = "";
	}
	
	public Conector(String namedatabase){
		//dataBase = "information_schema";
		dataBase = namedatabase;
		host = "localhost";
		ArrayList parametrosbd = parametrosConectorBaseDatos();
		if(parametrosbd != null)
		{
			if(parametrosbd.size()>0)
			{
				host = (String)parametrosbd.get(1);
			}
		}
		driver = "com.mysql.jdbc.Driver";
		port = "3306";
		url = "jdbc:mysql://" + host +":" + port +"/" + dataBase;
		user = "root";
		password = "";
	}
	
	/**
	 * Es el metodo que obtiene y retorna los parametros de nombre de base de datos y host servidor, del archivo plano parametrosBD.txt.
	 * @return parametros
	 */
	public ArrayList parametrosConectorBaseDatos()
	{
		ArrayList parametros = new ArrayList(); 
		try
		{
			FileInputStream fr = new FileInputStream( "./images/parametrosBD.txt" );
	    	DataInputStream entrada = new DataInputStream(fr);
	        String parametro = "";
	        String valorparametro = "";
	        boolean esvalor = false;
	        while((parametro=entrada.readLine())!= null)
	        {
	        	for (int i = 0; i < parametro.length(); i++) 
	        	{
	        		if(esvalor == true)
	        		{
	        			valorparametro += parametro.charAt(i);
	        		}
	        		if(parametro.charAt(i) == ':')
	        		{
	        			esvalor = true;
	        		}
				}
	        	parametros.add(valorparametro.trim());
	        	valorparametro = "";
	        	esvalor = false;
	        }
		}
		catch (Exception e)
		{
		}
		return parametros;
	}
	
	/**
	 * Es el metodo conecta o que inicia la conexion con la base de datos.
	 * @throws ErrorConector
	 */
	public void iniciarConexionBaseDatos() throws ErrorConector {
		try {
			Class.forName( driver );
			conector = DriverManager.getConnection (url,user, password);
			escuchador = conector.createStatement();
		} catch (ClassNotFoundException e) {
			throw new ErrorConector( "Driver no encontrado", e.getMessage() );
		} catch (SQLException e) {
			throw new ErrorConector( "Error de conexion", e.getMessage() );
		}
	}

	/**
	 * Es el metodo que desconecta o que termina una conexion con la base de datos.
	 * @throws ErrorConector
	 */
	public void terminarConexionBaseDatos() throws ErrorConector {
		try {
			escuchador.close();
			conector.close();
		} catch (SQLException e) {
			throw new ErrorConector( "Error al cerrar la conexion", e.getMessage() );
		}
	}
	
	/**
	 * Es el metodo que permite ejecutar la consulta SQL o select recibido como parametro.
	 * Se retorna el resultado de la consulta.
	 * @param consultaSQL
	 * @return rs
	 * @throws ErrorConector
	 */
	public ResultSet consultarEnBaseDatos(String consultaSQL) throws ErrorConector
	{
		ResultSet rs = null;
		try 
		{
			rs = escuchador.executeQuery(consultaSQL);
		} 
		catch (SQLException e) 
		{
			throw new ErrorConector( "Error al consultar", e.getMessage() );
		}
		return rs;
	}

	/**
	 * Es el metodo que permite ejecutar una accion segun el la linea recibida como parametro.
	 * @param lineaSQL
	 * @throws ErrorConector
	 */
	public void ejecutarEnBaseDatos(String lineaSQL) throws ErrorConector
	{
		try 
		{
			escuchador.executeUpdate(lineaSQL);
		} 
		catch (SQLException e) 
		{
			throw new ErrorConector( "Error al ejecutar", e.getMessage() );
		} 
	}
}