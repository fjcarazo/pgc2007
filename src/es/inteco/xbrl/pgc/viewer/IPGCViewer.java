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


package es.inteco.xbrl.pgc.viewer;


import java.util.ArrayList;

import es.inteco.xbrl.pgc.errors.exceptions.XBRLPGCException;


/**
 *
 *
 * Interfaz que implementa un visualizador de PGC.
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


public interface IPGCViewer
{

    /**
     * Visualiza el m�dulo indicado de un informe XBRL dado, dicho informe  se encuentra en la ruta del sistema
     * indicada en el par�metro inputPath.
     * 
     * Para proporcionar la visualizaci�n genera un documento con formato html, devolvi�ndolo en la
     * ruta del sistema indicada por el par�metro outputPath.
     * 
     * @param inputPath
     * ruta en la cual se encuentra el informe XBRL a visualizar
     * 
     * @param module
     * m�dulo a visualizar
     * 
     * @param outputPath
     * ruta en la cual se dejar� el resultado
     * 
     * @throws XBRLPGCException
     */
    public abstract void view(String inputPath, String module, String outputPath) throws XBRLPGCException;

    
    
    /**
     * Visualiza el m�dulo indicado de un informe XBRL, dado mediante un array de bytes.
     * 
     * Para proporcionar la visualizaci�n genera un documento con formato html devolvi�ndolo
     * en un array de bytes.
     *  
     * @param inputDocument
     * array de bytes con el informe XBRL
     * 
     * @param module
     * identificador del m�dulo a visualizar
     * 
     * @return
     * array de bytes con el documento html generado
     * 
     * @throws XBRLPGCException
     */
    public abstract byte[] view(byte[] inputDocument, String module) throws  XBRLPGCException;
    
    

    /**
     * Visualiza el m�dulo indicado de un informe en formato com�n (XML) dado, dicho informe  
     * se encuentra en la ruta del sistema indicada en el par�metro inputPath.
     * 
     * Para proporcionar la visualizaci�n genera un documento con formato html, devolvi�ndolo en la
     * ruta del sistema indicada por el par�metro outputPath.
     * 
     * @param inputPath
     * ruta en la cual se encuentra el informe a visualizar
     * 
     * @param module
     * m�dulo a visualizar
     * 
     * @param outputPath
     * ruta en la cual se dejar� el resultado
     * 
     * @throws XBRLPGCException
     */
    public abstract void viewXML (String inputPath, String module, String outputPath) throws XBRLPGCException;

    
    /**
     * Visualiza el m�dulo indicado de un informe en formato com�n (XML), dado mediante un array de bytes.
     * 
     * Para proporcionar la visualizaci�n genera un documento con formato html devolvi�ndolo
     * en un array de bytes.
     *  
     * @param inputDocument
     * array de bytes con el informe
     * 
     * @param module
     * identificador del m�dulo a visualizar
     * 
     * @return
     * array de bytes con el documento html generado
     * 
     * @throws XBRLPGCException
     */
    public abstract byte[] viewXML (byte[] inputDocument, String module) throws  XBRLPGCException;
    
    


    
    /**
     * Obtiene la lista destinada a almacenar warnings o errores que llegan al logger durante el proceso
     * @return
     * ArrayList de String
     */
    public ArrayList<String> getLoggerTraceList();
    
    
    
    
    
	/**
	 * Establece la lista destinada a almacenar warnings o errores que llegan al logger durante el proceso
	 * @param loggerList
	 * ArrayList de String
	 */
	public void setLoggerTraceList(ArrayList<String> loggerList) ;

}
