package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
public class GuiasUsuario extends JDialog implements ActionListener
{
	private JButton btanterior;
	private JButton btsiguiente;
	private InterfazInicio inicio;
	private JPanel panelimagen;
	private int numeroimagenes;
	private ArrayList imagenes;
	private int imagenactual;
		
	public GuiasUsuario(InterfazInicio interfaz, int numeroimageninicio, int numeroimagenfin)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Guias de Usuario - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int anchoventana = 970;
		int altoventana =738;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),0,anchoventana,altoventana);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		inicio=interfaz;
		numeroimagenes=(numeroimagenfin-numeroimageninicio)+1;
		imagenactual=0;

		imagenes = new ArrayList();
		for (int i = numeroimageninicio-1; i < numeroimagenfin; i++) 
		{
			JLabel imagen = new JLabel(new ImageIcon("./images/GuiaUsuario/"+(i+1)+".PNG"));
			imagen.setVisible(false);
			imagenes.add(imagen);
		}
		
		btanterior = new JButton(new ImageIcon("./images/IconosInterfaz/anterior.PNG"));
		btanterior.setToolTipText("Presione para devolver la guia.");
		
		btsiguiente = new JButton(new ImageIcon("./images/IconosInterfaz/siguiente.PNG"));
		btsiguiente.setToolTipText("Presione para avanzar con la guia.");
		
		btanterior.addActionListener(this);
		btanterior.setActionCommand("Anterior");
		btsiguiente.addActionListener(this);
		btsiguiente.setActionCommand("Siguiente");
		
		btsiguiente.setPreferredSize(new Dimension(90,40));
		btsiguiente.setBackground(new Color(238,238,238));
		btsiguiente.setForeground(new Color(102,122,179));
		btsiguiente.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32 ));
		
		btanterior.setPreferredSize(new Dimension(90,40));
		btanterior.setBackground(new Color(238,238,238));
		btanterior.setForeground(new Color(102,122,179));
		btanterior.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32 ));
		
		panelimagen = new JPanel(new FlowLayout());
		panelimagen.setPreferredSize(new Dimension (960,643));
		panelimagen.setBackground(new Color(68,146,132));
		TitledBorder titulopanel = new TitledBorder("Guia "+(imagenactual+1));
		titulopanel.setTitleFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 18 ));
		titulopanel.setTitleColor(new Color(244,255,213));
		panelimagen.setBorder(titulopanel);
		
		JLabel primera = (JLabel)imagenes.get(imagenactual);
		primera.setVisible(true);
		
		btanterior.setEnabled(false);
		
		for (int i = 0; i < imagenes.size(); i++) 
		{
			JLabel aux = (JLabel)imagenes.get(i);
			panelimagen.add(aux);
		}

		add(panelimagen);
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (960,50));
		panelcolor.setBackground(new Color(68,146,132));
		
		panelcolor.add(btanterior);
		panelcolor.add(new JLabel("          "));
		panelcolor.add(btsiguiente);
		add(panelcolor);
	}
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Anterior"))
		{
			for (int i = 0; i < imagenes.size(); i++) 
			{
				JLabel aux = (JLabel)imagenes.get(i);
				aux.setVisible(false);
			}

			if(imagenactual == 0)
			{
				imagenactual = 0;
				btanterior.setEnabled(false);
			}
			else
			{
				imagenactual--;
				btanterior.setEnabled(true);
				btsiguiente.setEnabled(true);
				if(imagenactual == 0)
				{
					btanterior.setEnabled(false);
				}
			}
			JLabel aux = (JLabel)imagenes.get(imagenactual);
			aux.setVisible(true);
			TitledBorder titulopanel = new TitledBorder("Guia "+(imagenactual+1));
			titulopanel.setTitleFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 18 ));
			titulopanel.setTitleColor(new Color(244,255,213));
			panelimagen.setBorder(titulopanel);
		}
		else if (clic.equals("Siguiente"))
		{
			btanterior.setEnabled(true);
			for (int i = 0; i < imagenes.size(); i++) 
			{
				JLabel aux = (JLabel)imagenes.get(i);
				aux.setVisible(false);
			}

			if(imagenactual == (numeroimagenes-1))
			{
				imagenactual = (numeroimagenes-1);
				btsiguiente.setEnabled(false);
			}
			else
			{
				imagenactual++;
				btanterior.setEnabled(true);
				btsiguiente.setEnabled(true);
				if(imagenactual == (numeroimagenes-1))
				{
					btsiguiente.setEnabled(false);
				}
			}
			JLabel aux = (JLabel)imagenes.get(imagenactual);
			aux.setVisible(true);
			TitledBorder titulopanel = new TitledBorder("Guia "+(imagenactual+1));
			titulopanel.setTitleFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 18 ));
			titulopanel.setTitleColor(new Color(244,255,213));
			panelimagen.setBorder(titulopanel);
		}
	}
}