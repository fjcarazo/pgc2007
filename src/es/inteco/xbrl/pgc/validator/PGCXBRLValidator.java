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


package es.inteco.xbrl.pgc.validator;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import org.apache.log4j.Logger;

import es.inteco.xbrl.pgc.errors.GenericErrorsHandler;
import es.inteco.xbrl.pgc.errors.PGCErrorsHandler;
import es.inteco.xbrl.pgc.errors.exceptions.XBRLPGCException;
import es.inteco.xbrl.pgc.errors.exceptions.XBRLValidatorException;
import es.inteco.xbrl.pgc.transform.ConfigurationManager;
import es.inteco.xbrl.pgc.transform.config.ConfigReport;
import es.inteco.xbrl.pgc.utils.PGCUtils;




/**
 *
 * Clase que gestiona la validaci�n de un documento XBRL contra la taxonom�a e incorpora traduccion a c�digos PGC.
 * 
 * Esta clase genera un resultado indicando si la validaci�n ha sido correcta, para ello
 * utiliza la clase ValidateResult, en la cual indica si la validaci�n fue bien o no
 * en cuyo caso informa de los errores de validaci�n que se hayan encontrado.
 * 
 * Es una extensi�n de la clase XBRLValidator, mediante la cual se hace un tratamiento
 * diferente en la devoluci�n de los errores.  Dicho tratamiento consiste en a�adir
 * informaci�n de los c�digos PGC que han podido ser traducidos, mediante la consulta 
 * a los mapas, de los conceptos de la taxonom�a que puedan aparecer en los mensajes
 * de error.
 * 
 * El resto del tratamiento de errores es id�ntico al realizado en la clase XBRLValidator.
 *
 *
 *
 *<br><br>
 * <b>Proyecto</b>: API XBRL-PGC2007 - Grupo de utilidades y librer�as en c�digo abierto para facilitar 
 *                             la integraci�n del formato XBRL en las herramientas software de gesti�n de  terceros
 *                             aisl�ndose de la complejidad en el procesamiento del modelo de datos de las taxonom�as.
 *                             Ayudando de esta forma a las empresas finales en la labor de realizaci�n de informes XBRL
 *                             y asegurar el �xito de implantaci�n del nuevo Plan General de Contabilidad 2007 en formato XBRL
 *
 *
 * @version 1.0, 14/01/2009
 * @author difusioncalidad@inteco.es
 *
 */


public class PGCXBRLValidator extends XBRLValidator
{
    static Logger logger = Logger.getLogger(PGCXBRLValidator.class);
    
    String reportID = null;
    
    /*Constructor*/
    protected PGCXBRLValidator()
    {
	super();
    }
    
    

    /* (non-Javadoc)
     * @see es.inteco.xbrl.pgc.validator.XBRLValidator#validate(java.lang.String)
     */
    public ValidateResult validate(String documentPath) throws XBRLPGCException
    {
	ValidateResult validateResult = null;
	
	try
	{
	    FileInputStream inputStreamDocument = new FileInputStream(documentPath);
	    String schemaRef = PGCUtils.getSchemaRef(inputStreamDocument);
	    inputStreamDocument.close();
	    
	    
	    ConfigReport cr =ConfigurationManager.getInstance().getGlobalReportFromSchemaRef(schemaRef);

	    
	    if ( cr != null )
	    {
	    	reportID = cr.getId();
	    }
	    
	    
	    validateResult = super.validate(documentPath);
	}
	catch(XBRLPGCException ex)
	{
	    throw ex;
	
	} catch (Exception e)
	{
	    logger.error(e.getMessage());
	    throw new XBRLValidatorException(XBRLPGCException.genericErrorValidatingInstance, new String[]{documentPath},e);
	}
	
	return validateResult;
    }
    
    
    /* (non-Javadoc)
     * @see es.inteco.xbrl.pgc.validator.XBRLValidator#validate(byte[])
     */
    public ValidateResult validate(byte[] inputDocument) throws XBRLPGCException
    {
	ValidateResult validateResult = null;
	
	try
	{
	    ByteArrayInputStream inputStreamDocument = new ByteArrayInputStream(inputDocument);
	    String schemaRef = PGCUtils.getSchemaRef(inputStreamDocument);
	    inputStreamDocument.close();
	    
	    //reportID = ConfigurationManager.getInstance().getGlobalReportFromSchemaRef(schemaRef).getId();
	    
	    
	    ConfigReport cr =ConfigurationManager.getInstance().getGlobalReportFromSchemaRef(schemaRef);

	    
	    if ( cr != null )
	    {
	    	reportID = cr.getId();
	    }
	    
	    
	    validateResult = super.validate(inputDocument);
	}
	catch(XBRLPGCException ex)
	{
	    throw ex;
	
	} catch (Exception e)
	{
	    logger.error(e.getMessage());
	    throw new XBRLValidatorException(XBRLPGCException.genericErrorValidatingInstance, new String[]{},e);
	}
	
	return validateResult;
    }
    
    
 
    
    
