package basedeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mundo.*;

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
public class PruebaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Prueba prueb, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO prueba ("
                + "nombre,"
                + "tipo_prueba,"
                + "tiempo_maximo"
                + ") VALUES ("
                + "'" + prueb.getNombre() + "', "
                + "'" + prueb.getTipoprueba() + "', "
                + prueb.getTiempomaximo() 
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Prueba> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM prueba ORDER BY id_prueba ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Prueba> lista = new ArrayList<Prueba>();
        try {
            if(rs.first())
			{
				do
				{
					Prueba prueb = new Prueba();
					prueb.setIdprueba( rs.getInt( "id_prueba" ) );
					prueb.setNombre( rs.getString( "nombre" ) );
					prueb.setTipoprueba( rs.getString( "tipo_prueba" ) );
					prueb.setTiempomaximo( rs.getInt( "tiempo_maximo" ) );
	                lista.add(prueb);
				} while(rs.next());
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Prueba buscarNombre( String nombreprueb, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM prueba WHERE nombre = '" + nombreprueb + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Prueba prueb = new Prueba();
				prueb.setIdprueba( rs.getInt( "id_prueba" ) );
				prueb.setNombre( rs.getString( "nombre" ) );
				prueb.setTipoprueba( rs.getString( "tipo_prueba" ) );
				prueb.setTiempomaximo( rs.getInt( "tiempo_maximo" ) );
                return prueb;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Prueba buscarIdPrueba( int idprueba, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM prueba WHERE id_prueba = " + idprueba + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Prueba prueb = new Prueba();
				prueb.setIdprueba( rs.getInt( "id_prueba" ) );
				prueb.setNombre( rs.getString( "nombre" ) );
				prueb.setTipoprueba( rs.getString( "tipo_prueba" ) );
				prueb.setTiempomaximo( rs.getInt( "tiempo_maximo" ) );
                return prueb;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Prueba prueb, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE prueba "
                + "SET nombre = '"+prueb.getNombre()+ "', "
                + "tipo_prueba = '" + prueb.getTipoprueba()+ "', "
                + "tiempo_maximo = " + prueb.getTiempomaximo()
                +" WHERE id_prueba= "+prueb.getIdprueba();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM prueba "
                +"WHERE id_prueba = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE prueba";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}