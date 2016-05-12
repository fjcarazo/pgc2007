
/**
 *
 * API XBRL-PGC2007 is a set of packages for the treatment of instances XBRL
 * (eXtensible Business Reporting Language) corresponding to the taxonomy PGC2007.
 * The General Plan of Accounting 2007 is the legal text that regulates the accounting of
 * the companies in Spain.
 *
 * This program is part of the API XBRL-PGC2007.
 *
 * Copyright (C) 2009  INTECO (Instituto Nacional de Tecnolog�as de la
 * Comunicaci�n, S.A.)
 *
 * Authors: Members of Software Quality Department inside INTECO
 *
 * E-mail: difusioncalidad@inteco.es
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation; 
 * either version 3 of the License, or (at your opinion) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see http://www.gnu.org/licenses/
 */


package es.inteco.xbrl.pgc.transform;

import java.util.Collection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



/**
*
*
* Clase encargada de traducir un texto (String) y obtener la lista de elementos xbrl y codigos PGC si los hubiera.
*
*
*<br><br>
* <b>Proyecto</b>: API XBRL-PGC2007 - Grupo de utilidades y librer�as en c�digo abierto para facilitar 
*                             la integraci�n del formato XBRL en las herramientas software de gesti�n de  terceros
*                             aisl�ndose de la complejidad en el procesamiento del modelo de datos de las taxonom�as.
*                             Ayudando de esta forma a las empresas finales en la labor de realizaci�n de informes XBRL
*                             y asegurar el �xito de implantaci�n del nuevo Plan General de Contabilidad 2007 en formato XBRL
*
* @version 1.0, 18/02/2009
* @author difusioncalidad@inteco.es
*
*/
public class Translator {

	private String textToTranslate=null;
	private String reportID=null;
	private Collection<String> foundFactList=null;
	private Collection<String> foundItemIdList=null;
	
	
	
	static Logger logger = Logger.getLogger(Translator.class);
	
	/**
	 * Obtiene la lista de facts encontrados en el proceso de b�squeda
	 * @return
	 * lista de nombres XBRL
	 */
	public Collection<String> getFoundFactList() {
		return foundFactList;
	}

	
	
	/**
	 * Obtiene la lista de items ID (PGCCode) encontrados en el proceso de b�squeda
	 * @return
	 * lista de codigos PGC
	 */
	public Collection<String> getFoundItemIdList() {
		return foundItemIdList;
	}

	/**
	 * Constructor
	 */
	private Translator() {
		super();
	}

	/**
	 * Crea una instancia de la clase a partir de los parametros de entrada
	 * @param textToTranslate
	 * texto a traducir
	 * @param reportID
	 * id del report con el que se est� trabajando que servir� de filtro para
	 * realizar un b�squeda m�s efectiva
	 */
	public Translator(String textToTranslate, String reportID) {
		
		this.textToTranslate = textToTranslate;
		this.reportID = reportID;
		this.loadTranslationFomText();
	}

	
	
	   /**
     * Revisa el parametro de entrada para localizar elementos XBRL y codigos PGC <br>
     * Para acceder a los elementos/codigos encontrados habr� que 
     * @param textIn
     * texto donde se busca
     * @param reportID
     * filtro para realizar la b�squeda en un s�lo report
     */
    private void loadTranslationFomText(){
    	

    	Vector<String> vFact = new Vector<String>();
    	Vector<String> vCode = new Vector<String>();
    	StringBuffer sb = new StringBuffer();
    	try
    	{
   		
    		Pattern p = Pattern.compile("([a-zA-Z_.0-9]+)");
    		Matcher m = p.matcher(textToTranslate);

    		boolean result = m.find();
    		while(result) 
    		{
    			String conceptName = textToTranslate.substring(m.start(), m.end());
    			String code = ConfigurationManager.getInstance().getInfoToTranslate(reportID, null, conceptName);

    			if (code != null)
    			{
    				vFact.add(conceptName);
    				vCode.add(code);
    			}

    			result = m.find();
    		}
    		m.appendTail(sb);


    	} catch (Exception e)
    	{
    		logger.error(e);
    	}

    	
    	this.foundFactList=vFact;
    	this.foundItemIdList=vCode;
    	

    	
    }
	
	
	
	
	
	
	
	

}
