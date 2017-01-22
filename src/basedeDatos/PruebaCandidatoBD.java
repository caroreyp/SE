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
public class PruebaCandidatoBD
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( PruebaCandidato nuevaprueba, Conector conector ) throws ErrorConector
    {
    	String convocatoria="";
        if((nuevaprueba.getIdconvocator()) == -1)
        {
        	convocatoria="NULL";
        }
        else
        {
        	convocatoria+=nuevaprueba.getIdconvocator();
        }
        String lineaSQL = "INSERT INTO pruebacandidato ("
                + "id_prueba, "
                + "id_candidato, "
                + "id_resultado,"
                + "id_convocatoria"
                + ") VALUES ("
                + nuevaprueba.getIdprueba() + ", "
                + nuevaprueba.getIdcandidato() + ", "
                + nuevaprueba.getIdresultado() + ", "
                + convocatoria
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<PruebaCandidato> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pruebacandidato";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<PruebaCandidato> lista = new ArrayList<PruebaCandidato>();
        try {
            if(rs.first())
			{
				do
				{
					PruebaCandidato pruebacand = new PruebaCandidato();
	            	pruebacand.setIdprueba( rs.getInt( "id_prueba" ) );
	            	pruebacand.setIdcandidato( rs.getInt( "id_candidato" ) );
	            	pruebacand.setIdresultado( rs.getInt( "id_resultado" ) );
	            	if( rs.getString( "id_convocatoria" ) == null )
	                {
	            		pruebacand.setIdconvocator( -1 );
	                }
	                else
	                {
	                	pruebacand.setIdconvocator( rs.getInt( "id_convocatoria" ) );
	                }
	                lista.add(pruebacand);
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
    public static List<PruebaCandidato> listarPuntajeDescendente( int idconvocatoria, Conector conector ) throws ErrorConector
    {
    	String consultaSQL = "";
        if((idconvocatoria) == -1)
        {
        	consultaSQL = "SELECT p.id_prueba, p.id_candidato, p.id_resultado, p.id_convocatoria "
                +"FROM pruebacandidato p, resultado r, prueba b "
                +"WHERE p.id_resultado = r.id_resultado "
                +"AND p.id_prueba = b.id_prueba "
                +"AND b.nombre = 'Prueba Libre' "
                +"ORDER BY r.puntaje DESC";
        }
        else
        {
        	consultaSQL = "SELECT p.id_prueba, p.id_candidato, p.id_resultado, p.id_convocatoria "
                +"FROM pruebacandidato p, resultado r "
                +"WHERE p.id_resultado = r.id_resultado "
                +"AND p.id_convocatoria = "+idconvocatoria+" "
                +"ORDER BY r.puntaje DESC";
        }
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<PruebaCandidato> lista = new ArrayList<PruebaCandidato>();
        try {
            if(rs.first())
			{
				do
				{
					PruebaCandidato pruebacand = new PruebaCandidato();
	            	pruebacand.setIdprueba( rs.getInt( "id_prueba" ) );
	            	pruebacand.setIdcandidato( rs.getInt( "id_candidato" ) );
	            	pruebacand.setIdresultado( rs.getInt( "id_resultado" ) );
	            	if( rs.getString( "id_convocatoria" ) == null )
	                {
	            		pruebacand.setIdconvocator( -1 );
	                }
	                else
	                {
	                	pruebacand.setIdconvocator( rs.getInt( "id_convocatoria" ) );
	                }
	                lista.add(pruebacand);
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
    public static PruebaCandidato buscar( int idprueba, int idcandidato, int idresultado, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM pruebacandidato WHERE id_prueba = " + idprueba
                                               +" AND id_candidato = " + idcandidato
                                               +" AND id_resultado = " + idresultado;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	PruebaCandidato pruebacand = new PruebaCandidato();
            	pruebacand.setIdprueba( rs.getInt( "id_prueba" ) );
            	pruebacand.setIdcandidato( rs.getInt( "id_candidato" ) );
            	pruebacand.setIdresultado( rs.getInt( "id_resultado" ) );
            	if( rs.getString( "id_convocatoria" ) == null )
                {
            		pruebacand.setIdconvocator( -1 );
                }
                else
                {
                	pruebacand.setIdconvocator( rs.getInt( "id_convocatoria" ) );
                }
                return pruebacand;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idprueba, int idcandidato, int idresultado, PruebaCandidato pruebacand, Conector conector ) throws ErrorConector
    {
    	String convocatoria="";
        if((pruebacand.getIdconvocator()) == -1)
        {
        	convocatoria="NULL";
        }
        else
        {
        	convocatoria+=pruebacand.getIdconvocator();
        }
        String lineaSQL = "UPDATE pruebacandidato "
                + "SET id_prueba =" + pruebacand.getIdprueba()+ ", "
                + "id_candidato =" + pruebacand.getIdprueba()+ ", "
                + "id_resultado =" + pruebacand.getIdprueba()+ ", "
                + "id_convocatoria =" + convocatoria
		        + " WHERE id_prueba = " + idprueba
                + " AND id_candidato = " + idcandidato
                + " AND id_resultado = " + idresultado;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idprueba, int idcandidato, int idresultado, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM pruebacandidato "
                +"WHERE id_prueba = " + idprueba
                +" AND id_candidato = " + idcandidato
                +" AND id_resultado = " + idresultado;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE pruebacandidato";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}