    /**
     * Devuelve una instancia de la propia clase
     * @return
     * Objeto del tipo IXBRLValidator
     */
    public static IXBRLValidator createInstance(){
	return new PGCXBRLValidator();
    }
    
    /**
     * Punto de entrada de ejecuci�n
     * @param args
     */
    public static void main(String args[])
    {
    	String documentPath = null;
    	byte[] inputDocument = null;
    	
    	if(args.length == 0 || (args.length != 1 && args.length != 2)){
    		errorParameters();
    	}else{
    		for (int i=0; i<args.length; i++) {
        		
        		if (args[i].charAt(0) == '-') {
        			
        			switch (args[i].charAt(1)) {
        				case 'h': use(); System.exit(0);
        				case 'd':
        					if (i+1 < args.length) 
        						documentPath = args[++i];
        					else
        						errorParameters();
        					break;
        				case 'i':
        					if (i+1 < args.length) 
        						inputDocument = args[++i].getBytes();
        					else
        						errorParameters();
        					break;
        				default:
        					errorParameters();
        			}
        		}
        		else
        			errorParameters();
       		}
    	}
   	
    	PGCXBRLValidator validator = new PGCXBRLValidator();
    	ValidateResult validateResult = null;
	
	try
	{
			if(documentPath != null && inputDocument == null){
				validateResult = validator.validate(documentPath);
			}
			else if(inputDocument != null && documentPath == null){
				validateResult = validator.validate(inputDocument);
			}
			else
				errorParameters();
			printResults(validateResult);
	} catch (Throwable e)
	{
	    logger.error(e);
	}
    }
    
    /*Muestra el contenido del ValidateResult*/
    static private void printResults(ValidateResult validateResult)
    {

    	String errors = null;
    	if(validateResult == null)
    		errorParameters();
    	else if(!validateResult.isValid()){
    		errors = validateResult.getErrors();
    		logger.error("Se encontraron errores en la validaci�n:");
    		logger.error(errors);
    	}
    	else if(validateResult.isValid()){
    		logger.info("Validaci�n correcta");
    	}
    }
    
    /*Metodo que gestiona los mensajes al usuario cuando existe error en los par�metros*/
    static private void errorParameters()
    {
    	logger.error("Error en los par�metros");
    	use();
    	System.exit(0);
    }
    
    /*Metodo que presenta por salida estandar los errores al usuario cuando hay error en los par�metros*/
    static private void use()
    {
    	System.out.println(); 
    	System.out.println("API XBRL PGC 2007");
	    System.out.println();    	
	    System.out.println("Uso: xbrlvalidator [-d documentPath]");
	    System.out.println("					Realiza validaci�n XBRL del documento [documentPath].");
	    System.out.println();    	
	    System.out.println("Uso: xbrlvalidator [-i inputPath]");
	    System.out.println("					Realiza validaci�n XBRL del array de bytes [inputPath].");
	    System.out.println();    	
	    System.out.println("Uso: xbrlvalidator -h");
	    System.out.println("     				Muestra esta ayuda");
	    System.out.println();
    }


    @Override
    protected GenericErrorsHandler getErrorsHandler()
    {
	return new PGCErrorsHandler(reportID);
    }

}
