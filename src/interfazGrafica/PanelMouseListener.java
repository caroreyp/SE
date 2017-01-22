package interfazGrafica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

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
public class PanelMouseListener extends JPanel implements MouseListener
{

	private InterfazInicio inicio;
	
	public PanelMouseListener(InterfazInicio interfaz)
	{
		inicio = interfaz;
	    addMouseListener(this);
	}
	 
 	/**
	 * Este método se llama cuando se hace click sobre la superficie del editor.
	 * @param evento Es el evento del click sobre el editor
	 */
    public void mouseClicked( MouseEvent evento )
    {
        if( evento.getButton( ) == MouseEvent.BUTTON1 )
        {
        	inicio.colocarComentariosResultado();
        }
    }

	public void mouseEntered(MouseEvent arg0) 
	{
			
	}

	public void mouseExited(MouseEvent arg0) 
	{
		
	}

	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		
	}
}