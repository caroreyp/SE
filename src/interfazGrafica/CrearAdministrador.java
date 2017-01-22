package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import basedeDatos.*;

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
public class CrearAdministrador extends JDialog implements ActionListener {
	
	private JTable tbdatos;
	private JButton btaceptar;
	private JButton btcancelar;
	private boolean salir;
	private InterfazInicio inicio;
	
	public CrearAdministrador(InterfazInicio interfaz){
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Crear Administrador - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 40;
		int anchoventana = 395+aumentoancho;
		int altoventana =249+15;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio = interfaz;
		salir = false;
		//tabla datos nombre, apellido y documento
		tbdatos = new JTable(new DatosAdminTableModel());
		// Create the text field editor
		JTextField caja = new JTextField();
	    DefaultCellEditor editor = new DefaultCellEditor(caja);
	    // Assign the editor to the second column
	    TableColumnModel tcm = tbdatos.getColumnModel();
	    tcm.getColumn(1).setCellEditor(editor);
		// Set column widths
	    tcm.getColumn(0).setPreferredWidth(150);
	    tcm.getColumn(1).setPreferredWidth(200);
	    tbdatos.setForeground(Color.BLACK);
	    tbdatos.setGridColor(new Color(35,91,92));
	    tbdatos.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
	    // Set row heighht
	    tbdatos.setRowHeight(20);
	    tbdatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdatos.setPreferredScrollableViewportSize(tbdatos.getPreferredSize());

	    btaceptar = new JButton("Aceptar");

		btcancelar = new JButton("Cancelar");
		
		btaceptar.addActionListener(this);
	    btaceptar.setActionCommand("Aceptar");
		btcancelar.addActionListener(this);
		btcancelar.setActionCommand("Cancelar");
		
		btcancelar.setPreferredSize(new Dimension(117+15,40+5));
		btcancelar.setBackground(new Color(60,116,107));
		btcancelar.setForeground(new Color(187,192,125));
		btcancelar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));
		
		btaceptar.setPreferredSize(new Dimension(117+15,40+5));
		btaceptar.setBackground(new Color(60,116,107));
		btaceptar.setForeground(new Color(187,192,125));
		btaceptar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));

		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (385+aumentoancho,210+20));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espaciosup = new JLabel(" ");
	    espaciosup.setPreferredSize(new Dimension(375+aumentoancho,6));	
	    panelcolor.add(espaciosup);
		panelcolor.add(new JScrollPane(tbdatos));
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(375+aumentoancho,6));	
	    panelcolor.add(espacios);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
		add(panelcolor);
		
	    JOptionPane.showMessageDialog(this,"La contraseña sera asignada por el sistema.","Contraseña Administrador", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	}
	
	public void dispose( ){
		if(salir == false){
			int respuesta = JOptionPane.showConfirmDialog(this,"¿Cancelar la creacion del administrador?","Confirmacion para salir",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	salir = true;
	            super.dispose();
	        }
		}
    }
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar")){
			boolean contestoTodo=true;
			String datoFaltante = "";
			int cantidadFilas = tbdatos.getRowCount();
			Administrador nuevo = new Administrador();
			for (int i = 0; i < cantidadFilas; i++){
				String datoAct = (String) tbdatos.getValueAt(i, 0);
				String datoActual = datoAct.trim();
				String repuestaAct = (String) tbdatos.getValueAt(i, 1);
				String repuestaActual = repuestaAct.trim();
				if(repuestaActual == null || repuestaActual.equalsIgnoreCase("")){
					datoFaltante = datoActual;
					contestoTodo=false;
					break;
				}
				else{
					if(datoActual.equalsIgnoreCase("Nombres")){
						nuevo.setNombres(repuestaActual);
					}
					if(datoActual.equalsIgnoreCase("Apellidos")){
						nuevo.setApellidos(repuestaActual);
					}
					if(datoActual.equalsIgnoreCase("# Documento Identidad")){
						try {
							boolean valornumerico = true;
							for (int j = 0; j < repuestaActual.length(); j++) 
							{
								char numero = repuestaActual.charAt(j);
								if(numero != '0' && numero != '1' && numero != '2' && numero != '3' && numero != '4' && numero != '5' && numero != '6' && numero != '7' && numero != '8' && numero != '9')
								{
									valornumerico = false;
									break;
								}
							}
							if(valornumerico == true)
							{
								nuevo.setDocumentoidentidad(repuestaActual);
							}
							else
							{
								int numerodocumento = Integer.parseInt(repuestaActual);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this,"El # de documento de identidad es un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							datoFaltante = "# Documento Identidad";
							contestoTodo=false;
						}
					}
					if(datoActual.equalsIgnoreCase("# Celular o Telefonico")){
						try {
							boolean valornumerico = true;
							for (int j = 0; j < repuestaActual.length(); j++) 
							{
								char numero = repuestaActual.charAt(j);
								if(numero != '0' && numero != '1' && numero != '2' && numero != '3' && numero != '4' && numero != '5' && numero != '6' && numero != '7' && numero != '8' && numero != '9')
								{
									valornumerico = false;
									break;
								}
							}
							if(valornumerico == true)
							{
								nuevo.setNumerotelefonico(repuestaActual);
							}
							else
							{
								int numerodocumento = Integer.parseInt(repuestaActual);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this,"El # Celular o Telefonico es un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							datoFaltante = "# Celular o Telefonico";
							contestoTodo=false;
						}
					}
					if(datoActual.equalsIgnoreCase("Correo Electronico")){
						boolean correovalido = inicio.validarEmail(repuestaActual);
						if(correovalido == true)
						{
							nuevo.setCorreoelectronico(repuestaActual);
						}
						else
						{
							JOptionPane.showMessageDialog(this,"El correo electronico "+repuestaActual+" no es valido.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							datoFaltante = "Correo Electronico";
							contestoTodo=false;
						}
					}
				}
			}
			if(contestoTodo==true)
			{
				int respuesta = JOptionPane.showConfirmDialog(this,"¿Esta seguro de los datos para crear otro Administrador ?","Confirmacion para crear",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        if (respuesta == JOptionPane.YES_OPTION){
		        	Administrador registrado = null;
		        	try 
		    		{
		    			Conector conector = new Conector();
		    			conector.iniciarConexionBaseDatos();
		    			registrado = AdministradorBD.buscarDocumentoIdentidad(nuevo.getDocumentoidentidad(), conector);
		    			conector.terminarConexionBaseDatos();
		    		}
		    		catch (Exception e)
		    		{
		    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		    		}
		    		
		    		if(registrado == null)
		    		{
		    			String passwordadmin = inicio.encriptarPassword(""+nuevo.getDocumentoidentidad());
		    			nuevo.setPassword(passwordadmin);
		    			try 
			    		{
			    			Conector conector = new Conector();
			    			conector.iniciarConexionBaseDatos();
			    			AdministradorBD.insertar(nuevo,conector);
			    			conector.terminarConexionBaseDatos();
			    			JOptionPane.showMessageDialog(this,"El Administrador ha sido creado.","Crear Administrador", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			    			JOptionPane.showMessageDialog(this,"La contraseña para iniciar sesion es el # de documento de identidad"+"\n"+"el nuevo administrador debe iniciar sesion y cambiar su contraseña.","Advertencia", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			    		}
			    		catch (Exception e)
			    		{
			    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			    		}
		    		}
		    		else
		    		{
		    			JOptionPane.showMessageDialog(this,"El Administrador ya existe en el sistema.","Crear Administrador", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
		    		}
		        	salir = true;
					super.dispose();
		        }
			}
			else{
				JOptionPane.showMessageDialog(this,"Falta por contestar la respuesta del dato:"+"\n"+datoFaltante+"\n"+"Conteste todos los datos correctamente.","Advertencia", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}