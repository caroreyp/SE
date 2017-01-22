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
public class CaracteristicaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Caracteristica caracter, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO caracteristica ("
                + "id_escala,"
                + "nombre,"
                + "descripcion"
                + ") VALUES ("
                + caracter.getIdescala() + ", "
                + "'" + caracter.getNombre() + "', "
                + "'" + caracter.getDescripcion() + "'"
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Caracteristica> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM caracteristica ORDER BY id_caracteristica ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Caracteristica> lista = new ArrayList<Caracteristica>();
        try {
            if(rs.first())
			{
				do
				{
					Caracteristica caracter = new Caracteristica();
					caracter.setIdcaracteristica( rs.getInt( "id_caracteristica" ) );
					caracter.setIdescala( rs.getInt( "id_escala" ) );
					caracter.setNombre( rs.getString( "nombre" ) );
					caracter.setDescripcion( rs.getString( "descripcion" ) );
	                lista.add(caracter);
				} while(rs.next());
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
	 * Metodo que retorna en una lista las tuplas seleccionadas segun los parametros.
	 */
    public static List<Caracteristica> buscarCaracterisiticasEscala( int idescal, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM caracteristica WHERE id_escala =" + idescal;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Caracteristica> lista = new ArrayList<Caracteristica>();
        try {
            if(rs.first())
			{
				do
				{
					Caracteristica caracter = new Caracteristica();
					caracter.setIdcaracteristica( rs.getInt( "id_caracteristica" ) );
					caracter.setIdescala( rs.getInt( "id_escala" ) );
					caracter.setNombre( rs.getString( "nombre" ) );
					caracter.setDescripcion( rs.getString( "descripcion" ) );
	                lista.add(caracter);
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
    public static Caracteristica buscarNombre( String nombrecaract, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM caracteristica WHERE nombre = '" + nombrecaract + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Caracteristica caracter = new Caracteristica();
				caracter.setIdcaracteristica( rs.getInt( "id_caracteristica" ) );
				caracter.setIdescala( rs.getInt( "id_escala" ) );
				caracter.setNombre( rs.getString( "nombre" ) );
				caracter.setDescripcion( rs.getString( "descripcion" ) );
                return caracter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Caracteristica buscarIdPrueba( int idcaract, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM caracteristica WHERE id_caracteristica = " + idcaract + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Caracteristica caracter = new Caracteristica();
				caracter.setIdcaracteristica( rs.getInt( "id_caracteristica" ) );
				caracter.setIdescala( rs.getInt( "id_escala" ) );
				caracter.setNombre( rs.getString( "nombre" ) );
				caracter.setDescripcion( rs.getString( "descripcion" ) );
                return caracter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Caracteristica caracter, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE caracteristica "
                + "SET id_escala = "+caracter.getIdescala()+ ", "
                + "nombre = '" + caracter.getNombre()+ "', "
                + "descripcion = '" + caracter.getDescripcion()+ "' "
                +" WHERE id_caracteristica= "+caracter.getIdcaracteristica();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM caracteristica "
                +"WHERE id_caracteristica = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE caracteristica";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}