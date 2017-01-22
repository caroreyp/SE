package basedeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class CrearBD 
{
	/**
	 * Metodo que busca y retorna el nombre de una base de datos segun los paramentros recibidos.
	 */
    public static String buscarBaseDatos( String nombrebased, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + nombrebased + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	String nombrebd = ( rs.getString( "SCHEMA_NAME" ) );
                return nombrebd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que crea una base de datos con el nombre recibido como paramentro.
	 */
    public static void crearBaseDatos(String nombrebased, Conector conector) throws ErrorConector
    {
        String lineaSQL = "CREATE DATABASE IF NOT EXISTS "+nombrebased;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que ejecuta la linea de codigo recibida como paramentro.
	 */
    public static void ejecutarCodigoSQL(String lineaSQL, Conector conector) throws ErrorConector
    {
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}