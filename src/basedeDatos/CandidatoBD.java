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
public class CandidatoBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
	public static void insertar( Candidato candidat, Conector conector ) throws ErrorConector
    {
		String lineaSQL = "INSERT INTO candidato ("
            + "documento_identidad, "
            + "nombres, "
            + "apellidos, "
            + "genero, "
            + "fecha_nacimiento, "
            + "numero_telefonico, "
            + "correo_electronico"
            + ") VALUES ("
            + candidat.getDocumentoidentidad() + ", "
            + "'" + candidat.getNombres() + "', "
            + "'" + candidat.getApellidos() + "', "
            + "'" + candidat.getGenero() + "', "
            + "'" + candidat.getFechanacimiento() + "', "
            + candidat.getNumerotelefonico() + ", "
            + "'" + candidat.getCorreoelectronico() + "' "
            + ")";
    conector.ejecutarEnBaseDatos(lineaSQL);
    }

	/**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
	public static List<Candidato> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM candidato ORDER BY id_candidato ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Candidato> lista = new ArrayList<Candidato>();
        try {
            if(rs.first())
			{
				do
				{
					Candidato candidat = new Candidato();
					candidat.setIdcandidato( rs.getInt( "id_candidato" ) );
					candidat.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
					candidat.setNombres( rs.getString( "nombres" ) );
					candidat.setApellidos( rs.getString( "apellidos" ) );
					candidat.setGenero( rs.getString( "genero" ) );
					candidat.setFechanacimiento( rs.getString( "fecha_nacimiento" ) );
					candidat.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
					candidat.setCorreoelectronico( rs.getString( "correo_electronico" ) );
	                lista.add(candidat);
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
    public static Candidato buscarNombres( String nombrecandid, String apellidocandid, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM candidato WHERE nombres = '" + nombrecandid + "' AND apellidos = '" + apellidocandid + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Candidato candidat = new Candidato();
				candidat.setIdcandidato( rs.getInt( "id_candidato" ) );
				candidat.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
				candidat.setNombres( rs.getString( "nombres" ) );
				candidat.setApellidos( rs.getString( "apellidos" ) );
				candidat.setGenero( rs.getString( "genero" ) );
				candidat.setFechanacimiento( rs.getString( "fecha_nacimiento" ) );
				candidat.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
				candidat.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return candidat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Candidato buscarDocumentoIdentidad( String cedulacandid, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM candidato WHERE documento_identidad = " + cedulacandid + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Candidato candidat = new Candidato();
				candidat.setIdcandidato( rs.getInt( "id_candidato" ) );
				candidat.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
				candidat.setNombres( rs.getString( "nombres" ) );
				candidat.setApellidos( rs.getString( "apellidos" ) );
				candidat.setGenero( rs.getString( "genero" ) );
				candidat.setFechanacimiento( rs.getString( "fecha_nacimiento" ) );
				candidat.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
				candidat.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return candidat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Candidato buscarIdCandidato( int idcandidat, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM candidato WHERE id_candidato = " + idcandidat + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Candidato candidat = new Candidato();
				candidat.setIdcandidato( rs.getInt( "id_candidato" ) );
				candidat.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
				candidat.setNombres( rs.getString( "nombres" ) );
				candidat.setApellidos( rs.getString( "apellidos" ) );
				candidat.setGenero( rs.getString( "genero" ) );
				candidat.setFechanacimiento( rs.getString( "fecha_nacimiento" ) );
				candidat.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
				candidat.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return candidat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Candidato candidat, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE candidato "
                + "SET documento_identidad = " + candidat.getDocumentoidentidad()+ ", "
                + "nombres = '" + candidat.getNombres()+ "', "
                + "apellidos = '" + candidat.getApellidos()+ "', "
                + "genero = '" + candidat.getGenero()+ "', "
                + "fecha_nacimiento = '" + candidat.getFechanacimiento()+ "', "
                + "numero_telefonico = " + candidat.getNumerotelefonico()+ ", "
                + "correo_electronico = '" + candidat.getCorreoelectronico()+ "' "
                +"WHERE id_candidato = " + candidat.getIdcandidato();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM candidato "
                +"WHERE id_candidato = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE candidato";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}