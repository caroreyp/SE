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
import javax.swing.JPasswordField;

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
public class CambiarContraseña extends JDialog implements ActionListener {

	private JLabel etiquetaactual;
	private JPasswordField txtactual;
	private JLabel etiquetanueva;
	private JPasswordField txtnueva;
	private JLabel etiquetaverificar;
	private JPasswordField txtverificar;
	private JLabel separador;
	private JButton btaceptar;
	private JButton btcancelar;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	private Administrador adminactual;
	
	public CambiarContraseña(InterfazInicio interfaz, Administrador actualadmin){
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Cambiar Contraseña Administrador - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 810+aumentoancho;
		int altoventana =230+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),150,anchoventana,altoventana);
		
		inicio=interfaz;
		adminactual = actualadmin;
		
		etiquetaactual = new JLabel("      Contraseña Actual");
		etiquetaactual.setForeground(new Color(171,223,140));
		etiquetaactual.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 31 ));
		
		txtactual = new JPasswordField(15);
		txtactual.setBackground(Color.WHITE);
		txtactual.setForeground(Color.BLACK);
		
		etiquetanueva = new JLabel("Contraseña Nueva");
		etiquetanueva.setForeground(new Color(171,223,140));
		etiquetanueva.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 31 ));
		
		txtnueva = new JPasswordField(15);
		txtnueva.setBackground(Color.WHITE);
		txtnueva.setForeground(Color.BLACK);
		
		etiquetaverificar = new JLabel("Confirmar Contraseña Nueva");
		etiquetaverificar.setForeground(new Color(171,223,140));
		etiquetaverificar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 31 ));
		
		txtverificar = new JPasswordField(15);
		txtverificar.setBackground(Color.WHITE);
		txtverificar.setForeground(Color.BLACK);
		
		separador = new JLabel("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		separador.setForeground(new Color(244,255,213));
		
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
		panelcolor.setPreferredSize(new Dimension (800+aumentoancho,190+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(730+aumentoancho,15));	
	    panelcolor.add(espacioa);
	    panelcolor.add(etiquetaactual);
	    panelcolor.add(new JLabel("            "));
	    panelcolor.add(etiquetanueva);
	    panelcolor.add(new JLabel("            "));
	    panelcolor.add(etiquetaverificar);
	    
	    JPanel panelentradas = new JPanel(new FlowLayout());
	    panelentradas.setPreferredSize(new Dimension (740,25));
		panelentradas.setBackground(new Color(68,146,132));
	    
		panelentradas.add(txtactual);
		panelentradas.add(new JLabel("              "));
	    panelentradas.add(txtnueva);
	    panelentradas.add(new JLabel("                                   "));
	    panelentradas.add(txtverificar);

	    panelcolor.add(panelentradas);
	    
	    JLabel espacioe = new JLabel(" ");
	    espacioe.setPreferredSize(new Dimension(730+aumentoancho,10));	
	    panelcolor.add(espacioe);
	    panelcolor.add(separador);
	    panelcolor.add(btaceptar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(btcancelar);
	    
	    add(panelcolor);
	}
	
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String contraseñaadmin = txtactual.getText();
			if(contraseñaadmin.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Escriba la contraseña actual del administrador.","Cambiar Contraseña",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String contraseñaactual= inicio.encriptarPassword(txtactual.getText());
				String contraseñaalmacenada = adminactual.getPassword();
				
				if(contraseñaactual.equalsIgnoreCase(contraseñaalmacenada))
				{
					String contraseñanueva = txtnueva.getText();
					String contraseñaverificada = txtverificar.getText();
					if(contraseñanueva.equalsIgnoreCase(contraseñaverificada))
					{
						this.dispose();
						try
				        {
							String passwordnuevo = inicio.encriptarPassword(contraseñaverificada);
							adminactual.setPassword(passwordnuevo);
				            Conector conector = new Conector();
				            conector.iniciarConexionBaseDatos();
				            AdministradorBD.actualizar(adminactual, conector);
				            conector.terminarConexionBaseDatos();
				            JOptionPane.showMessageDialog(this,"La contraseña ha sido cambiada.","Cambiar Contraseña",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				        }
				        catch (Exception e)
						{
				        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"La contraseña nueva no es igual a la verificada.","Cambiar Contraseña",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"La Contraseña actual no corresponde.","Contraseña incorrecta",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}