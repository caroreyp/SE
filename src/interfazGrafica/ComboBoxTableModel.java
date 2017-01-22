package interfazGrafica;

import javax.swing.*;
import javax.swing.table.*;

import mundo.*;

import basedeDatos.Conector;
import basedeDatos.NumeroPreguntas;
import basedeDatos.PreguntaBD;

import java.util.List;

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
class ComboBoxTableModel extends AbstractTableModel {
  // Implementation of TableModel interface 
  public int getRowCount() {
	  
	  if(preguntas == null)
	  {
		  try 
		  {
			  Conector conector = new Conector();
			  conector.iniciarConexionBaseDatos();
			  preguntas= PreguntaBD.listar(conector); 
			  conector.terminarConexionBaseDatos();
		  } catch (Exception e){
			  JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("./images/IconosInterfaz/error.PNG"));
			  e.printStackTrace();
		  }
	  }
	  
	  int numeroPregunt = 0;
	  
	  if(preguntas != null){
		  numeroPregunt = preguntas.size();
	  }
	  
	  if(numeroPregunt<=RowCount)
	  {
		  for (int i = 0; i < numeroPregunt; i++)
		  {
			  Pregunta pregunt = (Pregunta)preguntas.get(i);
			  data[i][0] =pregunt.getOrdennumerico()+" - "+pregunt.getTexto();
		  }
	  }
	  if(numeroPregunt<RowCount)
	  {
		  data[numeroPregunt][0] ="                             Fin del Cuestionario haga click en Evaluar.";
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

  public boolean isCellEditable(int row, int column) 
  {
	  boolean editable = false;
	  String valorrespuesta = (String) data[row][column];
	  if(column == 1 && valorrespuesta==(null))
	  {
		  editable = true;
	  }
	  if(valorrespuesta!=(null))
	  {
		  if(valorrespuesta.equals("Si") || valorrespuesta.equals("No"))
	      {
	      	 editable = false;
	      }
		  if(column == 1 && valorrespuesta.equals(""))
		  {
			  editable = true;
		  }
	  }
      return editable;
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

  protected static final int COLUMN_COUNT = 2;
  
  List preguntas = null;
  
  NumeroPreguntas numeropreguntas = new NumeroPreguntas();
  
  private int RowCount = numeropreguntas.getNumeroPreguntas();
    
  protected static final String[] validStates = { 
    "", "Si", "No"
  };
  
  protected Object[][] data = new Object[RowCount][COLUMN_COUNT];
  
  
  protected static final String[] columnNames = {
    "Pregunta", "Respuesta"
  };

}
