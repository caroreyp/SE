package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
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
public class VerResultado extends JDialog implements ActionListener
{
	private JLabel etiqueta;
	private JTable tbresultados;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JComboBox comboBoxResultados;
	
	public VerResultado(InterfazInicio interfaz, String tiporesultado, String convocatoriaseleccionada)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Resultado - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 120;
		int anchoventana = 540+aumentoancho;
		int altoventana =229+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		//********************************************
		tbresultados = new JTable(new SimpleTableModel());
	    comboBoxResultados = new JComboBox();
	    tbresultados.setValueAt("Resultado:", 0, 0);
	    
	    int idconvocatoriaselec = 0;
	    if(convocatoriaseleccionada.equalsIgnoreCase("Pruebas Libres"))
	    {
	    	idconvocatoriaselec = -1;
	    }
	    else
	    {
		    List convocatorias = null;//convocatorias de la BD
		    try 
		    {
			    Conector conector = new Conector();
			    conector.iniciarConexionBaseDatos();
			    convocatorias = ConvocatoriaBD.listar(conector);
			    conector.terminarConexionBaseDatos();
		    } 
		    catch (Exception e)
		    {
			    JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		    }
		    int numeroconvocatorias = 0;
		    if(convocatorias != null)
		    {
			    numeroconvocatorias = convocatorias.size();
		    }
		    Convocatoria convoseleccionada = null;
		    for (int i = 0; i < numeroconvocatorias; i++)
		    {
			    Convocatoria convocatoriaaux = (Convocatoria)convocatorias.get(i);
			    int idconvo = convocatoriaaux.getIdconvocatoria();
			    String nombre = convocatoriaaux.getNombre();
			    String nombreconvo = (idconvo+" - "+nombre);
			    if(convocatoriaseleccionada.equalsIgnoreCase(nombreconvo))
			    {
			    	 convoseleccionada = convocatoriaaux;
			    	 idconvocatoriaselec = convoseleccionada.getIdconvocatoria();
			    	 break;
			    }
		    }
	    }
	    
