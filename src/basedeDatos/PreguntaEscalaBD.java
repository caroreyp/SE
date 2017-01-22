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
public class PreguntaEscalaBD
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( PreguntaEscala preguntescal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO preguntaescala ("
                + "id_escala,"
                + "id_pregunta,"
                + "respuesta"
                + ") VALUES ("
                + preguntescal.getIdescala() + ", "
                + preguntescal.getIdpregunta() + ", "
                + "'" + preguntescal.getRespuesta() + "' "
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<PreguntaEscala> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM preguntaescala";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<PreguntaEscala> lista = new ArrayList<PreguntaEscala>();
        try {
            if(rs.first())
			{
				do
				{
					PreguntaEscala preguntescal = new PreguntaEscala();
					preguntescal.setIdescala( rs.getInt( "id_escala" ) );
					preguntescal.setIdpregunta( rs.getInt( "id_pregunta" ) );
					preguntescal.setRespuesta( rs.getString( "respuesta" ) );
	                lista.add(preguntescal);
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
    public static List<PreguntaEscala> buscarPreguntasEscala( int idescala, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "SELECT * FROM preguntaescala WHERE id_escala = " + idescala;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<PreguntaEscala> lista = new ArrayList<PreguntaEscala>();
        try {
            if(rs.first())
			{
				do
				{
					PreguntaEscala preguntescal = new PreguntaEscala();
					preguntescal.setIdescala( rs.getInt( "id_escala" ) );
					preguntescal.setIdpregunta( rs.getInt( "id_pregunta" ) );
					preguntescal.setRespuesta( rs.getString( "respuesta" ) );
	                lista.add(preguntescal);
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
    public static PreguntaEscala consultar( int idescal, int idpregunt, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM preguntaescala WHERE id_escala = " + idescal
                                               +" AND id_pregunta = " + idpregunt;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	PreguntaEscala preguntescal = new PreguntaEscala();
				preguntescal.setIdescala( rs.getInt( "id_escala" ) );
				preguntescal.setIdpregunta( rs.getInt( "id_pregunta" ) );
				preguntescal.setRespuesta( rs.getString( "respuesta" ) );
                return preguntescal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idescal, int idpregunt, PreguntaEscala preguntescal, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE preguntaescala "
                + "SET id_escala =" + preguntescal.getIdescala()+ ", "
                + "id_pregunta =" + preguntescal.getIdpregunta()+ ", "
                + "respuesta = '" + preguntescal.getRespuesta()+"' "
		        + " WHERE id_escala = " + idescal
                + " AND id_pregunta = " + idpregunt;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idescal, int idpregunt, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM preguntaescala "
                +"WHERE id_escala = " + idescal
                +" AND id_pregunta = " + idpregunt;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE preguntaescala";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}