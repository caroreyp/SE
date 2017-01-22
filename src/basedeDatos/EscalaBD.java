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
public class EscalaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Escala escal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO escala ("
                + "nombre,"
                + "descripcion"
                + ") VALUES ("
                + "'" + escal.getNombre() + "', "
                + "'" + escal.getDescripcion() + "'"
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Escala> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM escala ORDER BY id_escala ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Escala> lista = new ArrayList<Escala>();
        try {
            if(rs.first())
			{
				do
				{
					Escala escal = new Escala();
					escal.setIdescala( rs.getInt( "id_escala" ) );
					escal.setNombre( rs.getString( "nombre" ) );
					escal.setDescripcion( rs.getString( "descripcion" ) );
	                lista.add(escal);
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
    public static Escala buscarNombre( String nombrecaract, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM escala WHERE nombre = '" + nombrecaract + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Escala escal = new Escala();
            	escal.setIdescala( rs.getInt( "id_escala" ) );
            	escal.setNombre( rs.getString( "nombre" ) );
            	escal.setDescripcion( rs.getString( "descripcion" ) );
                return escal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Escala buscarIdEscala( int idescal, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM escala WHERE id_escala = " + idescal + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Escala escal = new Escala();
            	escal.setIdescala( rs.getInt( "id_escala" ) );
            	escal.setNombre( rs.getString( "nombre" ) );
            	escal.setDescripcion( rs.getString( "descripcion" ) );
                return escal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Escala escal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE escala "
                + "SET nombre = '" + escal.getNombre()+ "', "
                + "descripcion = '" + escal.getDescripcion()+ "' "
                +" WHERE id_escala= "+escal.getIdescala();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM escala "
                +"WHERE id_escala = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE escala";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}