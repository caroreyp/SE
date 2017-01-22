package mundo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
public class MotorInferencia 
{
	/**
	 * Es la variable que establece el limite o puntaje minimo requerido para pasar las pruebas.
	 */
	public double limitepuntaje;
	
	/**
	 * Es el vector que almacena los puntajes obtenidos en todas las escalas pertenecientes a la base del conocimiento.
	 */
	private ArrayList puntajesescalastodas;
	
	/**
	 * Es el vector que almacena los puntajes obtenidos en las escalas utilizadas para evaluar las competencias segun los roles seleccionados en las pruebas.
	 */
	private ArrayList puntajesescalasprueba;

	/**
	 * Es el vector que almacena los puntajes obtenidos en las competencias utilizadas para evaluar los roles seleccionados en las pruebas.
	 */
	private ArrayList puntajescompetenciasprueba;

	/**
	 * Es el vector que almacena los puntajes obtenidos en los roles seleccionados en las pruebas.
	 */
	private ArrayList puntajesrolesprueba;

	/**
	 * Es la variable que almacena el id del resultado de la prueba que se esta evaluando.
	 */
	private int idresultadoactual;
	
	/**
	 * Es el metodo constructor de la calse.
	 */
	public MotorInferencia()
	{
		limitepuntaje = 0.6;
		puntajesescalastodas = new ArrayList();
		puntajesescalasprueba = new ArrayList();
		puntajescompetenciasprueba = new ArrayList();
		puntajesrolesprueba = new ArrayList();
		idresultadoactual = 0;
	}
	
	
	/**
	 * Es el metodo que calcula los puntajes de todas las escalas de la base del conocimiento.
	 * Se tiene en cuenta las respuestas del candidato que se obtienen del vector recibido como parametro.
	 * @param respuestas
	 */
	public void calcularPuntajesTodasLasEscalas(ArrayList respuestas)
	{
		puntajesescalastodas.clear();
		//Se obtienen todas las escalas de la base del conocimineto en una lista.
		int numeroescalas = 0;//numero de escalas en la BD
		List escalas = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            escalas= EscalaBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        if(escalas != null)
        {
        	numeroescalas = escalas.size();
        }
        //Por cada escala se obtienen las preguntas relacionadas para evaluar dicha escala.
        for (int i = 0; i < numeroescalas; i++)
		{
			Escala escalaaux = (Escala)escalas.get(i);
			int idescalaactual = escalaaux.getIdescala();
			String nombreescalaactual = escalaaux.getNombre();
			double puntajeescalaactual = 0;
			double numeropreguntasescala = 0;
			double numeropreguntascorrectasescala = 0;
			List preguntasescalact = null;
			try
	        {
	            Conector conector = new Conector();
	            conector.iniciarConexionBaseDatos();
	            preguntasescalact = PreguntaEscalaBD.buscarPreguntasEscala(idescalaactual, conector);
	            conector.terminarConexionBaseDatos();
	        }
	        catch (Exception e)
			{
	        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
	        if(preguntasescalact != null)
	        {
	        	numeropreguntasescala = preguntasescalact.size();
	        	//Por cada pregunta obtenida se compara la respuesta del candidato
		        for (int j = 0; j < preguntasescalact.size(); j++) 
		        {
		        	PreguntaEscala preguntescalaaux = (PreguntaEscala)preguntasescalact.get(j);
					int idpregunta = preguntescalaaux.getIdpregunta();
					String respuestacorrecta = preguntescalaaux.getRespuesta();
					Pregunta preguntaact = null;
					try
			        {
			            Conector conector = new Conector();
			            conector.iniciarConexionBaseDatos();
			            preguntaact = PreguntaBD.buscarIdPregunta(idpregunta, conector);
			            conector.terminarConexionBaseDatos();
			        }
			        catch (Exception e)
					{
			        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
			        if(preguntaact != null)
			        {
			        	int ordenactual = preguntaact.getOrdennumerico();
			        	for (int k = 0; k < respuestas.size(); k++) 
			    		{
			    			Respuesta respuestaux = (Respuesta)respuestas.get(k);
			    			String preguntaprueba = respuestaux.getPregunta();
			    			String respuestaprueba = (respuestaux.getRespuesta()).trim();
			    			int ordenpreguntaprueba = k+1;
			    			if(ordenpreguntaprueba == ordenactual)
			    			{
			    				if(respuestaprueba.equalsIgnoreCase(respuestacorrecta.trim()))
			    				{
			    					numeropreguntascorrectasescala++;
			    				}
			    				break;
			    			}
			    		}
			        }
				}
	        }
	        //se calcula el puntaje en la escala actual segun las preguntas contestadas correctamente
	        puntajeescalaactual = numeropreguntascorrectasescala/numeropreguntasescala;
	        PuntajeEscala puntajescalactual = new PuntajeEscala(puntajeescalaactual,idescalaactual,nombreescalaactual);
	        puntajesescalastodas.add(puntajescalactual);
		}
	}
	
	/**
	 * Es el metodo que calcula el puntaje de cada rol seleccionado en la prueba, lo roles se reciben en un vector como parametro.
	 * Se tiene en cuenta los puntajes de las escalas utilizadas para evaluar las competencias de cada rol.
	 * @param rolesactuales
	 */
	public void calcularPuntajeRoles(ArrayList rolesactuales)
	{
		puntajescompetenciasprueba.clear();
		puntajesescalasprueba.clear();
		puntajesrolesprueba.clear();
		//se obtienen el rol o roles seleccionados en la prueba
		for (int i = 0; i < rolesactuales.size(); i++)
		{
			Rol rolaux = (Rol)rolesactuales.get(i);
			int idrolactual = rolaux.getIdrol();
			String nombrerolactual = rolaux.getNombre();
			double puntajerolactual = 0;
			double numerocompetenciasrol = 0;
			double sumapuntajecompetenciasrol = 0;
			List competenciasrolact = null;
			try
	        {
	            Conector conector = new Conector();
	            conector.iniciarConexionBaseDatos();
	            competenciasrolact = CompetenciaRolBD.buscarCompetenciasRol(idrolactual, conector);
	            conector.terminarConexionBaseDatos();
	        }
	        catch (Exception e)
			{
	        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
	        if(competenciasrolact != null)
	        {
	        	puntajescompetenciasprueba.clear();
	        	//por cada rol se obtienen las competencias relacionadas con dicho rol.
	        	numerocompetenciasrol = competenciasrolact.size();
	        	for (int j = 0; j < competenciasrolact.size(); j++) 
		        {
	        		CompetenciaRol competenciarolaux = (CompetenciaRol)competenciasrolact.get(j);
					int idcompeten = competenciarolaux.getIdcompetencia();
					double puntajecompetenciaactual = 0;
					double numeroescalascompetencia = 0;
					double sumapuntajeescalascompetencia = 0;
					Competencia competenciaact = null;
					try
			        {
			            Conector conector = new Conector();
			            conector.iniciarConexionBaseDatos();
			            competenciaact = CompetenciaBD.buscarIdCompetencia(idcompeten, conector);
			            conector.terminarConexionBaseDatos();
			        }
			        catch (Exception e)
					{
			        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
			        if(competenciaact != null)
			        {
			        	//por cada competencia se obtienen las escalas relacionadas para evaluar dicha competencia.
			        	List escalascompetenciaact = null;
						try
				        {
				            Conector conector = new Conector();
				            conector.iniciarConexionBaseDatos();
				            escalascompetenciaact = CompetenciaEscalaBD.buscarEscalasCompetencia(competenciaact.getIdcompetencia(), conector);
				            conector.terminarConexionBaseDatos();
				        }
				        catch (Exception e)
						{
				        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
						}
				        if(escalascompetenciaact != null)
				        {
				        	puntajesescalasprueba.clear();
				        	numeroescalascompetencia = escalascompetenciaact.size();
				        	for (int k = 0; k < escalascompetenciaact.size(); k++) 
				        	{
				        		CompetenciaEscala preguntescalaaux = (CompetenciaEscala)escalascompetenciaact.get(k);
				        		int idescala = preguntescalaaux.getIdescala();
				        		Escala escalact = null;
								try
						        {
						            Conector conector = new Conector();
						            conector.iniciarConexionBaseDatos();
						            escalact = EscalaBD.buscarIdEscala(idescala, conector);
						            conector.terminarConexionBaseDatos();
						        }
						        catch (Exception e)
								{
						        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
								}
						        if(escalact != null)
						        {
						        	for (int l = 0; l < puntajesescalastodas.size(); l++) 
						        	{
						        		PuntajeEscala puntajeescaltemp = (PuntajeEscala)puntajesescalastodas.get(l);
						        		int idescalapuntaje = puntajeescaltemp.getIdescala();
						        		if(idescalapuntaje == (escalact.getIdescala()))
								        {
						        			puntajesescalasprueba.add(puntajeescaltemp);
						        			sumapuntajeescalascompetencia += puntajeescaltemp.getPuntajeescala();
						        			break;
								        }	
									}
						        }
							}
				        	//se calcula el puntaje en la competencia actual con los puntajes de las escalas que evaluan dicha competencia
				        	puntajecompetenciaactual = (sumapuntajeescalascompetencia/numeroescalascompetencia);
				        	
				        	ArrayList escalascompeten = new ArrayList();
				        	for (int k = 0; k < puntajesescalasprueba.size(); k++)
				        	{
				        		PuntajeEscala escalatemp = (PuntajeEscala)puntajesescalasprueba.get(k);
				        		escalascompeten.add(escalatemp);
							}
					        PuntajeCompetencia puntajcompetenciactual = new PuntajeCompetencia(puntajecompetenciaactual,competenciaact.getIdcompetencia(),competenciaact.getNombre(),escalascompeten);
					        puntajescompetenciasprueba.add(puntajcompetenciactual);
					        sumapuntajecompetenciasrol += puntajecompetenciaactual;
				        }
			        }
		        }
	        	//se calcula el puntaje en el rol actual con los puntajes obtenidos en las competencias relativas a dicho rol.
	        	puntajerolactual = sumapuntajecompetenciasrol/numerocompetenciasrol;
	        	ArrayList competenciasrol = new ArrayList();
	        	for (int j = 0; j < puntajescompetenciasprueba.size(); j++) 
	        	{
	        		PuntajeCompetencia competemp = (PuntajeCompetencia)puntajescompetenciasprueba.get(j);
	        		competenciasrol.add(competemp);
				}
		        PuntajeRol nuevopuntajerol = new PuntajeRol(puntajerolactual, idrolactual, nombrerolactual,competenciasrol);
		        puntajesrolesprueba.add(nuevopuntajerol);
	        }
		}
	}
	
	
	/**
	 * Es el metodo que calcula el puntaje final de la prueba, este determina directamente un resultado cualitativo.
	 * Se tiene en cuenta los puntajes obtenidos en cada rol que fue seleccionado para presentar la prueba.
	 * @param resultado
	 * @param rolesactuales
	 */
	public void calcularPuntajeResultadoPrueba(Resultado resultado, ArrayList rolesactuales)
	{
		//se calcula el puntaje de la prueba, este el promedio de los puntajes de los roles seleccionados
		double puntajeprueba = 0;
		double sumapuntajeroles = 0;
		for (int i = 0; i < puntajesrolesprueba.size(); i++)
        {
			PuntajeRol puntajerolaux = (PuntajeRol)puntajesrolesprueba.get(i);
			sumapuntajeroles += puntajerolaux.getPuntajerol();
		}
		double numeropuntajeroles = puntajesrolesprueba.size();
		puntajeprueba = sumapuntajeroles/numeropuntajeroles;
		//se asigna el puntaje final de la prueba
		double puntajeaproximado = (Math.rint(puntajeprueba*100))/100;
		resultado.setPuntaje(puntajeaproximado);
		//se adiciona el resultado a la base de datos
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			ResultadoBD.insertar(resultado, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		//double puntajeaproximado = (Math.rint(puntajeprueba*100))/100;
		Resultado resultadoid = null;
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			resultadoid = ResultadoBD.buscarResultadoFechaHoraPuntaje(resultado.getFechaprueba(), resultado.getHorainicial(), resultado.getHorafinal(), puntajeaproximado, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		int idresultado = 0;
		if(resultadoid != null)
		{
			//al obtener el id del resultado se crean las relaciones de la tabla resuladorol
			idresultado = resultadoid.getIdresultado();
			idresultadoactual = idresultado;
			for (int i = 0; i < rolesactuales.size(); i++) 
			{
				Rol rolid = (Rol)rolesactuales.get(i);
				int idrol = rolid.getIdrol();
				ResultadoRol resultadorol = new ResultadoRol();
				resultadorol.setIdresultado(idresultado);
				resultadorol.setIdrol(idrol);
				try 
				{
					Conector conector = new Conector();
					conector.iniciarConexionBaseDatos();
					ResultadoRolBD.insertar(resultadorol, conector);
					conector.terminarConexionBaseDatos();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
			}
		}
	}
	
	
	/**
	 * Es el metodo que hace un analisis de los puntajes obtenidos y determina unas observaciones finales.
	 * Las observaciones definen el perfil psicologico y las competencias del candidato segun los puntajes. 
	 */
	public void determinarObservacionesResultadoPrueba()
	{
		//se realiza un analisis de los puntajes obtenidos en las escalas, competencias y roles, pera definir unas observaciones finales.
		
		//se compara el puntaje de la escala mendacidad y si es mayor o igual a 0.6 determinar que el candidato no fue sincero al responder la prueba.
		//se compara el puntaje de la escala mendacidad y si es menor a 0.6 determinar que el candidato fue sincero al responder la prueba.
		String observaciones = "";
		//se verifica la escala de mendacidad para saber la veracidad de la prueba.
		for (int i = 0; i < puntajesescalastodas.size(); i++) 
    	{
    		PuntajeEscala puntajeescaltemp = (PuntajeEscala)puntajesescalastodas.get(i);
    		String nombretemp = puntajeescaltemp.getNombreescala();
    		if(nombretemp.equalsIgnoreCase("Mendacidad") || nombretemp.equalsIgnoreCase("Menticidad"))
	        {
    			double puntajemendacidad = puntajeescaltemp.getPuntajeescala();
    			if(puntajemendacidad >= limitepuntaje)
    			{
    				observaciones+="El candidato no fue sincero al contestar la prueba. ";
    			}
    			else
    			{
    				if(puntajemendacidad > 0)
        			{
    					observaciones+="El candidato fue sincero al contestar la prueba. ";
        			}
    			}
	        }	
		}
		//se compara el puntaje en cada escala utilizada en las competencias y si es mayor o igual a 0.6 agregar que cumple con las caracterisiticas de la escala respectiva a observaciones
		//se compara el puntaje en cada escala utilizada en las competencias y si es menor a 0.6 agregar que No cumple con las caracterisiticas de la escala respectiva a observaciones
		
		//se compara el puntaje en cada competencia de los roles seleccionados y si es mayor o igual a 0.6 agregar que cumple con dicha competencia a observaciones
		//se compara el puntaje en cada competencia de los roles seleccionados y si es menor a 0.6 agregar que no cumple con dicha competencia a observaciones
		
		//se compara el puntaje en cada rol y si es mayor o igual a 0.6 agregar que es aceptable o recomendable para dicho rol a observaciones
		//se compara el puntaje en cada rol y si es menor a 0.6 agregar que no es aceptable o no cumple para desempeñarse en dicho rol a observaciones
		
		//se compara el puntaje de la prueba y si es mayor o igual a 0.6 determinar que el candidato obtubo un puntaje o resultado bueno en la prueba.
		//se compara el puntaje de la prueba y si es menor a 0.6 determinar que el candidato obtubo un puntaje o resultado malo en la prueba.
		
		//se analizan los puntajes obtenidos en las escalas, competencias y roles que apliquen segun la prueba, para determinar las observaciones
		for (int i = 0; i < puntajesrolesprueba.size(); i++)
        {
			PuntajeRol auxrol = (PuntajeRol)puntajesrolesprueba.get(i);
        	ArrayList competencias = auxrol.getPuntajescompetenciasprueba();
        	double puntajerol = auxrol.getPuntajerol();
        	for (int j = 0; j < competencias.size(); j++) 
        	{
        		PuntajeCompetencia auxcompetencia = (PuntajeCompetencia)competencias.get(j);
            	ArrayList escalas = auxcompetencia.getPuntajesescalasprueba();
            	for (int k = 0; k < escalas.size(); k++)
            	{
            		PuntajeEscala auxescala = (PuntajeEscala)escalas.get(k);
            		String carateristicas = "";
            		double puntajeescala = auxescala.getPuntajeescala();
        			if(puntajeescala >= limitepuntaje)
        			{
        				carateristicas = obtenerCaracterisiticasEscala(auxescala.getIdescala(), "es ");
        				if(observaciones.contains(carateristicas) == false)
        				{
        					observaciones+="El candidato "+carateristicas+". ";
        				}
        			}
        			else
        			{
        				if(puntajeescala > 0)
        				{
            				PuntajeEscala opuesta = obtenerPuntajeEscalaOpuesta(auxescala.getNombreescala());
            				if((opuesta.getPuntajeescala()) >= limitepuntaje)
            				{
            					carateristicas = obtenerCaracterisiticasEscala(opuesta.getIdescala(), "es ");
                				if(observaciones.contains(carateristicas) == false)
                				{
                					observaciones+="El candidato "+carateristicas+". ";
                				}
            				}
        				}
        			}
				}
            	double puntajecompetencia = auxcompetencia.getPuntajecompetencia();
            	if((puntajecompetencia) <= (limitepuntaje-0.15))
    			{
            		if(puntajecompetencia > 0)
    				{
            			//if(observaciones.contains("tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ") == false)
        				//{
        					observaciones+="El candidato no tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ";
        				//}
    				}
    			}
            	else
            	{
            		if((puntajecompetencia-0.15) >= limitepuntaje)
        			{
        				//if(observaciones.contains("tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ") == false)
        				//{
        					observaciones+="El candidato tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ";
        				//}
        			}
        			else
        			{
        				if(puntajerol >= limitepuntaje)
            			{
            				//if(observaciones.contains("tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ") == false)
            				//{
            					observaciones+="El candidato tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ";
            				//}
            			}
            			else
            			{
        					//if(observaciones.contains("tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ") == false)
            				//{
            					observaciones+="El candidato no tiene capacidad de "+auxcompetencia.getNombrecompetencia()+". ";
            				//}
            			}
        			}
            	}
			}
			if(puntajerol >= limitepuntaje)
			{
				observaciones+="El candidato es aceptable o recomendable para desempeñarse como "+auxrol.getNombrerol()+". ";
			}
			else
			{
				observaciones+="El candidato no es aceptable o no cumple para desempeñarse como "+auxrol.getNombrerol()+". ";
			}
		}
		if(puntajesrolesprueba.size() > 0)
		{
			double mayorpuntajerol = 0;
			String mayornombrerol = "";
			for (int i = 0; i < puntajesrolesprueba.size(); i++)
	        {
				PuntajeRol auxrol = (PuntajeRol)puntajesrolesprueba.get(i);
				double puntajeaux = auxrol.getPuntajerol();
				if(mayorpuntajerol < puntajeaux)
				{
					mayorpuntajerol = puntajeaux;
					mayornombrerol = auxrol.getNombrerol();
				}
	        }
			if(mayorpuntajerol >= limitepuntaje)
			{
				observaciones+="El rol más adecuado para el candidato es "+mayornombrerol+", teniendo en cuenta los roles evaluados para los resultados de la prueba. ";
			}
		}
		//al tener listas las observaciones se actuliza el resultado obtenido con el idresultadoactual
		Resultado resultadoactual = null;
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			resultadoactual = ResultadoBD.buscarIdResultado(idresultadoactual, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(resultadoactual != null)
		{
			
			double puntaje = resultadoactual.getPuntaje();
			if((puntaje+0.05) >= limitepuntaje)
			{
				observaciones+="El candidato obtuvo un buen resultado en la prueba en general. ";
			}
			else
			{
				observaciones+="El candidato obtuvo un mal resultado en la prueba en general. ";
			}
			//se actualiza el resultado con las observaciones.
			resultadoactual.setObservaciones(observaciones);
			try 
			{
				Conector conector = new Conector();
				conector.iniciarConexionBaseDatos();
				ResultadoBD.actualizar(resultadoactual, conector);
				conector.terminarConexionBaseDatos();
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
	}
	
	/**
	 * Es el metodo que retorna las caracterisiticas del id de la escala que recibe como parametro.
	 * @param idescala
	 * @param es
	 * @return caracteristicas
	 */
	public String obtenerCaracterisiticasEscala(int idescala, String es)
	{
		String caracteristicas = "";
		List caracteristicasescal = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            caracteristicasescal = CaracteristicaBD.buscarCaracterisiticasEscala(idescala, conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        if(caracteristicasescal != null)
        {
        	for (int i = 0; i < caracteristicasescal.size(); i++) 
        	{
				Caracteristica caraux = (Caracteristica)caracteristicasescal.get(i);
				String nombrecaract = caraux.getNombre();
				if((caracteristicasescal.size()) == (i+1))
				{
					caracteristicas += es+nombrecaract+"";
				}
				else
				{
					caracteristicas += es+nombrecaract+", ";
				}
			}
        }
        return caracteristicas;
	}
	
	/**
	 * Es el metodo que retorna el puntaje obtenido en la escala opuesta al nombre de la escala recibida como parametro.
	 * @param nombreescal
	 * @return opuesta
	 */
	public PuntajeEscala obtenerPuntajeEscalaOpuesta(String nombreescal)
	{
		PuntajeEscala opuesta = null;
		for (int i = 0; i < puntajesescalastodas.size(); i++) 
		{
			PuntajeEscala auxescala = (PuntajeEscala)puntajesescalastodas.get(i);
    		String nombreaux = auxescala.getNombreescala();
    		if(nombreescal.equalsIgnoreCase("Neuroticismo") && nombreaux.equalsIgnoreCase("Estabilidad"))
    		{
    			opuesta = auxescala;
    			break;
    		}
    		if(nombreescal.equalsIgnoreCase("Estabilidad") && nombreaux.equalsIgnoreCase("Neuroticismo"))
    		{
    			opuesta = auxescala;
    			break;
    		}
    		if(nombreescal.equalsIgnoreCase("Extraversión") && nombreaux.equalsIgnoreCase("Introversión"))
    		{
    			opuesta = auxescala;
    			break;
    		}
    		if(nombreescal.equalsIgnoreCase("Introversión") && nombreaux.equalsIgnoreCase("Extraversión"))
    		{
    			opuesta = auxescala;
    			break;
    		}
    		if(nombreescal.equalsIgnoreCase("Psicoticismo") && nombreaux.equalsIgnoreCase("Normalidad"))
    		{
    			opuesta = auxescala;
    			break;
    		}
    		if(nombreescal.equalsIgnoreCase("Normalidad") && nombreaux.equalsIgnoreCase("Psicoticismo"))
    		{
    			opuesta = auxescala;
    			break;
    		}
		}
		return opuesta;
	}
	
	/**
	 * Es el metodo que reune los datos del candidato, de la prueba presentada, la convocatoria y el resultado generado.
	 * El resultado contiene el puntaje y las observaciones que fueron determinadas.
	 * @param candidatoactual
	 * @param tipopruebaactual
	 * @param convocatoriaactual
	 */
	public void almacenarResultadoPruebaCandidato(Candidato candidatoactual, Prueba tipopruebaactual, Convocatoria convocatoriaactual)
	{
		//se almacena el resultado de la prueba candidato en la base de datos
		int idprueba = tipopruebaactual.getIdprueba();
		int idcandidato = candidatoactual.getIdcandidato();
		int idconvocatoria = -1;
		if(convocatoriaactual != null)
		{
			idconvocatoria = convocatoriaactual.getIdconvocatoria();
		}
		
		PruebaCandidato pruebacandidato = new PruebaCandidato(idprueba, idcandidato, idresultadoactual, idconvocatoria);
		
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			PruebaCandidatoBD.insertar(pruebacandidato, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
	}
}