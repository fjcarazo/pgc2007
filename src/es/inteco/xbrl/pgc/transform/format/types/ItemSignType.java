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


/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3rc1</a>, using an XML
 * Schema.
 * $Id: ItemSignType.java,v 1.1 2011/11/30 13:50:32 pablo Exp $
 */

package es.inteco.xbrl.pgc.transform.format.types;

/**
*
* This class was automatically generated with 
* <a href="http://www.castor.org">Castor 1.3rc1</a>, using an XML
* Schema.
* $Id: ItemSignType.java,v 1.1 2011/11/30 13:50:32 pablo Exp $
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
@SuppressWarnings("serial")
public enum ItemSignType implements java.io.Serializable {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant VALUE_0
     */
    VALUE_0("+"),
    /**
     * Constant VALUE_1
     */
    VALUE_1("-");

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field value.
     */
    private final java.lang.String value;


      //----------------/
     //- Constructors -/
    //----------------/

    private ItemSignType(final java.lang.String value) {
        this.value = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static es.inteco.xbrl.pgc.transform.format.types.ItemSignType fromValue(
            final java.lang.String value) {
        for (ItemSignType c: ItemSignType.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(
            final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString(
    ) {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value(
    ) {
        return this.value;
    }

}
