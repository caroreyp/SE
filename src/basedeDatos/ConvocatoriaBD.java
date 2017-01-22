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
public class ConvocatoriaBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
	public static void insertar( Convocatoria convocator, Conector conector ) throws ErrorConector
    {
		String lineaSQL = "INSERT INTO convocatoria ("
            + "id_administrador, "
            + "nombre, "
            + "fecha_inicial, "
            + "hora_inicial, "
            + "fecha_final, "
            + "hora_final, "
            + "finalizada"
            + ") VALUES ("
            + convocator.getIdadmin() + ", "
            + "'" + convocator.getNombre() + "', "
            + "'" + convocator.getFechainicial() + "', "
            + "'" + convocator.getHorainicial() + "', "
            + "'" + convocator.getFechafinal() + "', "
            + "'" + convocator.getHorafinal() + "', "
            + "'" + convocator.getFinalizada() + "' "
            + ")";
    conector.ejecutarEnBaseDatos(lineaSQL);
    }

	/**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
	public static List<Convocatoria> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM convocatoria ORDER BY id_convocatoria ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Convocatoria> lista = new ArrayList<Convocatoria>();
        try {
            if(rs.first())
			{
				do
				{
					Convocatoria convocator = new Convocatoria();
					convocator.setIdconvocatoria( rs.getInt( "id_convocatoria" ) );
					convocator.setIdadmin( rs.getInt( "id_administrador" ) );
					convocator.setNombre( rs.getString( "nombre" ) );
					convocator.setFechainicial( rs.getString( "fecha_inicial" ) );
					convocator.setHorainicial( rs.getString( "hora_inicial" ) );
					convocator.setFechafinal( rs.getString( "fecha_final" ) );
					convocator.setHorafinal( rs.getString( "hora_final" ) );
					convocator.setFinalizada( rs.getString( "finalizada" ) );
	                lista.add(convocator);
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
	public static List<Convocatoria> buscarNoFinalizadas( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM convocatoria WHERE finalizada = 'No'";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Convocatoria> lista = new ArrayList<Convocatoria>();
        try {
            if(rs.first())
			{
				do
				{
					Convocatoria convocator = new Convocatoria();
					convocator.setIdconvocatoria( rs.getInt( "id_convocatoria" ) );
					convocator.setIdadmin( rs.getInt( "id_administrador" ) );
					convocator.setNombre( rs.getString( "nombre" ) );
					convocator.setFechainicial( rs.getString( "fecha_inicial" ) );
					convocator.setHorainicial( rs.getString( "hora_inicial" ) );
					convocator.setFechafinal( rs.getString( "fecha_final" ) );
					convocator.setHorafinal( rs.getString( "hora_final" ) );
					convocator.setFinalizada( rs.getString( "finalizada" ) );
	                lista.add(convocator);
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
    public static Convocatoria buscarIdConvocatoria( int idconcocator, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM convocatoria WHERE id_convocatoria = " + idconcocator + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Convocatoria convocator = new Convocatoria();
				convocator.setIdconvocatoria( rs.getInt( "id_convocatoria" ) );
				convocator.setIdadmin( rs.getInt( "id_administrador" ) );
				convocator.setNombre( rs.getString( "nombre" ) );
				convocator.setFechainicial( rs.getString( "fecha_inicial" ) );
				convocator.setHorainicial( rs.getString( "hora_inicial" ) );
				convocator.setFechafinal( rs.getString( "fecha_final" ) );
				convocator.setHorafinal( rs.getString( "hora_final" ) );
				convocator.setFinalizada( rs.getString( "finalizada" ) );
                return convocator;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Convocatoria convocator, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE convocatoria "
                + "SET id_administrador = " + convocator.getIdadmin()+ ", "
                + "nombre = '" + convocator.getNombre()+ "', "
                + "fecha_inicial = '" + convocator.getFechainicial()+ "', "
                + "hora_inicial = '" + convocator.getHorainicial()+ "', "
                + "fecha_final = '" + convocator.getFechafinal()+ "', "
                + "hora_final = '" + convocator.getHorafinal()+ "', "
                + "finalizada = '" + convocator.getFinalizada()+ "' "
                +"WHERE id_convocatoria = " + convocator.getIdconvocatoria();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM convocatoria "
                +"WHERE id_convocatoria = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE convocatoria";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}