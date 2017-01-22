package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
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
public class EliminarReglaConocimiento extends JDialog implements ActionListener
{
	private JTable tbdesde;
	private JTable tbhasta;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private String desdeentidad;
	private String hastaentidad;
	
	public EliminarReglaConocimiento(InterfazInicio interfaz, String desde, String hasta)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Eliminar Regla Conocimiento - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 390+aumentoancho;
		int altoventana =220+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);
		
		inicio=interfaz;
		desdeentidad = desde;
		hastaentidad = hasta;
	    //********************************************
		tbdesde = new JTable(new SimpleTableModel());
		tbdesde.setValueAt("  ID "+desde.toUpperCase(), 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbdesde.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(150);
	    tcm3.getColumn(1).setPreferredWidth(150);
	    
	    tbdesde.setForeground(Color.BLACK);
	    tbdesde.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbdesde.setRowHeight(20);
	    tbdesde.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdesde.setPreferredScrollableViewportSize(tbdesde.getPreferredSize());
	    tbdesde.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	    //********************************************
	    tbhasta = new JTable(new SimpleTableModel());
	    tbhasta.setValueAt("  ID "+hasta.toUpperCase(), 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbhasta.getColumnModel();
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(150);
	    tcm4.getColumn(1).setPreferredWidth(150);
	    
	    tbhasta.setForeground(Color.BLACK);
	    tbhasta.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbhasta.setRowHeight(20);
	    tbhasta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbhasta.setPreferredScrollableViewportSize(tbhasta.getPreferredSize());
	    tbhasta.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************

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
		
		int tbancho = 300;
		int tbalto = 2*((tbdesde.getRowCount()*20)+9);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbdesde);
	    panel.add(tbhasta);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (380+aumentoancho,180+15));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(420+aumentoancho,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(panel);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(420+aumentoancho,10));	
	    panelcolor.add(espaciob);
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
			String iddigitadodesde = (String) tbdesde.getValueAt(0, 1);
			if(iddigitadodesde == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el Id de "+desdeentidad,"Eliminar Regla",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String iddigitadohasta = (String) tbhasta.getValueAt(0, 1);
				if(iddigitadohasta == null)
				{
					JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el Id de "+hastaentidad,"Eliminar Regla",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
				else
				{
					try 
					{
						int iddesde = Integer.parseInt(iddigitadodesde);
						int idhasta = Integer.parseInt(iddigitadohasta);
						int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				        if (respuesta == JOptionPane.YES_OPTION){
				        	if(desdeentidad.equalsIgnoreCase("Pregunta") && hastaentidad.equalsIgnoreCase("Escala"))
					        {
								try 
					    		{
					    			Conector conectora = new Conector();
					    			conectora.iniciarConexionBaseDatos();
					    			PreguntaEscala existep = PreguntaEscalaBD.consultar(idhasta, iddesde, conectora);
					    			conectora.terminarConexionBaseDatos();
					    			if(existep != null)
					    			{
					    				Conector conectorb = new Conector();
						    			conectorb.iniciarConexionBaseDatos();
						    			PreguntaEscalaBD.eliminar(existep.getIdescala(), existep.getIdpregunta(), conectorb);
					    				conectorb.terminarConexionBaseDatos();
					    				JOptionPane.showMessageDialog(this,"Se Elimino la regla entre "+desdeentidad+" y "+hastaentidad+" como base del conocimiento.","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					    				dispose();
					    			}
					    			else
					    			{
					    				JOptionPane.showMessageDialog(this,"La regla entre "+desdeentidad+" y "+hastaentidad+" no existe en la base del conocimineto","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					    			}
					    		}
					    		catch (Exception e)
					    		{
					    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					    		}

					        }
					        if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Escala"))
					        {
								try 
					    		{
					    			Conector conectora = new Conector();
					    			conectora.iniciarConexionBaseDatos();
					    			CompetenciaEscala existep = CompetenciaEscalaBD.consultar(iddesde, idhasta, conectora);
					    			conectora.terminarConexionBaseDatos();
					    			if(existep != null)
					    			{
					    				Conector conectorb = new Conector();
						    			conectorb.iniciarConexionBaseDatos();
						    			CompetenciaEscalaBD.eliminar(existep.getIdcompetencia(), existep.getIdescala(), conectorb);
					    				conectorb.terminarConexionBaseDatos();
						    			JOptionPane.showMessageDialog(this,"Se Elimino la regla entre "+desdeentidad+" y "+hastaentidad+" como base del conocimiento.","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
						    			dispose();
					    			}
					    			else
					    			{
					    				JOptionPane.showMessageDialog(this,"La regla entre "+desdeentidad+" y "+hastaentidad+" no existe en la base del conocimineto","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					    			}
					    		}
					    		catch (Exception e)
					    		{
					    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					    		}
					        }
					        if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Rol"))
					        {
								try 
					    		{
					    			Conector conectora = new Conector();
					    			conectora.iniciarConexionBaseDatos();
					    			CompetenciaRol existep = CompetenciaRolBD.consultar(iddesde, idhasta, conectora);
					    			conectora.terminarConexionBaseDatos();
					    			if(existep != null)
					    			{
					    				Conector conectorb = new Conector();
						    			conectorb.iniciarConexionBaseDatos();
						    			CompetenciaRolBD.eliminar(existep.getIdcompetencia(), existep.getIdrol(), conectorb);
					    				conectorb.terminarConexionBaseDatos();
					    				JOptionPane.showMessageDialog(this,"Se Elimino la regla entre "+desdeentidad+" y "+hastaentidad+" como base del conocimiento.","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					    				dispose();
					    			}
					    			else
					    			{
					    				JOptionPane.showMessageDialog(this,"La regla entre "+desdeentidad+" y "+hastaentidad+" no existe en la base del conocimineto","Eliminar Regla", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					    			}
					    		}
					    		catch (Exception e)
					    		{
					    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					    		}
					        }
				        }
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(this,"Los Id deben ser valores numericos enteros.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}