	    List pruebascandidatos = null;//pruebascandidatos de la BD
		try
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			pruebascandidatos= PruebaCandidatoBD.listar(conector);
			conector.terminarConexionBaseDatos();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		int numeropruebascandidatos = 0;
		if(pruebascandidatos != null)
		{
			numeropruebascandidatos = pruebascandidatos.size();
		}
		for (int i = 0; i < numeropruebascandidatos; i++)
		{
			PruebaCandidato pruebaca = (PruebaCandidato)pruebascandidatos.get(i);
			int idconvocator = pruebaca.getIdconvocator();
			if(idconvocator == idconvocatoriaselec)
			{
				int idprueb = pruebaca.getIdprueba();
				int idcandid = pruebaca.getIdcandidato();
				int idresult = pruebaca.getIdresultado();
				Prueba prueb = null;
				Candidato candid = null;	
				Resultado result = null;
				try
				{
					Conector conector = new Conector();
					conector.iniciarConexionBaseDatos();
					result = ResultadoBD.buscarIdResultado(idresult, conector);
					conector.terminarConexionBaseDatos();
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
				if(tiporesultado.equalsIgnoreCase("Sin Revisar"))
				{
					if(result != null)
					{
						String revisado = result.getRevisado();
						if(revisado.equalsIgnoreCase("No"))
						{
							try
							{
								Conector conector = new Conector();
								conector.iniciarConexionBaseDatos();
								prueb = PruebaBD.buscarIdPrueba(idprueb, conector);
								conector.terminarConexionBaseDatos();
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
							try
							{
								Conector conector = new Conector();
								conector.iniciarConexionBaseDatos();
								candid = CandidatoBD.buscarIdCandidato(idcandid, conector);
								conector.terminarConexionBaseDatos();
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
							String nombreprueba = prueb.getNombre();
							String nombrecandidato = candid.getNombres()+" "+candid.getApellidos();
							comboBoxResultados.addItem(result.getIdresultado()+" - "+nombreprueba+" - "+nombrecandidato);
						}
					}
				}
				if(tiporesultado.equalsIgnoreCase("Revisados"))
				{
					if(result != null)
					{
						String revisado = result.getRevisado();
						if(revisado.equalsIgnoreCase("Si"))
						{
							try
							{
								Conector conector = new Conector();
								conector.iniciarConexionBaseDatos();
								prueb = PruebaBD.buscarIdPrueba(idprueb, conector);
								conector.terminarConexionBaseDatos();
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
							try
							{
								Conector conector = new Conector();
								conector.iniciarConexionBaseDatos();
								candid = CandidatoBD.buscarIdCandidato(idcandid, conector);
								conector.terminarConexionBaseDatos();
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
							String nombreprueba = prueb.getNombre();
							String nombrecandidato = candid.getNombres()+" "+candid.getApellidos();
							comboBoxResultados.addItem(result.getIdresultado()+" - "+nombreprueba+" - "+nombrecandidato);
						}
					}
				}
			}
			
		}
		  
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboBoxResultados);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbresultados.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(70);
	    tcm2.getColumn(1).setPreferredWidth(330);
	    tbresultados.setForeground(Color.BLACK);
	    tbresultados.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbresultados.setRowHeight(20);
	    tbresultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbresultados.setPreferredScrollableViewportSize(tbresultados.getPreferredSize());
		//********************************************
	    
	    inicio = interfaz;
		etiqueta = new JLabel(" Seleccione el Resultado segun la prueba y el candidato");
		etiqueta.setForeground(new Color(171,223,140));
		etiqueta.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiqueta.setPreferredSize(new Dimension (525+aumentoancho,40+5));
		
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
		panelcolor.setPreferredSize(new Dimension (530+aumentoancho,190+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(520+aumentoancho,15));	
	    panelcolor.add(espaciou);
		panelcolor.add(etiqueta);
	    JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(520+aumentoancho,3));	
	    panelcolor.add(espacioup);
		panelcolor.add(tbresultados);
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(520+aumentoancho,10));	
	    panelcolor.add(espacios);
		
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
	    
	    add(panelcolor);
	}
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String resultadoselecconado=(String) tbresultados.getValueAt(0, 1);
			if(resultadoselecconado == null && comboBoxResultados.getItemCount() > 0)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un resultado para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else if(resultadoselecconado == null && comboBoxResultados.getItemCount() == 0)
			{
				JOptionPane.showMessageDialog(this,"No hay resultados que mostrar en esta convocatoria.","Seleccionar Resultado", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
			else
			{
				List pruebascandidatos = null;//pruebascandidatos de la BD
				try
				{
					Conector conector = new Conector();
					conector.iniciarConexionBaseDatos();
					pruebascandidatos= PruebaCandidatoBD.listar(conector);
					conector.terminarConexionBaseDatos();
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
				int numeropruebascandidatos = 0;
				if(pruebascandidatos != null)
				{
					numeropruebascandidatos = pruebascandidatos.size();
				}
				for (int i = 0; i < numeropruebascandidatos; i++)
				{
					PruebaCandidato pruebaca = (PruebaCandidato)pruebascandidatos.get(i);
					int idprueb = pruebaca.getIdprueba();
					int idcandid = pruebaca.getIdcandidato();
					int idresult = pruebaca.getIdresultado();
					Prueba prueb = null;
					Candidato candid = null;
					try
					{
						Conector conector = new Conector();
						conector.iniciarConexionBaseDatos();
						prueb = PruebaBD.buscarIdPrueba(idprueb, conector);
						conector.terminarConexionBaseDatos();
					} 
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					try
					{
						Conector conector = new Conector();
						conector.iniciarConexionBaseDatos();
						candid = CandidatoBD.buscarIdCandidato(idcandid, conector);
						conector.terminarConexionBaseDatos();
					} 
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					String nombreprueba = prueb.getNombre();
					String nombrecandidato = candid.getNombres()+" "+candid.getApellidos();
					String nombreresultado = (idresult+" - "+nombreprueba+" - "+nombrecandidato);
					if(nombreresultado.equalsIgnoreCase(resultadoselecconado))
					{
						this.dispose();
						inicio.verResultadoPrueba(pruebaca);
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}