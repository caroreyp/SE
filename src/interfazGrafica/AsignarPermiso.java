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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import mundo.*;
import basedeDatos.*;

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
public class AsignarPermiso extends JDialog implements ActionListener {
	
	private JLabel etiquetadocumento;
	private JTextField txtdocumento;
	private JButton btaceptar;
	private JButton btcancelar;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	private JLabel etiquetatxt;
	private JTable tbdocumento;
	private Administrador adminactual;
	
	public AsignarPermiso(InterfazInicio interfaz, Administrador actualadmin)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Asignar Permiso Candidato - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 120;
		int anchoventana = 610+aumentoancho;
		int altoventana =235+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		//********************************************
		etiquetatxt = new JLabel("Escriba el numero del documento de identidad del candidato");
		etiquetatxt.setForeground(new Color(171,223,140));
		etiquetatxt.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiquetatxt.setPreferredSize(new Dimension (580+aumentoancho,40+5));

		
		tbdocumento = new JTable(new SimpleTableModel());
		JTextField caja = new JTextField();
		tbdocumento.setValueAt("# Documento Identidad :", 0, 0);
	    DefaultCellEditor editor2 = new DefaultCellEditor(caja);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbdocumento.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(150);
	    tcm2.getColumn(1).setPreferredWidth(200);
	    tbdocumento.setForeground(Color.BLACK);
	    tbdocumento.setGridColor(new Color(35,91,92));
	    tbdocumento.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
	    // Set row heighht
	    tbdocumento.setRowHeight(20);
	    tbdocumento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdocumento.setPreferredScrollableViewportSize(tbdocumento.getPreferredSize());
		//********************************************
	    
		inicio=interfaz;
		adminactual = actualadmin;
		etiquetadocumento = new JLabel("# Documento Identidad :");
		etiquetadocumento.setForeground(new Color(35,91,92));
		
		txtdocumento = new JTextField(11);
		txtdocumento.setBackground(Color.WHITE);
		txtdocumento.setForeground(Color.BLACK);
		
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
		panelcolor.setPreferredSize(new Dimension (600+aumentoancho,195+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		panelcolor.add(new JLabel("                                                  "));
		panelcolor.add(etiquetatxt);
		panelcolor.add(new JScrollPane(tbdocumento));
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(520+aumentoancho,10));	
	    panelcolor.add(espacios);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
		add(panelcolor);
	}
	
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String documentodigitado = (String) tbdocumento.getValueAt(0, 1);
			boolean candidatoregistrado = false;
			List candidatos = null;
			try
	        {
	            Conector conector = new Conector();
	            conector.iniciarConexionBaseDatos();
	            candidatos= CandidatoBD.listar(conector);
	            conector.terminarConexionBaseDatos();
	        }
	        catch (Exception e)
			{
	        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			if(documentodigitado == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el numero del documento.","Asignar Permiso",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				Candidato actualcandidat = null;
				try 
				{
					for (int i = 0; i < candidatos.size(); i++) 
		            {
		            	Candidato candidatoaux = (Candidato)candidatos.get(i);
		            	boolean valornumerico = true;
						for (int j = 0; j < documentodigitado.length(); j++) 
						{
							char numero = documentodigitado.charAt(j);
							if(numero != '0' && numero != '1' && numero != '2' && numero != '3' && numero != '4' && numero != '5' && numero != '6' && numero != '7' && numero != '8' && numero != '9')
							{
								valornumerico = false;
								break;
							}
						}
						if(valornumerico == true)
						{
							if(documentodigitado.equalsIgnoreCase(candidatoaux.getDocumentoidentidad()))
			                {
			                	candidatoregistrado = true;
			                	actualcandidat = candidatoaux;
			                	break;
			                }
						}
						else
						{
							int numerodocumento = Integer.parseInt(documentodigitado);
						}
		            }
					if(candidatoregistrado == true)
					{
						this.dispose();
						try 
			    		{
			    			Conector conectora = new Conector();
			    			conectora.iniciarConexionBaseDatos();
			    			Candidato candidatoregis = CandidatoBD.buscarDocumentoIdentidad(documentodigitado, conectora);
			    			conectora.terminarConexionBaseDatos();
			    			GregorianCalendar calendario = new GregorianCalendar();
			    			String fechapermiso = calendario.get(Calendar.YEAR)+"-"+(calendario.get(Calendar.MONTH)+1)+"-"+calendario.get(Calendar.DAY_OF_MONTH);
			    			String horapermiso = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
			    			
			    			Permiso permisoasignado = null;
			    			Conector conectorb = new Conector();
			    			conectorb.iniciarConexionBaseDatos();
			    			permisoasignado = PermisoBD.buscarCandidato(candidatoregis.getIdcandidato(), conectorb);
			    			conectorb.terminarConexionBaseDatos();
			    	        
			    			if(permisoasignado == null)
			    			{
			    				Permiso nuevopermiso = new Permiso();
				    			nuevopermiso.setIdcandidato(candidatoregis.getIdcandidato());
				    			nuevopermiso.setIdadmin(adminactual.getIdadmin());
				    			nuevopermiso.setFechapermiso(fechapermiso);
				    			nuevopermiso.setHorapermiso(horapermiso);
				    			Conector conectorc = new Conector();
				    			conectorc.iniciarConexionBaseDatos();
				    			PermisoBD.insertar(nuevopermiso, conectorc);
				    			conectorc.terminarConexionBaseDatos();
				    			JOptionPane.showMessageDialog(this,"Al candidato se le asigno un permiso.","Asignar Permiso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			    			}
			    			else
			    			{
			    				JOptionPane.showMessageDialog(this,"El candidato ya tiene asignado un permiso.","Asignar Permiso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			    			}
			    		}
			    		catch (Exception e)
			    		{
			    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			    		}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"El numero de documento escrito no esta registrado.","Error Sesion",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this,"El numero de documento debe ser un valor numerico.","Error Sesion",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}