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
public class CompetenciaEscalaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( CompetenciaEscala competenescal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO competenciaescala ("
                + "id_competencia, "
                + "id_escala"
                + ") VALUES ("
                + competenescal.getIdcompetencia() + ", "
                + competenescal.getIdescala()
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<CompetenciaEscala> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competenciaescala";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaEscala> lista = new ArrayList<CompetenciaEscala>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaEscala competenescal = new CompetenciaEscala();
					competenescal.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenescal.setIdescala( rs.getInt( "id_escala" ) );
	                lista.add(competenescal);
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
    public static List<CompetenciaEscala> buscarCompetenciasEscala( int idescal, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM competenciaescala WHERE id_escala = " + idescal;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaEscala> lista = new ArrayList<CompetenciaEscala>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaEscala competenescal = new CompetenciaEscala();
					competenescal.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenescal.setIdescala( rs.getInt( "id_escala" ) );
	                lista.add(competenescal);
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
    public static List<CompetenciaEscala> buscarEscalasCompetencia( int idcompeten, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM competenciaescala WHERE id_competencia = " + idcompeten;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<CompetenciaEscala> lista = new ArrayList<CompetenciaEscala>();
        try {
            if(rs.first())
			{
				do
				{
					CompetenciaEscala competenescal = new CompetenciaEscala();
					competenescal.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competenescal.setIdescala( rs.getInt( "id_escala" ) );
	                lista.add(competenescal);
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
    public static CompetenciaEscala consultar( int idcompetencia, int idescala, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competenciaescala WHERE id_competencia = " + idcompetencia
                                               +" AND id_escala = " + idescala;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	CompetenciaEscala competenescal = new CompetenciaEscala();
				competenescal.setIdcompetencia( rs.getInt( "id_competencia" ) );
				competenescal.setIdescala( rs.getInt( "id_escala" ) );
                return competenescal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idcompetencia, int idescala, CompetenciaEscala competenescal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE competenciaescala "
                + "SET id_competencia =" + competenescal.getIdcompetencia()+ ", "
                + "id_escala =" + competenescal.getIdescala()
		        + " WHERE id_competencia = " + idcompetencia
                + " AND id_escala = " + idescala;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idcompetencia, int idescala, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM competenciaescala "
                +"WHERE id_competencia = " + idcompetencia
                +" AND id_escala = " + idescala;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE competenciaescala";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}