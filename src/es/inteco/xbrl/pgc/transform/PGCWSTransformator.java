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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import es.inteco.xbrl.pgc.errors.exceptions.XBRLValidatorException;
import es.inteco.xbrl.pgc.errors.exceptions.XSDValidatorException;






/**
 *
 *
 * Clase utilizada para permitir la publicaci�n del servicio de transformaci�n a trav�s
 * de un servicio WEB. 
 *
 *
 *<br><br>
 * <b>Proyecto</b>: API XBRL-PGC2007 - Grupo de utilidades y librer�as en c�digo abierto para facilitar 
 *                             la integraci�n del formato XBRL en las herramientas software de gesti�n de  terceros
 *                             aisl�ndose de la complejidad en el procesamiento del modelo de datos de las taxonom�as.
 *                             Ayudando de esta forma a las empresas finales en la labor de realizaci�n de informes XBRL
 *                             y asegurar el �xito de implantaci�n del nuevo Plan General de Contabilidad 2007 en formato XBRL
 *
 * @version 1.0, 14/01/2009
 * @author difusioncalidad@inteco.es
 *
 */

@WebService()
public class PGCWSTransformator
{

    private static final Logger logger = Logger.getLogger(PGCWSTransformator.class);

    
    
    
    /**
	 * Constructor por defecto
	 */
	public PGCWSTransformator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Operaci�n de servicio web encargada de realizar la transformaci�n de una instancia XBRL a XML en formato
     * com�n.
     * 
     * @param inputDocument
     * array de bytes con el documento de entrada
     * 
     * @param validate
     * valor booleano que indicar� si se debe validar el documento XBRL de entrada, previamente a su transformaci�n.
     * 
     * @return
     * Devuelve un objeto TransformResult con el resultado de la transformaci�n.
     * 
     * @see TransformResult 
     * 
     */   
    @WebMethod(operationName = "transformToXml")
    public TransformResult transformToXml(@WebParam(name = "inputDocument") final byte[] inputDocument, @WebParam(name = "validate") final boolean validate)
    {
	
	TransformResult transResult = new TransformResult();
	try
	{
	    byte[] xmlResult = PGCTransformer.createInstance().transformToXml(inputDocument, validate);
	    transResult.setArrayResult(xmlResult);
	    
	} catch (XSDValidatorException xsde)
	{
	    logger.error(xsde.getMessage());
	    transResult.setXsdValidateError(xsde.getMessage());
	} catch (XBRLValidatorException xbrle)
	{
	    logger.error(xbrle.getMessage());
	    transResult.setXbrlValidateError(xbrle.getMessage());
	} catch (Throwable e)
	{
	    logger.error(e.getMessage());
	    transResult.setGeneralError(e.getMessage());
	}
	return transResult;
    }

    /**
     * Operaci�n de servicio web encargada de realizar la transformaci�n de formato com�n a una instancia XBRL.
     * 
     * @param inputDocument
     * array de bytes con el documento de entrada
     * 
     * @param validate
     * valor booleano que indicar� si se debe validar el documento XBRL de salida.
     * 
     * @return
     * Devuelve un objeto TransformResult con el resultado de la transformaci�n.
     * 
     * @see TransformResult 
     * 
     */  
    @WebMethod(operationName = "transformToXbrl")
    public TransformResult transformToXbrl(@WebParam(name = "inputDocument") final byte[] inputDocument, @WebParam(name = "validate") final boolean validate)
    {
	
	TransformResult transResult = new TransformResult();
	try
	{
	    byte[] xbrlResult = PGCTransformer.createInstance().transformToXbrl(inputDocument, validate);
	    transResult.setArrayResult(xbrlResult);

	} catch (XSDValidatorException xsde)
	{
	    logger.error(xsde.getMessage());
	    transResult.setXsdValidateError(xsde.getMessage());
	} catch (XBRLValidatorException xbrle)
	{
	    logger.error(xbrle.getMessage());
	    transResult.setXbrlValidateError(xbrle.getMessage());
	} catch (Throwable e)
	{
	    logger.error(e.getMessage());
	    transResult.setGeneralError(e.getMessage());
	} 
	return transResult;
    }

}
