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
public class SesionAdministradorBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( SesionAdministrador nuevasesion, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO sesionadministrador ("
                + "id_administrador, "
                + "id_sesion"
                + ") VALUES ("
                + nuevasesion.getIdadministrador() + ", "
                + nuevasesion.getIdsesion()
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<SesionAdministrador> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesionadministrador";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<SesionAdministrador> lista = new ArrayList<SesionAdministrador>();
        try {
            if(rs.first())
			{
				do
				{
					SesionAdministrador sesionadmin = new SesionAdministrador();
					sesionadmin.setIdadministrador( rs.getInt( "id_administrador" ) );
					sesionadmin.setIdsesion( rs.getInt( "id_sesion" ) );
	                lista.add(sesionadmin);
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
    public static SesionAdministrador consultar( int idadministrador, int idsesion, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesionadministrador WHERE id_administrador = " + idadministrador
                                               +" AND id_sesion = " + idsesion;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	SesionAdministrador sesionadmin = new SesionAdministrador();
            	sesionadmin.setIdadministrador( rs.getInt( "id_administrador" ) );
            	sesionadmin.setIdsesion( rs.getInt( "id_sesion" ) );
                return sesionadmin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idadministrador, int idsesion, SesionAdministrador sesionadmin, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE sesionadministrador "
                + "SET id_administrador =" + sesionadmin.getIdadministrador()+ ", "
                + "id_sesion =" + sesionadmin.getIdsesion()
		        + " WHERE id_administrador = " + idadministrador
                + " AND id_sesion = " + idsesion;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idadministrador, int idsesion, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM sesionadministrador "
                +"WHERE id_administrador = " + idadministrador
                +" AND id_sesion = " + idsesion;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE sesionadministrador";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}