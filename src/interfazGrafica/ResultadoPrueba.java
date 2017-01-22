package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
public class ResultadoPrueba extends JDialog implements ActionListener 
{
	private JTable tbresultados;
	private JTable tbcandidato;
	private JTextArea observaciones;
	private JTextArea comentarios;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private Resultado resultadoselec;
	
	public ResultadoPrueba(InterfazInicio interfaz, PruebaCandidato pruebacselec)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Resultado Prueba - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 680+aumentoancho;
		int altoventana =415+150+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-(altoventana/2),anchoventana,altoventana);
		
		inicio=interfaz;
		Font fuenteletra = new Font( "Arial", Font.TRUETYPE_FONT, 15 );
		
		int idprueb = pruebacselec.getIdprueba();
		int idcandid = pruebacselec.getIdcandidato();
		int idresult = pruebacselec.getIdresultado();
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
		
		//********************************************
		tbresultados = new JTable(2,5);
		tbresultados.setValueAt("           NOMBRE PRUEBA", 0, 0);
		tbresultados.setValueAt("  FECHA PRUEBA", 0, 1);
		tbresultados.setValueAt("  HORA PRUEBA", 0, 2);
		tbresultados.setValueAt("    DURACION PRUEBA", 0, 3);
		tbresultados.setValueAt("  PUNTAJE", 0, 4);
		
		tbresultados.setEnabled(false);
		
		
		if(prueb != null)
		{
			if(candid != null)
			{
				if(result != null)
				{
					tbresultados.setValueAt(prueb.getNombre(), 1, 0);
					tbresultados.setValueAt(result.getFechaprueba(), 1, 1);
					tbresultados.setValueAt(result.getHorainicial(), 1, 2);
					tbresultados.setValueAt(result.getDuracionprueba()+" minutos", 1, 3);
					tbresultados.setValueAt(result.getPuntaje(), 1, 4);
					resultadoselec = result;
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No se obtubo el resultado.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No se obtubo el candidato.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"No se obtubo la prueba.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		
		
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbresultados.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(170);
	    tcm2.getColumn(1).setPreferredWidth(110);
	    tcm2.getColumn(2).setPreferredWidth(100);
	    tcm2.getColumn(3).setPreferredWidth(140);
	    tcm2.getColumn(4).setPreferredWidth(70);
	    tbresultados.setForeground(Color.BLACK);
	    tbresultados.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbresultados.setRowHeight(20);
	    tbresultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbresultados.setPreferredScrollableViewportSize(tbresultados.getPreferredSize());
		//********************************************
	    
	    
	    //tabla candidato
	  //********************************************
	    tbcandidato = new JTable(2,3);
	    tbcandidato.setValueAt("                  NOMBRE CANDIDATO", 0, 0);
	    tbcandidato.setValueAt("             CORREO ELECTRONICO", 0, 1);
	    tbcandidato.setValueAt(" NUMERO TELEFONICO", 0, 2);
	    tbcandidato.setEnabled(false);
		
	    
	    if(prueb != null)
		{
			if(candid != null)
			{
				if(result != null)
				{
					tbcandidato.setValueAt(candid.getNombres()+" "+candid.getApellidos(), 1, 0);
				    tbcandidato.setValueAt(candid.getCorreoelectronico(), 1, 1);
				    tbcandidato.setValueAt(candid.getNumerotelefonico(), 1, 2);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No se obtubo el resultado.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No se obtubo el candidato.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"No se obtubo la prueba.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
	 
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbcandidato.getColumnModel();
	    //tcm3.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(230);
	    tcm3.getColumn(1).setPreferredWidth(220);
	    tcm3.getColumn(2).setPreferredWidth(140);
	    
	    tbcandidato.setForeground(Color.BLACK);
	    //tbcandidato.setGridColor(new Color(35,91,92));
	    tbcandidato.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbcandidato.setRowHeight(20);
	    tbcandidato.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbcandidato.setPreferredScrollableViewportSize(tbcandidato.getPreferredSize());
	    //tbcandidato.setFont(fuenteletra);
		//********************************************
	    
	    int numerorenglones = 0; //tamanio del arraylist con los renglones
	    int numeroespacios = 0;
	    observaciones = new JTextArea();
	    String observacionesresult = "";
	    if(prueb != null)
		{
			if(candid != null)
			{
				if(result != null)
				{
					String observacionesobtenidas = result.getObservaciones();
					ArrayList renglonesobser = vectorObservaciones(observacionesobtenidas);
					observacionesresult += "                                                            OBSERVACIONES"
			    		+"\n"+"¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
					for (int i = 0; i < renglonesobser.size(); i++)
					{
						String renglonaux = (String)renglonesobser.get(i);
						if(renglonaux.contains("sincero") || renglonaux.contains("evaluados") || renglonaux.contains("del Proyecto.") || (renglonaux.contains("aceptable") && (renglonaux.contains("desempeñarse como Administrador") == false)))
						{
							observacionesresult += "\n"+renglonaux.trim()+"\n";
							numeroespacios++;
						}
						else
						{
							observacionesresult += "\n"+renglonaux.trim();
						}
					}
					observacionesresult += "\n"+"";

					if(renglonesobser != null)
					{
						numerorenglones = renglonesobser.size();
						for (int i = 0; i < numeroespacios; i++)
						{
							numerorenglones++;
						}
					}
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No se obtubo el resultado.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No se obtubo el candidato.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"No se obtubo la prueba.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}

	    
	    observaciones.setText(observacionesresult);
	    
	    int numeroadicionales=numerorenglones-8;
	    
	    observaciones.setPreferredSize(new Dimension(570,180+(numeroadicionales*20)));
	    observaciones.setEditable(false);
	    observaciones.setForeground(Color.BLACK);
	    observaciones.setFont(fuenteletra);
        
	    JScrollPane barrasobservaciones = new JScrollPane(observaciones);
	    barrasobservaciones.setPreferredSize(new Dimension(590,180));
	    
	    comentarios = new JTextArea();
	    comentarios.setEditable(true);
	    comentarios.setForeground(Color.BLACK);
	    comentarios.setFont(fuenteletra);
	    PanelMouseListener panelm = new PanelMouseListener(inicio);
	    comentarios.addMouseListener(panelm);
	    
	    String comentresultad = resultadoselec.getComentarios();
	    if(comentresultad.equalsIgnoreCase(""))
	    {
	    	comentarios.setText("Escriba aqui sus comentarios ...");
	    }
	    else
	    {
	    	comentarios.setText(comentresultad);
	    }
	    
	    JScrollPane barrascomentarios = new JScrollPane(comentarios);
	    barrascomentarios.setPreferredSize(new Dimension(590,145));

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
		
	    JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(600,282+150));
	    panel.setBackground(new Color(60,116,107));
	    panel.add(tbresultados);
	    panel.add(tbcandidato);
	    panel.add(barrasobservaciones);
	    panel.add(barrascomentarios);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (670+aumentoancho,375+150+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espaciosup = new JLabel(" ");
	    espaciosup.setPreferredSize(new Dimension(670+aumentoancho,8));	
	    panelcolor.add(espaciosup);
		panelcolor.add(panel);
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(670+aumentoancho,8));	
	    panelcolor.add(espacios);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
		add(panelcolor);
	}
	
	public ArrayList vectorObservaciones(String observaciones)
	{
		ArrayList renglones = new ArrayList();
		String renglon = "";
		for (int i = 0; i < observaciones.length(); i++) 
		{
			char caracter = observaciones.charAt(i);
			if(caracter != '.')
			{
				renglon += caracter;
				if(renglon.length()>(70) && caracter == ' ')
				{
					renglon += caracter;
					renglones.add(renglon);
					renglon = "";
				}
				if((i+1)==observaciones.length())
				{
					renglon += caracter;
					renglones.add(renglon);
					renglon = "";
				}
			}
			else
			{
				renglon += caracter;
				renglones.add(renglon);
				renglon = "";
			}
		}
		return renglones;
	}
	
	public void colocarTextoComentarios(String texto)
	{
		String textocoment = comentarios.getText();
		if(textocoment.equalsIgnoreCase("Escriba aqui sus comentarios ..."))
		{
			comentarios.setText(texto);
		}
	}
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			this.dispose();
			resultadoselec.setComentarios(comentarios.getText());
			if((resultadoselec.getRevisado()).equalsIgnoreCase("No"))
			{
				resultadoselec.setRevisado("Si");
				try 
	    		{
	    			Conector conector = new Conector();
	    			conector.iniciarConexionBaseDatos();
	    			ResultadoBD.actualizar(resultadoselec, conector);
	    			conector.terminarConexionBaseDatos();
		    		JOptionPane.showMessageDialog(this,"El resultado de la prueba ha sido revisado,"+"\n"+"estara disponible en resultados revisados.","Resultado Prueba", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
			}
			else
			{
				try 
	    		{
	    			Conector conector = new Conector();
	    			conector.iniciarConexionBaseDatos();
	    			ResultadoBD.actualizar(resultadoselec, conector);
	    			conector.terminarConexionBaseDatos();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}