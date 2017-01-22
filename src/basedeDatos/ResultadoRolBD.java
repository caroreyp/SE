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
public class ResultadoRolBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( ResultadoRol resultadorol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO resultadorol ("
                + "id_rol, "
                + "id_resultado"
                + ") VALUES ("
                + resultadorol.getIdrol() + ", "
                + resultadorol.getIdresultado()
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<ResultadoRol> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultadorol";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<ResultadoRol> lista = new ArrayList<ResultadoRol>();
        try {
            if(rs.first())
			{
				do
				{
					ResultadoRol resultadorol = new ResultadoRol();
					resultadorol.setIdrol( rs.getInt( "id_rol" ) );
					resultadorol.setIdresultado( rs.getInt( "id_resultado" ) );
	                lista.add(resultadorol);
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
    public static List<ResultadoRol> buscarRolesResultado( int idresultado, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM resultadorol WHERE id_resultado = " + idresultado;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<ResultadoRol> lista = new ArrayList<ResultadoRol>();
        try {
            if(rs.first())
			{
				do
				{
					ResultadoRol resultadorol = new ResultadoRol();
					resultadorol.setIdrol( rs.getInt( "id_rol" ) );
					resultadorol.setIdresultado( rs.getInt( "id_resultado" ) );
	                lista.add(resultadorol);
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
    public static ResultadoRol consultar( int idrol, int idresultado, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultadorol WHERE id_rol = " + idrol
                                               +" AND id_resultado = " + idresultado;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	ResultadoRol resultadorol = new ResultadoRol();
				resultadorol.setIdrol( rs.getInt( "id_rol" ) );
				resultadorol.setIdresultado( rs.getInt( "id_resultado" ) );
                return resultadorol;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idrol, int idresultado, ResultadoRol resultadorol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE resultadorol "
                + "SET id_rol =" + resultadorol.getIdrol()+ ", "
                + "id_resultado =" + resultadorol.getIdresultado()
		        + " WHERE id_rol = " + idrol
                + " AND id_resultado = " + idresultado;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idrol, int idresultado, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM resultadorol "
                +"WHERE id_rol = " + idrol
                +" AND id_resultado = " + idresultado;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE resultadorol";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}