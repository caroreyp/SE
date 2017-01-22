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
public class CompetenciaRolBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( CompetenciaRol competenrol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO competenciarol ("
                + "id_competencia, "
                + "id_rol"
                + ") VALUES ("
                + competenrol.getIdcompetencia() + ", "
                + competenrol.getIdrol()
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<CompetenciaRol> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competenciarol";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaRol> lista = new ArrayList<CompetenciaRol>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaRol competenrol = new CompetenciaRol();
					competenrol.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenrol.setIdrol( rs.getInt( "id_rol" ) );
	                lista.add(competenrol);
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
    public static List<CompetenciaRol> buscarCompetenciasRol( int idrol, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM competenciarol WHERE id_rol = " + idrol;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaRol> lista = new ArrayList<CompetenciaRol>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaRol competenrol = new CompetenciaRol();
					competenrol.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenrol.setIdrol( rs.getInt( "id_rol" ) );
	                lista.add(competenrol);
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
    public static List<CompetenciaRol> buscarRolesCompetencia( int idcompeten, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM competenciarol WHERE id_competencia = " + idcompeten;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaRol> lista = new ArrayList<CompetenciaRol>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaRol competenrol = new CompetenciaRol();
					competenrol.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenrol.setIdrol( rs.getInt( "id_rol" ) );
	                lista.add(competenrol);
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
    public static CompetenciaRol consultar( int idcompetencia, int idrol, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competenciarol WHERE id_competencia = " + idcompetencia
                                               +" AND id_rol = " + idrol;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	CompetenciaRol competenrol = new CompetenciaRol();
            	competenrol.setIdcompetencia( rs.getInt( "id_competencia" ) );
            	competenrol.setIdrol( rs.getInt( "id_rol" ) );
                return competenrol;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idcompetencia, int idrol, CompetenciaRol competenrol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE competenciarol "
                + "SET id_competencia =" + competenrol.getIdcompetencia()+ ", "
                + "id_rol =" + competenrol.getIdrol()
		        + " WHERE id_competencia = " + idcompetencia
                + " AND id_rol = " + idrol;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idcompetencia, int idrol, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM competenciarol "
                +"WHERE id_competencia = " + idcompetencia
                +" AND id_rol = " + idrol;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE competenciarol";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}