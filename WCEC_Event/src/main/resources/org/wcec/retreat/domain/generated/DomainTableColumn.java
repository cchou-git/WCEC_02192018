//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.06 at 10:18:46 AM EST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="tableColumnName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="headerColumnName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "DomainTableColumn")
public class DomainTableColumn {

    @XmlAttribute(name = "tableColumnName", required = true)
    protected String tableColumnName;
    @XmlAttribute(name = "headerColumnName", required = true)
    protected String headerColumnName;

    /**
     * Gets the value of the tableColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableColumnName() {
        return tableColumnName;
    }

    /**
     * Sets the value of the tableColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableColumnName(String value) {
        this.tableColumnName = value;
    }

    /**
     * Gets the value of the headerColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderColumnName() {
        return headerColumnName;
    }

    /**
     * Sets the value of the headerColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderColumnName(String value) {
        this.headerColumnName = value;
    }

}