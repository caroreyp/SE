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
public class CompetenciaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Competencia competen, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO competencia ("
                + "nombre,"
                + "descripcion"
                + ") VALUES ("
                + "'" + competen.getNombre() + "', "
                + "'" + competen.getDescripcion() + "'"
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Competencia> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competencia ORDER BY id_competencia ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Competencia> lista = new ArrayList<Competencia>();
        try {
            if(rs.first())
			{
				do
				{
					Competencia competen = new Competencia();
					competen.setIdcompetencia( rs.getInt( "id_competencia" ) );
					competen.setNombre( rs.getString( "nombre" ) );
					competen.setDescripcion( rs.getString( "descripcion" ) );
	                lista.add(competen);
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
    public static Competencia buscarNombre( String nombrecaract, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competencia WHERE nombre = '" + nombrecaract + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Competencia competen = new Competencia();
				competen.setIdcompetencia( rs.getInt( "id_competencia" ) );
				competen.setNombre( rs.getString( "nombre" ) );
				competen.setDescripcion( rs.getString( "descripcion" ) );
                return competen;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Competencia buscarIdCompetencia( int idcompet, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM competencia WHERE id_competencia = " + idcompet + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Competencia competen = new Competencia();
				competen.setIdcompetencia( rs.getInt( "id_competencia" ) );
				competen.setNombre( rs.getString( "nombre" ) );
				competen.setDescripcion( rs.getString( "descripcion" ) );
                return competen;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Competencia competen, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE competencia "
                + "SET nombre = '" + competen.getNombre()+ "', "
                + "descripcion = '" + competen.getDescripcion()+ "' "
                +" WHERE id_competencia= "+competen.getIdcompetencia();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM competencia "
                +"WHERE id_competencia = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE competencia";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}