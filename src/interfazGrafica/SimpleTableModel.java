package interfazGrafica;

import javax.swing.table.AbstractTableModel;

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
class SimpleTableModel extends AbstractTableModel {
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
	    return columnNames[column];
	  }

	  public boolean isCellEditable(int row, int column) {
	    return column == 1;
	  }

	  public void setValueAt(Object value, int row, int column) 
	  {
	      data[row][column] = value;
	      fireTableRowsUpdated(0, row);
	  }

	  protected static final int COLUMN_COUNT = 2;
	  
	  private int RowCount = 1;
	  
	  protected Object[][] data = new Object[RowCount][COLUMN_COUNT];
	  
	  
	  protected static final String[] columnNames = {
	    " ", "Seleccione Aqui"
	  };

	}