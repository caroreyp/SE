package interfazGrafica;

import javax.swing.table.*;

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
class GenderTableModel extends AbstractTableModel {
	  // Implementation of TableModel interface 
	  public int getRowCount() {
	    return data.length;
	  }

	  public int getColumnCount() {
	    return COLUMN_COUNT;
	  }

	  public Object getValueAt(int row, int column) {
	    return data[row][column];
	  }

	  public String getColumnName(int column) {
	    return "";
	  }

	  public boolean isCellEditable(int row, int column) {
	    return column == 1;
	  }

	  public void setValueAt(Object value, int row, int column) {
	    if (isValidValue(value)) {
	      data[row][column] = value;
	      fireTableRowsUpdated(row, row);
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

	  protected static final int COLUMN_COUNT = 2;
	    
	  protected static final String[] validStates = { 
	    "Femenino", "Masculino"
	  };

	  protected Object[][] data = new Object[][] {
	    { "Genero", validStates[0] }
	  };
	  
	  protected static final String[] columnNames = {
	    "Genero Candidato(a)", "Seleccione"
	  };

	}