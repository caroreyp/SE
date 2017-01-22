package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class RegistrarCandidato extends JDialog implements ActionListener {
	
	private JTable tbdatos;
	private JTable tbgenero;
	private JTable tbdia;
	private JTable tbmes;
	private JTable tbaño;
	private JButton btaceptar;
	private JButton btcancelar;
	private boolean salir;
	
	private InterfazInicio inicio;
	
	public RegistrarCandidato(InterfazInicio interfaz){
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Registrar Candidato - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 40;
		int anchoventana = 390+aumentoancho;
		int altoventana =359+15;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);

		inicio = interfaz;
		
		salir = false;
		//tabla datos nombre, apellido y documento
		tbdatos = new JTable(new DatosCandidatoTableModel());
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

	    //tabla datos genero
		tbgenero = new JTable(new GenderTableModel());
		// Create the combo box editor
	    JComboBox comboBoxGenero = new JComboBox(GenderTableModel.getValidStates());
	    comboBoxGenero.setEditable(false);
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboBoxGenero);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbgenero.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(150);
	    tcm2.getColumn(1).setPreferredWidth(200);
	    tbgenero.setForeground(Color.BLACK);
	    tbgenero.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbgenero.setRowHeight(20);
	    tbgenero.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbgenero.setPreferredScrollableViewportSize(tbgenero.getPreferredSize());

	    //tabla datos año nacimiento
		tbaño = new JTable(new YearTableModel());
		// Create the combo box editor
	    JComboBox comboBoxAño = new JComboBox(YearTableModel.getValidStates());
	    comboBoxAño.setEditable(false);
	    DefaultCellEditor editor5 = new DefaultCellEditor(comboBoxAño);
	    // Assign the editor to the second column
	    TableColumnModel tcm5 = tbaño.getColumnModel();
	    tcm5.getColumn(1).setCellEditor(editor5);
		// Set column widths
	    tcm5.getColumn(0).setPreferredWidth(150);
	    tcm5.getColumn(1).setPreferredWidth(200);
	    tbaño.setForeground(Color.BLACK);
	    tbaño.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbaño.setRowHeight(20);
	    tbaño.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbaño.setPreferredScrollableViewportSize(tbaño.getPreferredSize());
	    
	    //tabla datos mes nacimiento
		tbmes = new JTable(new MonthTableModel());
		// Create the combo box editor
	    JComboBox comboBoxMes = new JComboBox(MonthTableModel.getValidStates());
	    comboBoxMes.setEditable(false);
	    DefaultCellEditor editor4 = new DefaultCellEditor(comboBoxMes);
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbmes.getColumnModel();
	    tcm4.getColumn(1).setCellEditor(editor4);
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(150);
	    tcm4.getColumn(1).setPreferredWidth(200);
	    tbmes.setForeground(Color.BLACK);
	    tbmes.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbmes.setRowHeight(20);
	    tbmes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbmes.setPreferredScrollableViewportSize(tbmes.getPreferredSize());

	    //tabla datos dia nacimento
		tbdia = new JTable(new DayTableModel());
		// Create the combo box editor
	    JComboBox comboBoxDia = new JComboBox(DayTableModel.getValidStates());
	    comboBoxDia.setEditable(false);
	    DefaultCellEditor editor3 = new DefaultCellEditor(comboBoxDia);
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbdia.getColumnModel();
	    tcm3.getColumn(1).setCellEditor(editor3);
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(150);
	    tcm3.getColumn(1).setPreferredWidth(200);
	    tbdia.setForeground(Color.BLACK);
	    tbdia.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbdia.setRowHeight(20);
	    tbdia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdia.setPreferredScrollableViewportSize(tbdia.getPreferredSize());

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
		panelcolor.setPreferredSize(new Dimension (380+aumentoancho,320+20));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espaciosup = new JLabel(" ");
	    espaciosup.setPreferredSize(new Dimension(370+aumentoancho,5));	
	    panelcolor.add(espaciosup);
		panelcolor.add(new JScrollPane(tbdatos));
		panelcolor.add(new JScrollPane(tbgenero));
		panelcolor.add(new JScrollPane(tbaño));
		panelcolor.add(new JScrollPane(tbmes));
		panelcolor.add(new JScrollPane(tbdia));
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(370+aumentoancho,5));	
	    panelcolor.add(espacios);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
		add(panelcolor);
	}
	
	public void dispose( ){
		if(salir == false){
			int respuesta = JOptionPane.showConfirmDialog(this,"¿Cancelar la incripcion del candidato?","Confirmacion para salir",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
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
			Candidato nuevo = new Candidato();
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
			if(contestoTodo==true){
				String generoAct = (String) tbgenero.getValueAt(0, 0);
				String generoActual = generoAct.trim();
				String repuestageneroAct = (String) tbgenero.getValueAt(0, 1);
				String repuestageneroActual = repuestageneroAct.trim();
				
				String añoAct = (String) tbaño.getValueAt(0, 0);
				String añoActual = añoAct.trim();
				String repuestaañoAct = (String) tbaño.getValueAt(0, 1);
				String repuestaañoActual = repuestaañoAct.trim();
				
				String mesAct = (String) tbmes.getValueAt(0, 0);
				String mesActual = mesAct.trim();
				String repuestamesAct = (String) tbmes.getValueAt(0, 1);
				String repuestamesActual = repuestamesAct.trim();
				
				String diaAct = (String) tbdia.getValueAt(0, 0);
				String diaActual = diaAct.trim();
				String repuestadiaAct = (String) tbdia.getValueAt(0, 1);
				String repuestadiaActual = repuestadiaAct.trim();

				if(generoActual.equalsIgnoreCase("Genero")){
					nuevo.setGenero(""+repuestageneroActual.charAt(0));
				}
				if(añoActual.equalsIgnoreCase("Año Nacimiento")){
					int año = Integer.parseInt(repuestaañoActual);
					nuevo.setAñonacimiento(año);
				}
				if(mesActual.equalsIgnoreCase("Mes Nacimiento")){
					int mes = 0;
					if(repuestamesActual.equalsIgnoreCase("Enero")){
						mes = 1;
					}
					if(repuestamesActual.equalsIgnoreCase("Febrero")){
						mes = 2;
					}
					if(repuestamesActual.equalsIgnoreCase("Marzo")){
						mes = 3;
					}
					if(repuestamesActual.equalsIgnoreCase("Abril")){
						mes = 4;
					}
					if(repuestamesActual.equalsIgnoreCase("Mayo")){
						mes = 5;
					}
					if(repuestamesActual.equalsIgnoreCase("Junio")){
						mes = 6;
					}
					if(repuestamesActual.equalsIgnoreCase("Julio")){
						mes = 7;
					}
					if(repuestamesActual.equalsIgnoreCase("Agosto")){
						mes = 8;
					}
					if(repuestamesActual.equalsIgnoreCase("Septiembre")){
						mes = 9;
					}
					if(repuestamesActual.equalsIgnoreCase("Octubre")){
						mes = 10;
					}
					if(repuestamesActual.equalsIgnoreCase("Noviembre")){
						mes = 11;
					}
					if(repuestamesActual.equalsIgnoreCase("Diciembre")){
						mes = 12;
					}
					nuevo.setMesnacimiento(mes);
				}
				if(diaActual.equalsIgnoreCase("Dia Nacimiento")){
					int dia = Integer.parseInt(repuestadiaActual);
					nuevo.setDianacimiento(dia);
				}
				String fechanacimiento = nuevo.getAñonacimiento()+"-"+nuevo.getMesnacimiento()+"-"+nuevo.getDianacimiento();
				nuevo.setFechanacimiento(fechanacimiento);
				int respuesta = JOptionPane.showConfirmDialog(this,"¿Esta seguro de los datos del candidato para el registro?","Confirmacion para registrar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        if (respuesta == JOptionPane.YES_OPTION){

		        	Candidato registrado = null;
		        	try 
		    		{
		    			Conector conector = new Conector();
		    			conector.iniciarConexionBaseDatos();
		    			registrado = CandidatoBD.buscarDocumentoIdentidad(nuevo.getDocumentoidentidad(), conector);
		    			conector.terminarConexionBaseDatos();
		    		}
		    		catch (Exception e)
		    		{
		    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		    		}
		    		
		    		if(registrado == null)
		    		{
		    			try 
			    		{
			    			Conector conectora = new Conector();
			    			conectora.iniciarConexionBaseDatos();
			    			CandidatoBD.insertar(nuevo,conectora);
			    			conectora.terminarConexionBaseDatos();
			    			
			    			Conector conectorb = new Conector();
			    			conectorb.iniciarConexionBaseDatos();
			    			Candidato candidatoregis = CandidatoBD.buscarDocumentoIdentidad(nuevo.getDocumentoidentidad(), conectorb);
			    			conectorb.terminarConexionBaseDatos();
			    			
			    			Conector conectorc = new Conector();
			    			conectorc.iniciarConexionBaseDatos();
			    			List adm = AdministradorBD.listar(conectorc);
			    			conectorc.terminarConexionBaseDatos();
			    			Administrador adminregis = (Administrador)adm.get(0);
			    			GregorianCalendar calendario = new GregorianCalendar();
			    			String fechapermiso = calendario.get(Calendar.YEAR)+"-"+(calendario.get(Calendar.MONTH)+1)+"-"+calendario.get(Calendar.DAY_OF_MONTH);
			    			String horapermiso = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
			    			
			    			Permiso nuevopermiso = new Permiso();
			    			nuevopermiso.setIdcandidato(candidatoregis.getIdcandidato());
			    			nuevopermiso.setIdadmin(adminregis.getIdadmin());
			    			nuevopermiso.setFechapermiso(fechapermiso);
			    			nuevopermiso.setHorapermiso(horapermiso);
			    			
			    			Conector conectord = new Conector();
			    			conectord.iniciarConexionBaseDatos();
			    			PermisoBD.insertar(nuevopermiso, conectord);
			    			conectord.terminarConexionBaseDatos();
			    			JOptionPane.showMessageDialog(this,"El Candidato ha sido registrado.","Registrar Candidato", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			    		}
			    		catch (Exception e)
			    		{
			    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			    		}
		    		}
		    		else
		    		{
		    			JOptionPane.showMessageDialog(this,"El Candidato ya estaba registrado en el sistema.","Registrar Candidato", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
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