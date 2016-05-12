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


package es.inteco.xbrl.pgc.apifacade;


/**
 *
 * Interfaz que debe cumplir una clase que pretende encapsular un objeto XBRL del tipo
 * Unit
 *
 * <br><br>
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


public interface IXbrlUnit
{

    /**
     * Devuelve el key de un unit
     * @return
     * key del unit
     */
    public abstract String getKey();

    /**
     * Establece el key de un unit
     * @param key
     * key del unit
     */
    public abstract void setKey(String key);

    /**
     * Devuelve el code de un unit
     * @return
     * code del unit
     */
    public abstract String getCode();

    /**
     * Establece el code de un unit
     * @param code
     * code del unit
     */
    public abstract void setCode(String code);

    /**
     * Devuelve el prefijo de un unit
     * @return
     * prefijo del unit
     */
    public abstract String getPrefix();

    /**
     * Establece el prefijo de un unit
     * @param prefix
     * prefijo de unit
     */
    public abstract void setPrefix(String prefix);

    /**
     * Devuelve el measure de un unit
     * @return
     * measure del unit
     */
    public abstract Object getMeasure();

    /**
     * Establece el measure de un unit
     * @param measure
     * mesaure del unit
     */
    public abstract void setMeasure(Object measure);

    /**
     * Devuelve el unit como object
     * @return
     * unit como object
     */
    public abstract Object getUnitObject();

    /**
     * Establece el unit a partir de un object
     * @param unitObject
     * unit como object
     */
    public abstract void setUnitObject(Object unitObject);

}
