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
public class SesionCandidatoBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
    public static void insertar( SesionCandidato nuevasesion, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO sesioncandidato ("
                + "id_candidato, "
                + "id_sesion"
                + ") VALUES ("
                + nuevasesion.getIdcandidato() + ", "
                + nuevasesion.getIdsesion()
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<SesionCandidato> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesioncandidato";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<SesionCandidato> lista = new ArrayList<SesionCandidato>();
        try {
            if(rs.first())
			{
				do
				{
					SesionCandidato sesioncand = new SesionCandidato();
	            	sesioncand.setIdcandidato( rs.getInt( "id_candidato" ) );
	            	sesioncand.setIdsesion( rs.getInt( "id_sesion" ) );
	                lista.add(sesioncand);
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
    public static SesionCandidato consultar( int idcandidato, int idsesion, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM sesioncandidato WHERE id_candidato = " + idcandidato
                                               +" AND id_sesion = " + idsesion;
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	SesionCandidato sesioncand = new SesionCandidato();
            	sesioncand.setIdcandidato( rs.getInt( "id_candidato" ) );
            	sesioncand.setIdsesion( rs.getInt( "id_sesion" ) );
                return sesioncand;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( int idcandidato, int idsesion, SesionCandidato sesioncand, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE sesioncandidato "
                + "SET id_candidato =" + sesioncand.getIdcandidato()+ ", "
                + "id_sesion =" + sesioncand.getIdsesion()
		        + " WHERE id_candidato = " + idcandidato
                + " AND id_sesion = " + idsesion;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int idcandidato, int idsesion, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM sesioncandidato "
                +"WHERE id_candidato = " + idcandidato
                +" AND id_sesion = " + idsesion;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE sesioncandidato";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}