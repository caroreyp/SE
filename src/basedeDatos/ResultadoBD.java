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
public class ResultadoBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
	public static void insertar( Resultado resultad, Conector conector ) throws ErrorConector
    {
		String lineaSQL = "INSERT INTO resultado ("
            + "puntaje, "
            + "observaciones, "
            + "fecha_prueba, "
            + "nombre_dia, "
            + "hora_inicial, "
            + "hora_final,"
            + "duracion_prueba,"
            + "revisado,"
            + "comentarios"
            + ") VALUES ("
            + resultad.getPuntaje() + ", "
            + "'" + resultad.getObservaciones() + "', "
            + "'" + resultad.getFechaprueba() + "', "
            + "'" + resultad.getNombredia() + "', "
            + "'" + resultad.getHorainicial() + "', "
            + "'" + resultad.getHorafinal() + "', "
            + resultad.getDuracionprueba() + ", "
            + "'" + resultad.getRevisado() + "', "
            + "'" + resultad.getComentarios() + "' "
            + ")";
    conector.ejecutarEnBaseDatos(lineaSQL);
    }

	/**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
	public static List<Resultado> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultado ORDER BY id_resultado ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Resultado> lista = new ArrayList<Resultado>();
        try {
            if(rs.first())
			{
				do
				{
					Resultado resultad = new Resultado();
					resultad.setIdresultado( rs.getInt( "id_resultado" ) );
					resultad.setPuntaje( rs.getDouble( "puntaje" ) );
					resultad.setObservaciones( rs.getString( "observaciones" ) );
					resultad.setFechaprueba( rs.getString( "fecha_prueba" ) );
					resultad.setNombredia( rs.getString( "nombre_dia" ) );
					resultad.setHorainicial( rs.getString( "hora_inicial" ) );
					resultad.setHorafinal( rs.getString( "hora_final" ) );
					resultad.setDuracionprueba( rs.getInt( "duracion_prueba" ) );
					resultad.setRevisado( rs.getString( "revisado" ) );
					resultad.setComentarios( rs.getString( "comentarios" ) );
	                lista.add(resultad);
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
    public static Resultado buscarIdResultado( int idresultado, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultado WHERE id_resultado = " + idresultado + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Resultado resultad = new Resultado();
				resultad.setIdresultado( rs.getInt( "id_resultado" ) );
				resultad.setPuntaje( rs.getDouble( "puntaje" ) );
				resultad.setObservaciones( rs.getString( "observaciones" ) );
				resultad.setFechaprueba( rs.getString( "fecha_prueba" ) );
				resultad.setNombredia( rs.getString( "nombre_dia" ) );
				resultad.setHorainicial( rs.getString( "hora_inicial" ) );
				resultad.setHorafinal( rs.getString( "hora_final" ) );
				resultad.setDuracionprueba( rs.getInt( "duracion_prueba" ) );
				resultad.setRevisado( rs.getString( "revisado" ) );
				resultad.setComentarios( rs.getString( "comentarios" ) );
                return resultad;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Resultado buscarResultadoFechaHoraPuntaje( String  fechaprueba, String horainicial, String horafinal, double puntajeresul, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultado WHERE fecha_prueba = '" + fechaprueba+ "'"
        											 +" AND hora_inicial = '" + horainicial+ "'"
        											 +" AND hora_final = '" + horafinal+ "'"
        											 +" AND puntaje = " + puntajeresul+ " ";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Resultado resultad = new Resultado();
				resultad.setIdresultado( rs.getInt( "id_resultado" ) );
				resultad.setPuntaje( rs.getDouble( "puntaje" ) );
				resultad.setObservaciones( rs.getString( "observaciones" ) );
				resultad.setFechaprueba( rs.getString( "fecha_prueba" ) );
				resultad.setNombredia( rs.getString( "nombre_dia" ) );
				resultad.setHorainicial( rs.getString( "hora_inicial" ) );
				resultad.setHorafinal( rs.getString( "hora_final" ) );
				resultad.setDuracionprueba( rs.getInt( "duracion_prueba" ) );
				resultad.setRevisado( rs.getString( "revisado" ) );
				resultad.setComentarios( rs.getString( "comentarios" ) );
                return resultad;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * Metodo que retorna en una lista las tuplas seleccionadas segun los parametros.
	 */
    public static List<Resultado> buscarNoRevisados( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM resultado WHERE revisado = 'No'";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Resultado> lista = new ArrayList<Resultado>();
        try {
            if(rs.first())
			{
				do
				{
					Resultado resultad = new Resultado();
					resultad.setIdresultado( rs.getInt( "id_resultado" ) );
					resultad.setPuntaje( rs.getDouble( "puntaje" ) );
					resultad.setObservaciones( rs.getString( "observaciones" ) );
					resultad.setFechaprueba( rs.getString( "fecha_prueba" ) );
					resultad.setNombredia( rs.getString( "nombre_dia" ) );
					resultad.setHorainicial( rs.getString( "hora_inicial" ) );
					resultad.setHorafinal( rs.getString( "hora_final" ) );
					resultad.setDuracionprueba( rs.getInt( "duracion_prueba" ) );
					resultad.setRevisado( rs.getString( "revisado" ) );
					resultad.setComentarios( rs.getString( "comentarios" ) );
	                lista.add(resultad);
				} while(rs.next());
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Resultado resultad, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE resultado "
                + "SET puntaje = " + resultad.getPuntaje()+ ", "
                + "observaciones = '" + resultad.getObservaciones()+ "', "
                + "fecha_prueba = '" + resultad.getFechaprueba()+ "', "
                + "nombre_dia = '" + resultad.getNombredia()+ "', "
                + "hora_inicial = '" + resultad.getHorainicial()+ "', "
                + "hora_final = '" + resultad.getHorafinal()+ "', "
                + "duracion_prueba = " + resultad.getDuracionprueba()+ ", "
                + "revisado = '" + resultad.getRevisado()+ "', "
                + "comentarios = '" + resultad.getComentarios()+ "' "
                +"WHERE id_resultado = " + resultad.getIdresultado();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM resultado "
                +"WHERE id_resultado = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE resultado";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}