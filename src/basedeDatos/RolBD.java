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
public class RolBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Rol rol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO rol ("
                + "nombre"
                + ") VALUES ("
                + "'" + rol.getNombre() + "'"
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Rol> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM rol ORDER BY id_rol ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Rol> lista = new ArrayList<Rol>();
        try {
            if(rs.first())
			{
				do
				{
					Rol rol = new Rol();
					rol.setIdrol( rs.getInt( "id_rol" ) );
					rol.setNombre( rs.getString( "nombre" ) );
	                lista.add(rol);
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
    public static Rol buscarNombre( String nombrerol, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM rol WHERE nombre = '" + nombrerol + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Rol rol = new Rol();
				rol.setIdrol( rs.getInt( "id_rol" ) );
				rol.setNombre( rs.getString( "nombre" ) );
                return rol;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Rol buscarIdRol( int idrol, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM rol WHERE id_rol = " + idrol + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Rol rol = new Rol();
				rol.setIdrol( rs.getInt( "id_rol" ) );
				rol.setNombre( rs.getString( "nombre" ) );
                return rol;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Rol rol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE rol "
                + "SET nombre = '"+rol.getNombre()+ "'"
                +" WHERE id_rol= "+rol.getIdrol();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM rol "
                +"WHERE id_rol = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE rol";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}