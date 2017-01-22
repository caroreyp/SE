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
public class PermisoBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Permiso permiso, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO permiso ("
                + "id_candidato,"
                + "id_administrador,"
                + "fecha_permiso,"
                + "hora_permiso"
                + ") VALUES ("
                + permiso.getIdcandidato() + ", "
                + permiso.getIdadmin() + ", "
                + "'" + permiso.getFechapermiso() + "', "
                + "'" + permiso.getHorapermiso() + "' "
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Permiso> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM permiso ORDER BY id_permiso ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Permiso> lista = new ArrayList<Permiso>();
        try {
            if(rs.first())
			{
				do
				{
					Permiso permiso = new Permiso();
	            	permiso.setIdpermiso( rs.getInt( "id_permiso" ) );
	            	permiso.setIdcandidato( rs.getInt( "id_candidato" ) );
	            	permiso.setIdadmin( rs.getInt( "id_administrador" ) );
	            	permiso.setFechapermiso( rs.getString( "fecha_permiso" ) );
	            	permiso.setHorapermiso( rs.getString( "hora_permiso" ) );
	                lista.add(permiso);
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
    public static Permiso buscarCandidato( int idcandid, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM permiso WHERE id_candidato = " + idcandid + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Permiso permiso = new Permiso();
            	permiso.setIdpermiso( rs.getInt( "id_permiso" ) );
            	permiso.setIdcandidato( rs.getInt( "id_candidato" ) );
            	permiso.setIdadmin( rs.getInt( "id_administrador" ) );
            	permiso.setFechapermiso( rs.getString( "fecha_permiso" ) );
            	permiso.setHorapermiso( rs.getString( "hora_permiso" ) );
                return permiso;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Permiso buscarIdPermiso( int idpermiso, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM permiso WHERE id_permiso = " + idpermiso + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Permiso permiso = new Permiso();
            	permiso.setIdpermiso( rs.getInt( "id_permiso" ) );
            	permiso.setIdcandidato( rs.getInt( "id_candidato" ) );
            	permiso.setIdadmin( rs.getInt( "id_administrador" ) );
            	permiso.setFechapermiso( rs.getString( "fecha_permiso" ) );
            	permiso.setHorapermiso( rs.getString( "hora_permiso" ) );
                return permiso;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Permiso permiso, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE permiso "
                + "SET id_candidato = " + permiso.getIdcandidato()+ ", "
                + "id_administrador = " + permiso.getIdadmin()+ ", "
                + "fecha_permiso = '" + permiso.getFechapermiso()+ "', "
                + "hora_permiso = '" + permiso.getHorapermiso()+ "' "
                +"WHERE id_permiso= "+permiso.getIdpermiso();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM permiso "
                +"WHERE id_permiso = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE permiso";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}