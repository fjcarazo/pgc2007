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


package es.inteco.xbrl.pgc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.xerces.util.XMLCatalogResolver;



/**
 *
 *
 * Clase de configuraci�n utilizada por la API para determinar la localizaci�n de los recursos
 * y otro tipo de propiedades las cuales deben ser configuradas mediante un archivo de configuraci�n.
 * <br /><br />
 * El archivo de configuraci�n est� ubicado en el directorio config y se llama pgc.properties.  Este
 * archivo contiene una serie de propiedades necesarias para el funcionamiento del sistema en general.
 * <br /><br />
 * <ul>
 * <li>
 * taxonomyRoot: identifica el directorio ra�z de la taxonom�a.
 * </li>
 * <li>
 * basePath: directorio ra�z en el cual ha sido instalada la API.  En este directorio se encontrar�n todos
 *	los recursos necesarios, es decir, los archivos .cmd para l�nea de comando as� como los directorios
 * 	config, lib, properties, resources con sus contenidos.
 * </li>
 * <li>
 * tempDirectory: directorio temporal, necesario para guardar ficheros de modo temporal, necesario para 
 * 	los procesos realizados por el sistema.
 * </li>
 * <li>
 * schemaLocationIOInterface: schemaLocation de los XML de entrada en formato com�n, necesario para poder
 * 	resolver la localizaci�n del esquema con el cual ser� validado.
 * </li>
 * </ul>
 * 
 * Esta clase, adem�s de proporcionar acceso a las propiedades previametne mencionadas y que se encuentran
 * en el archivo de configuraci�n pgc.properties, provee otras propiedades compuestas a partir de 
 * �stas.  Dichas propiedades son comentadas en sus respectivos m�todos de acceso a las mismas.
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


public class XbrlApiConfiguration
{

    private static final Logger logger = Logger.getLogger(XbrlApiConfiguration.class);
    
    private static final String PROPERTY_FILE = "pgc.properties";
    private static final String XSL_TRANSFORMATION_EXECUTABLE_BAT_FILE = "runXslTransformer.bat";
    
    private String taxonomyRoot = null;
    
    private String m_coreRoot = null;
    private String mapsPath = null;
    private String globalPath = null;
    private String taxonomyConfigurationFile = null;
    private String tempDirectory = null;
    private String xslTransformerPath = null;
    private String schemaLocationIOInterface = null;
    private XMLCatalogResolver resolver =null;
    private String pgcErrorsCatalogPath = null;
    private String xslTransformationLauncherFilename = null;
  
    
    private static XbrlApiConfiguration config = null;
    
    /*Constructor*/
    private XbrlApiConfiguration() 
    {
	super();
    }
    
    
    /**
     * Este m�todo se encarga de crear una �nica instancia inicalizando la misma con las 
     * propiedades que ser�n publicadas.
     * 
     */
    private static void initialize()
    {
	config = new XbrlApiConfiguration();
	Properties pgcProperties = new Properties();
	try
	{
	    // Carga el fichero de configuraci�n con las propiedades
	    pgcProperties.load(new FileInputStream(new File(ClassLoader.getSystemResource(PROPERTY_FILE).toURI())));

	    config.setTaxonomyRoot(prepareTaxonomyRoot(pgcProperties.getProperty("taxonomyRoot")));
	    config.setBasePath(pgcProperties.getProperty("basePath"));
	    config.setTempDirectory(pgcProperties.getProperty("tempDirectory"));
	    config.setSchemaLocationIOInterface(pgcProperties.getProperty("schemaLocationIOInterface"));
	    config.setXslTransformationLauncherFileName(pgcProperties.getProperty("xslTransformLauncher"));


	} catch (FileNotFoundException e)
	{
	    logger.fatal(e);

	} catch (IOException e)
	{
	    logger.fatal(e);

	} catch (URISyntaxException e)
	{
	    logger.fatal(e);
	}
    }    
    
    
    
    static
    {
	initialize();
    }
    



 

    
    

    /**
     * Devuelve una instancia de configuraci�n, si no ha sido creada a�n la crea para
     * de modo est�tico de modo que las sucesivas veces que se acceda se obtenga la 
     * misma.
     * 
     * @return
     * referencia a la configuraci�n
     * 
     */
    public static final XbrlApiConfiguration getInstance()
    {

	return config;

    }

    /**
     * Devuelve el path ra�z en el cual se encuentra la taxonom�a.
     * 
     * @return
     * path 
     */
    public final String getTaxonomyRoot()
    {
	return taxonomyRoot;
    }
    
    
    /**
     * Devuelve el directorio en el cual se encuentran los mapas de configuraci�n
     * de PGC2007.
     * 
     * @return
     * path de mapas.
     * 
     */
    public final String getMapsPath()
    {

	return mapsPath;
    }

    
    /**
     * Devuelve el directorio en el cual se encuentran los recursos necesarios para
     * poder invocar al transformador XSL 2.0.
     * 
     * @return
     * path de transformaci�n XSL
     * 
     */
    public final String getXslTransformerPath()
    {
	return xslTransformerPath;
    }

    
    /**
     * Devuelve el path absoluto a la localizaci�n del archivo de configuraci�n global.xml.
     * 
     * @return
     * path del archivo global.xml
     * 
     */
    public final String getGlobalPath()
    {
	return globalPath;
    }

    private final void setBasePath(String m_basePath)
    {
	String coreRootRelative = m_basePath;
	
	Class<? extends XbrlApiConfiguration> c = this.getClass();
	URL u = c.getProtectionDomain().getCodeSource().getLocation();		
	String fileUri = "";
	//String strPathSeparator = System.getProperty("file.separator"); //TODO: revisar la l�gica del m�todo para usar este srt en lugar de \\ en las rutas
	
	try
	{
		fileUri =  URLDecoder.decode(u.getFile(), "UTF-8") ;
	}
	catch(UnsupportedEncodingException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	  
	File f = new File(fileUri); 
	String coreRootBeforeChanges = f.getParent();
	if (coreRootRelative != null)
	{
	    coreRootBeforeChanges = coreRootBeforeChanges + coreRootRelative;
	}

	if (!coreRootBeforeChanges.endsWith("\\"))
	{
	    coreRootBeforeChanges += "\\";
	}
	
	m_coreRoot = PGCUtils.replace(coreRootBeforeChanges, "\\", "/");
	m_coreRoot = PGCUtils.replace(m_coreRoot, "//", "/");
	
	File coreRootPath = new File(m_coreRoot + "");
	if (!coreRootPath.exists()) 
	{
		coreRootBeforeChanges = f.getAbsolutePath();
		if (coreRootRelative != null) 
		{
			coreRootBeforeChanges = coreRootBeforeChanges + coreRootRelative;
		}
		if (!coreRootBeforeChanges.endsWith("\\")) 
		{
			coreRootBeforeChanges += "\\";
		}
		m_coreRoot = PGCUtils.replace(coreRootBeforeChanges, "\\", "/");
		m_coreRoot = PGCUtils.replace(m_coreRoot, "//", "/");
	}
	
	mapsPath = PGCUtils.getFullPath(m_coreRoot + "\\resources\\PGCMaps\\");
    if (!mapsPath.endsWith("\\")) 
	{
		mapsPath += "\\";
	}
	mapsPath = PGCUtils.replace(mapsPath, "\\", "/");
	mapsPath = PGCUtils.replace(mapsPath, "//", "/");
	xslTransformerPath = PGCUtils.getFullPath(m_coreRoot + "\\resources\\XSLTransformer\\");
    if (!xslTransformerPath.endsWith("\\")) 
	{
    	xslTransformerPath += "\\";
	}
	xslTransformerPath = PGCUtils.replace(xslTransformerPath, "\\", "/");
	xslTransformerPath = PGCUtils.replace(xslTransformerPath, "//", "/");	

	globalPath = PGCUtils.getFullPath(m_coreRoot + "\\config\\global.xml");
	globalPath = PGCUtils.replace(globalPath, "\\", "/");
	globalPath = PGCUtils.replace(globalPath, "//", "/");

	taxonomyConfigurationFile = PGCUtils.getFullPath(m_coreRoot + "\\config\\TaxonomyConfiguration.xml");
	taxonomyConfigurationFile = PGCUtils.replace(taxonomyConfigurationFile, "\\", "/");
	taxonomyConfigurationFile = PGCUtils.replace(taxonomyConfigurationFile, "//", "/");
	
	
	String catalogFile = PGCUtils.getFullPath(m_coreRoot + "\\config\\catalog.xml");
	catalogFile = PGCUtils.replace(catalogFile, "\\", "/");
	catalogFile = PGCUtils.replace(catalogFile, "//", "/");	
	
	resolver = getCatalog(catalogFile);
	
	pgcErrorsCatalogPath = PGCUtils.getFullPath(m_coreRoot + "\\config\\pgc07-errors.xml");
	pgcErrorsCatalogPath = PGCUtils.replace(pgcErrorsCatalogPath, "\\", "/");
	pgcErrorsCatalogPath = PGCUtils.replace(pgcErrorsCatalogPath, "//", "/");
	
	logger.debug("working with this Configuration values: " + 
				"\n mapsPath= " + mapsPath + 
				"\n xslTransformerPath: " + xslTransformerPath + 
				"\n globalPath: " + globalPath + 
				"\n taxonomyConfigurationFile: " + taxonomyConfigurationFile + 
				"\n pgcErrorsCatalogPath: " + pgcErrorsCatalogPath
				);
	
    }
    
    private XMLCatalogResolver getCatalog(String catalogFile)
    {
	if (resolver == null) 
	{
	    resolver = new XMLCatalogResolver(new String[]{catalogFile});
	}

	return resolver;
    }

    private final void setTaxonomyRoot(String taxonomyRoot)
    {
        this.taxonomyRoot = taxonomyRoot;
    }


    /**
     * Obtiene el path en el que se encuentra el fichero de configuraci�n que resuelve
     * los paths externos de la taxonom�a.
     * 
     * @return
     * path.
     */
    public final String getTaxonomyConfigurationFile()
    {
	return taxonomyConfigurationFile;
    }

    /**
     * Devuelve el path absoluto al directorio temporal de trabajo.
     * 
     * @return
     * path directorio temporal
     */
    public final String getTempDirectory()
    {
        return tempDirectory;
    }

    public final void setTempDirectory(String tempDirectory)
    {
        this.tempDirectory = tempDirectory;
    }

    
    public final void setXslTransformationLauncherFileName(String launcherFilename)
    {
    	this.xslTransformationLauncherFilename = launcherFilename;
    }

    public final String getXslTransformationLauncherFileName()
    {
    	return xslTransformationLauncherFilename;
    }

    public final String getXslTransformationExecutableBatFileName()
    {
    	return XSL_TRANSFORMATION_EXECUTABLE_BAT_FILE;
    }

    public final String getSchemaLocationIOInterface()
    {
        return schemaLocationIOInterface;
    }

    public final void setSchemaLocationIOInterface(String schemaLocationIOInterface)
    {
        this.schemaLocationIOInterface = schemaLocationIOInterface;
    }

    public final XMLCatalogResolver getResolver()
    {
        return resolver;
    }


    public final String getPgcErrorsCatalogPath()
    {
        return pgcErrorsCatalogPath;
    }
    
    
    /**
     * Convertimos la ruta para la taxonomia que se indica en pgc.properties
     * en una ruta absoluta para que nuestra aplicaci�n pueda funcionar correctamente 
     * @param originalProperty
     * ruta de taxonomia leida de pgc.properties
     * @return
     * ruta de taxonomia en path absoluto
     */
    private static String prepareTaxonomyRoot(String originalProperty){
    	String returnValue = originalProperty;
    	
    	try{
    		logger.debug("taxonomy root from properties file= " + originalProperty);
    		String newValue=originalProperty;
    	
    		File file = new File(newValue);
    		file = file.getAbsoluteFile();  
   		
    		newValue= file.getAbsoluteFile().getAbsolutePath();
    	   
    		newValue= newValue.replace('\\', '/');
    	    if  ( !(newValue.endsWith("/"))){
    	    	newValue += "/";
    	    }
    	    
    	    returnValue=newValue;
    	}
    	catch(Exception e){
    		//registro el error pero no paro la aplicaci�n. Intentar� ejecutar con el par�metro original
    		logger.error(e.getMessage());
    	}
    	
    	//logger.debug("working with this taxonomy root: " + returnValue);
    	return returnValue;
    	
    }
}
