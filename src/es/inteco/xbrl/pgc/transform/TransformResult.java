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



/**
 *
 *
 * Clase que encapsula el resultado de la transformaci�n.  Es usada para devolver la respuesta
 * del servicio de transformaci�n.
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
 * @see PGCWSTransformator
 */


public class TransformResult
{

    
    /**
     * Cadena de errores, en caso de que los hubiera, en la validaci�n de esquema previa a la transformaci�n.
     */
    private String xsdValidateError = null;
    
        
    /**
     * Cadena de errores, en caso de que los hubiera, en la validaci�n XBRL previa a la generaci�n.
     */
    private String xbrlValidateError = null;
    
    
    /**
     * Cadena de errores, en caso de que los hubiera, producidos en el proceso de generaci�n. 
     */
    private String generalError = null;
    
    
    /**
     * Array de bytes con el resultado de la generaci�n en caso de ser correcta. Si se produce alg�n error
     * este array de bytes ser� nulo.
     * 
     */
    private byte[] arrayResult = null;
    

    /**
     * Constructor por defecto 
     */
    public TransformResult() {
    	super();
    }



    /**
     * Devuelve un error de validaci�n XSD
     * @return
     * Error de validaci�n
     */
    public final String getXsdValidateError()
    {
        return xsdValidateError;
    }



    /**
     * Establece un error de validaci�n XSD
     * @param xsdValidateError
     * error XSD
     */
    public final void setXsdValidateError(String xsdValidateError)
    {
        this.xsdValidateError = xsdValidateError;
    }



    /**
     * Devuelve un error de validaci�n XBRL
     * @return
     * error de validaci�n XBRL
     */
    public final String getXbrlValidateError()
    {
        return xbrlValidateError;
    }



    /**
     * Establece un error de validaci�n XBRL
     * @param xbrlValidateError
     * Error de validaci�n XBRL
     */
    public final void setXbrlValidateError(String xbrlValidateError)
    {
        this.xbrlValidateError = xbrlValidateError;
    }



    /**
     * Devuelve un error de tipo generico
     * @return
     * error de tipo generico
     */
    public final String getGeneralError()
    {
        return generalError;
    }



    /**
     * Establece un error de tipo gen�rico
     * @param generalError
     * error de tipo gen�rico
     */
    public final void setGeneralError(String generalError)
    {
        this.generalError = generalError;
    }



    /**
     * Devuelve los resultados en un array de bytes
     * @return
     * array de bytes resultado
     */
    public final byte[] getArrayResult()
    {
        return arrayResult;
    }



    /**
     * Establece los resultados de la transformaci�n en un array de bytes
     * @param arrayResult
     * array de bytes resultado
     */
    public final void setArrayResult(byte[] arrayResult)
    {
        this.arrayResult = arrayResult;
    }

}
