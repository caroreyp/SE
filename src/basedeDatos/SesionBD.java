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
public class SesionBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Sesion sesio, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO sesion ("
                + "fecha_sesion,"
                + "hora_inicial,"
                + "hora_final"
                + ") VALUES ("
                + "'" + sesio.getFechasesion() + "', "
                + "'" + sesio.getHorainicial() + "', "
                + sesio.getHorafinal() + " "
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Sesion> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesion ORDER BY id_sesion ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Sesion> lista = new ArrayList<Sesion>();
        try {
            if(rs.first())
			{
				do
				{
					Sesion sesio = new Sesion();
					sesio.setIdsesion( rs.getInt( "id_sesion" ) );
					sesio.setFechasesion( rs.getString( "fecha_sesion" ) );
					sesio.setHorainicial( rs.getString( "hora_inicial" ) );
					sesio.setHorafinal( rs.getString( "hora_final" ) );
	                lista.add(sesio);
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
    public static Sesion buscarFechaHora( String fechasesion, String horasesion, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesion WHERE fecha_sesion = '" + fechasesion + "' AND hora_inicial = '"+horasesion+"'";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Sesion sesio = new Sesion();
				sesio.setIdsesion( rs.getInt( "id_sesion" ) );
				sesio.setFechasesion( rs.getString( "fecha_sesion" ) );
				sesio.setHorainicial( rs.getString( "hora_inicial" ) );
				sesio.setHorafinal( rs.getString( "hora_final" ) );
                return sesio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Sesion buscarIdSesion( int idsesion, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesion WHERE id_sesion = " + idsesion + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Sesion sesio = new Sesion();
				sesio.setIdsesion( rs.getInt( "id_sesion" ) );
				sesio.setFechasesion( rs.getString( "fecha_sesion" ) );
				sesio.setHorainicial( rs.getString( "hora_inicial" ) );
				sesio.setHorafinal( rs.getString( "hora_final" ) );
                return sesio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Sesion sesio, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE sesion "
                + "SET fecha_sesion = '" + sesio.getFechasesion()+ "', "
                + "hora_inicial = '" + sesio.getHorainicial()+ "', "
                + "hora_final = '" + sesio.getHorafinal()+ "' "
                +"WHERE id_sesion= "+sesio.getIdsesion();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM sesion "
                +"WHERE id_sesion = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE sesion";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}