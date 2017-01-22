package interfazGrafica;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

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
class RolesTableModel extends AbstractTableModel {
	  // Implementation of TableModel interface 
	  public int getRowCount() {
		  List roles = null;//roles de la BD
		  
		  try 
		  {
			  Conector conector = new Conector();
			  conector.iniciarConexionBaseDatos();
			  roles= RolBD.listar(conector);
			  conector.terminarConexionBaseDatos();
		  } catch (Exception e){
			  JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
		  }
		  
		  int numeroRoles = 0;
		  
		  if(roles != null){
			  numeroRoles = roles.size();
		  }
		  
		  if(numeroRoles<=RowCount)
		  {
			  for (int i = 0; i < numeroRoles; i++)
			  {
				  Rol rolaux = (Rol)roles.get(i);
				  data[i][0] =rolaux.getNombre();
				  if(comenzo == true)
				  {
					  data[i][1] =validStates[0];
				  }
			  }
			  if(comenzo == true)
			  {
				  comenzo=false; 
			  }
		  }
	    return data.length;
	  }

	  public int getColumnCount() {
	    return COLUMN_COUNT;
	  }

	  public Object getValueAt(int row, int column) {
	    return data[row][column];
	  }

	  public String getColumnName(int column) {
	    return columnNames[column];
	  }

	  public boolean isCellEditable(int row, int column) {
	    return column == 1;
	  }

	  public void setValueAt(Object value, int row, int column) 
	  {
	    if (isValidValue(value)) {
	      data[row][column] = value;
	      fireTableRowsUpdated(0, row);
	    }
	  }

	  // Extra public methods
	  public static String[] getValidStates() {
	    return validStates;
	  }

	  // Protected methods
	  protected boolean isValidValue(Object value) {
	    if (value instanceof String) {
	      String sValue = (String)value;

	      for (int i = 0; i < validStates.length; i++) {
	        if (sValue.equals(validStates[i])) {
	          return true;
	        }
	      }
	    }

	    return false;
	  }
	  
	  private boolean comenzo = true;

	  protected static final int COLUMN_COUNT = 2;
	  
	  NumeroRoles roles = new NumeroRoles();
	  
	  private int RowCount = roles.getNumeroRoles();
	    
	  protected static final String[] validStates = { 
	    "No Seleccionar", "Seleccionar"
	  };
	  
	  protected Object[][] data = new Object[RowCount][COLUMN_COUNT];
	  
	  
	  protected static final String[] columnNames = {
	    "Roles", "Seleccionar"
	  };

	}

