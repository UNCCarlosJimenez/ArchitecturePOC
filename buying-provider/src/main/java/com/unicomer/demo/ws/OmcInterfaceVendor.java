/**
 * OmcInterfaceVendor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class OmcInterfaceVendor  implements java.io.Serializable {
    private int id;

    private java.lang.String lookupCode;

    private java.lang.String name;

    private java.lang.String segment;

    private java.lang.String transactionType;

    public OmcInterfaceVendor() {
    }

    public OmcInterfaceVendor(
           int id,
           java.lang.String lookupCode,
           java.lang.String name,
           java.lang.String segment,
           java.lang.String transactionType) {
           this.id = id;
           this.lookupCode = lookupCode;
           this.name = name;
           this.segment = segment;
           this.transactionType = transactionType;
    }


    /**
     * Gets the id value for this OmcInterfaceVendor.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this OmcInterfaceVendor.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the lookupCode value for this OmcInterfaceVendor.
     * 
     * @return lookupCode
     */
    public java.lang.String getLookupCode() {
        return lookupCode;
    }


    /**
     * Sets the lookupCode value for this OmcInterfaceVendor.
     * 
     * @param lookupCode
     */
    public void setLookupCode(java.lang.String lookupCode) {
        this.lookupCode = lookupCode;
    }


    /**
     * Gets the name value for this OmcInterfaceVendor.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this OmcInterfaceVendor.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the segment value for this OmcInterfaceVendor.
     * 
     * @return segment
     */
    public java.lang.String getSegment() {
        return segment;
    }


    /**
     * Sets the segment value for this OmcInterfaceVendor.
     * 
     * @param segment
     */
    public void setSegment(java.lang.String segment) {
        this.segment = segment;
    }


    /**
     * Gets the transactionType value for this OmcInterfaceVendor.
     * 
     * @return transactionType
     */
    public java.lang.String getTransactionType() {
        return transactionType;
    }


    /**
     * Sets the transactionType value for this OmcInterfaceVendor.
     * 
     * @param transactionType
     */
    public void setTransactionType(java.lang.String transactionType) {
        this.transactionType = transactionType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OmcInterfaceVendor)) return false;
        OmcInterfaceVendor other = (OmcInterfaceVendor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.lookupCode==null && other.getLookupCode()==null) || 
             (this.lookupCode!=null &&
              this.lookupCode.equals(other.getLookupCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.segment==null && other.getSegment()==null) || 
             (this.segment!=null &&
              this.segment.equals(other.getSegment()))) &&
            ((this.transactionType==null && other.getTransactionType()==null) || 
             (this.transactionType!=null &&
              this.transactionType.equals(other.getTransactionType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getId();
        if (getLookupCode() != null) {
            _hashCode += getLookupCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSegment() != null) {
            _hashCode += getSegment().hashCode();
        }
        if (getTransactionType() != null) {
            _hashCode += getTransactionType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OmcInterfaceVendor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "omcInterfaceVendor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lookupCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "lookupCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "segment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "transactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
