package basedeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mundo.Pregunta;

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
public class PreguntaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( Pregunta pregunt, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO pregunta ("
                + "orden_numerico,"
                + "texto"
                + ") VALUES ("
                + pregunt.getOrdennumerico() + ", "
                + "'" + pregunt.getTexto() + "' "
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Pregunta> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pregunta ORDER BY orden_numerico ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Pregunta> lista = new ArrayList<Pregunta>();
        try {
            if(rs.first())
			{
				do
				{
					Pregunta pregunt = new Pregunta();
	                pregunt.setIdpregunta( rs.getInt( "id_pregunta" ) );
	                pregunt.setOrdennumerico( rs.getInt( "orden_numerico" ) );
	                pregunt.setTexto( rs.getString( "texto" ) );
	                lista.add(pregunt);
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
    public static Pregunta buscarTexto( String textopreg, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pregunta WHERE texto = '" + textopreg + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Pregunta pregunt = new Pregunta();
                pregunt.setIdpregunta( rs.getInt( "id_pregunta" ) );
                pregunt.setOrdennumerico( rs.getInt( "orden_numerico" ) );
                pregunt.setTexto( rs.getString( "texto" ) );
                return pregunt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Pregunta buscarNumeroOrden( int ordenpreg, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pregunta WHERE orden_numerico = " + ordenpreg + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Pregunta pregunt = new Pregunta();
                pregunt.setIdpregunta( rs.getInt( "id_pregunta" ) );
                pregunt.setOrdennumerico( rs.getInt( "orden_numerico" ) );
                pregunt.setTexto( rs.getString( "texto" ) );
                return pregunt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Pregunta buscarIdPregunta( int idpregunt, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pregunta WHERE id_pregunta = " + idpregunt + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        try {
            if( rs.next() ){
            	Pregunta pregunt = new Pregunta();
                pregunt.setIdpregunta( rs.getInt( "id_pregunta" ) );
                pregunt.setOrdennumerico( rs.getInt( "orden_numerico" ) );
                pregunt.setTexto( rs.getString( "texto" ) );
                return pregunt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Pregunta pregunt, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE pregunta "
                + "SET orden_numerico ="+pregunt.getOrdennumerico()+ ", "
                + "texto = '" + pregunt.getTexto()+ "' "
                +"WHERE id_pregunta= "+pregunt.getIdpregunta();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM pregunta "
                +"WHERE id_pregunta = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE pregunta";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}