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
public class AdministradorBD 
{
	/**
	 * Metodo que inserta una nueva tupla con los parametros recibidos.
	 */
	public static void insertar( Administrador admin, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "INSERT INTO administrador ("
                + "documento_identidad, "
                + "nombres, "
                + "apellidos, "
                + "password, "
                + "numero_telefonico, "
                + "correo_electronico"
                + ") VALUES ("
                + admin.getDocumentoidentidad() + ", "
                + "'" + admin.getNombres() + "', "
                + "'" + admin.getApellidos() + "', "
                + "'" + admin.getPassword() + "', "
                + admin.getNumerotelefonico() + ", "
                + "'" + admin.getCorreoelectronico() + "' "
                + ")";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

	/**
	 * Metodo que retorna en una lista las tuplas almacenadas en la tabla.
	 */
    public static List<Administrador> listar( Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM administrador ORDER BY id_administrador ASC";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);
        List<Administrador> lista = new ArrayList<Administrador>();
        try {
            if(rs.first())
			{
				do
				{
					Administrador admin = new Administrador();
	            	admin.setIdadmin( rs.getInt( "id_administrador" ) );
	            	admin.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
	            	admin.setNombres( rs.getString( "nombres" ) );
	                admin.setApellidos( rs.getString( "apellidos" ) );
	                admin.setPassword( rs.getString( "password" ) );
	                admin.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
	                admin.setCorreoelectronico( rs.getString( "correo_electronico" ) );
	                lista.add(admin);
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
    public static Administrador buscarNombres( String nombreadmin, String apellidoadmin, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM administrador WHERE nombres = '" + nombreadmin + "' AND apellidos = '" + apellidoadmin + "' LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Administrador admin = new Administrador();
            	admin.setIdadmin( rs.getInt( "id_administrador" ) );
            	admin.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
            	admin.setNombres( rs.getString( "nombres" ) );
                admin.setApellidos( rs.getString( "apellidos" ) );
                admin.setPassword( rs.getString( "password" ) );
                admin.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
                admin.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Administrador buscarDocumentoIdentidad( String cedulaadmin, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM administrador WHERE documento_identidad = " + cedulaadmin + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Administrador admin = new Administrador();
            	admin.setIdadmin( rs.getInt( "id_administrador" ) );
            	admin.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
            	admin.setNombres( rs.getString( "nombres" ) );
                admin.setApellidos( rs.getString( "apellidos" ) );
                admin.setPassword( rs.getString( "password" ) );
                admin.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
                admin.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * Metodo que busca y retorna una tupla segun los paramentros recibidos.
	 */
    public static Administrador buscarIdAdministrador( int idadmin, Conector conector ) throws ErrorConector
    {
        String consultaSQL = "SELECT * FROM administrador WHERE id_administrador = " + idadmin + " LIMIT 1";
        ResultSet rs = conector.consultarEnBaseDatos(consultaSQL);        
        try {
            if( rs.next() ){
            	Administrador admin = new Administrador();
            	admin.setIdadmin( rs.getInt( "id_administrador" ) );
            	admin.setDocumentoidentidad( rs.getString( "documento_identidad" ) );
            	admin.setNombres( rs.getString( "nombres" ) );
                admin.setApellidos( rs.getString( "apellidos" ) );
                admin.setPassword( rs.getString( "password" ) );
                admin.setNumerotelefonico( rs.getString( "numero_telefonico" ) );
                admin.setCorreoelectronico( rs.getString( "correo_electronico" ) );
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Metodo que actualiza una tupla segun los paramentros recibidos.
	 */
    public static void actualizar( Administrador admin, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "UPDATE administrador "
                + "SET documento_identidad = " + admin.getDocumentoidentidad()+ ", "
                + "nombres = '" + admin.getNombres()+ "', "
                + "apellidos = '" + admin.getApellidos()+ "', "
                + "password = '" + admin.getPassword()+ "', "
                + "numero_telefonico = " + admin.getNumerotelefonico()+ ", "
                + "correo_electronico = '" + admin.getCorreoelectronico()+ "' "
                +"WHERE id_administrador = " + admin.getIdadmin();
        conector.ejecutarEnBaseDatos(lineaSQL);
    }

    /**
	 * Metodo que elimina una tupla segun los paramentros recibidos.
	 */
    public static void eliminar( int id, Conector conector ) throws ErrorConector
    {
        String lineaSQL = "DELETE FROM administrador "
                +"WHERE id_administrador = " + id;
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
    
    /**
	 * Metodo que trunca o vacia la tabla borrando todas las tuplas.
	 */
    public static void truncarTabla(Conector conector) throws ErrorConector
    {
        String lineaSQL = "TRUNCATE TABLE administrador";
        conector.ejecutarEnBaseDatos(lineaSQL);
    }
}