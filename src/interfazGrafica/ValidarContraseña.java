package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
public class ValidarContraseña extends JDialog implements ActionListener {
	
	private JLabel etiquetausuario;
	private JComboBox boxadmin;
	private JLabel etiquetacontraseña;
	private JPasswordField txtcontraseña;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private String passwordactual;
	
	public ValidarContraseña(InterfazInicio interfaz){
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Iniciar Sesion Administrador - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int anchoventana = 395+30;
		int altoventana =240+30;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;
		etiquetausuario = new JLabel("Administrador :");
		etiquetausuario.setForeground(new Color(244,255,213));
		etiquetausuario.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 35+5 ));
		
		boxadmin = new JComboBox ();
		boxadmin.setPreferredSize(new Dimension (180,25));
		boxadmin.addItem("");
		
		try
        {
            List administradores = null;
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            administradores= AdministradorBD.listar(conector);
            conector.terminarConexionBaseDatos();
            for (int i = 0; i < administradores.size(); i++) 
            {
                Administrador administradoraux=(Administrador)administradores.get(i);
                String nombreadministrador=administradoraux.getNombres();
		        String apellidoadministrador=administradoraux.getApellidos();
                if(!nombreadministrador.equalsIgnoreCase("") && !apellidoadministrador.equalsIgnoreCase(""))
                {
                	boxadmin.addItem(nombreadministrador+" "+apellidoadministrador);
                }
            }
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		
		etiquetacontraseña = new JLabel("     Contraseña :");
		etiquetacontraseña.setForeground(new Color(244,255,213));
		etiquetacontraseña.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 35+4 ));
		
		txtcontraseña = new JPasswordField(16);
		txtcontraseña.setBackground(Color.WHITE);
		txtcontraseña.setForeground(Color.BLACK);
		
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
		
		JPanel paneletiquetas = new JPanel(new FlowLayout());
		paneletiquetas.setPreferredSize(new Dimension (185,105));
		paneletiquetas.setBackground(new Color(68,146,132));
		
		JPanel panelentradas = new JPanel(new FlowLayout());
		panelentradas.setPreferredSize(new Dimension (190,95));
		panelentradas.setBackground(new Color(68,146,132));
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (385,200));
		panelcolor.setBackground(new Color(68,146,132));
		
		panelcolor.add(new JLabel("                                                                  "));
		paneletiquetas.add(etiquetausuario);
		paneletiquetas.add(etiquetacontraseña);
		panelcolor.add(paneletiquetas);
		
		panelentradas.add(boxadmin);
		JLabel medio = new JLabel("                                                          ");
		medio.setPreferredSize(new Dimension (175,10));
		panelentradas.add(medio);
		panelentradas.add(txtcontraseña);
		panelcolor.add(panelentradas);
		
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
		
		JPanel panelborde = new JPanel(new FlowLayout());
		panelborde.setPreferredSize(new Dimension (385+30,200+35));
		panelborde.setBackground(new Color(68,146,132));
		panelborde.add(panelcolor);
		add(panelborde);
	}
	
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String nombreadmin = (String)boxadmin.getSelectedItem();
			if(nombreadmin.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Seleccione el nombre del administrador.","Sesion Administrador",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String claveDigitada= txtcontraseña.getText();
				String claveAlmacenada = "";
				
				Administrador actualadmin = null;
				try
		        {
		            List administradores = null;
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            administradores= AdministradorBD.listar(conector);
		            conector.terminarConexionBaseDatos();
		            for (int i = 0; i < administradores.size(); i++) 
		            {
		                Administrador administradorAux=(Administrador)administradores.get(i);
		                String nombreadministrador=administradorAux.getNombres();
				        String apellidoadministrador=administradorAux.getApellidos();
		                if(nombreadmin.equalsIgnoreCase(nombreadministrador+" "+apellidoadministrador))
		                {
		                	claveAlmacenada = administradorAux.getPassword();
		                	actualadmin = administradorAux;
		                }
		            }
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
				
				boolean passwordcorrecto = inicio.validarPasswordDigitado(claveDigitada, claveAlmacenada);
				if(passwordcorrecto == true)
				{
					//verificar que no tenga sesion abierta
					List sesionesadmin = null;
					boolean sesionabierta = false;
					try 
					{
						Conector conector = new Conector();
						conector.iniciarConexionBaseDatos();
						 sesionesadmin = SesionAdministradorBD.listar(conector);
						conector.terminarConexionBaseDatos();
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					if(sesionesadmin != null)
					{
						for (int i = 0; i < sesionesadmin.size(); i++)
						{
							SesionAdministrador sesaadminaux = (SesionAdministrador)sesionesadmin.get(i);
							int idadminsesion = sesaadminaux.getIdadministrador();
							if(idadminsesion == actualadmin.getIdadmin())
							{
								Sesion sesioningr = null;
								try 
								{
									Conector conector = new Conector();
									conector.iniciarConexionBaseDatos();
									sesioningr = SesionBD.buscarIdSesion(sesaadminaux.getIdsesion(), conector);
									conector.terminarConexionBaseDatos();
								}
								catch (Exception e)
								{
									JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
								}
								if(sesioningr != null)
								{
									String horafinal = sesioningr.getHorafinal();
									if(horafinal == null)
									{
										sesionabierta = true;
										break;
									}
								}
							}
						}
					}
					this.dispose();
					if(sesionabierta == false)
					{
						inicio.verSesionAdministrador(actualadmin);
					}
					else
					{
						inicio.verSesionAdministrador(actualadmin);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"La Contraseña es Incorrecta.","Contraseña incorrecta",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}