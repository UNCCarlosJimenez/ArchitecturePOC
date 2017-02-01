/**
 * ResponseHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class ResponseHeader  implements java.io.Serializable {
    private java.lang.Integer responseCode;

    private java.lang.String responseDescription;

    private java.lang.Integer serviceCode;

    private java.lang.String serviceDescription;

    private java.lang.String transaction;

    public ResponseHeader() {
    }

    public ResponseHeader(
           java.lang.Integer responseCode,
           java.lang.String responseDescription,
           java.lang.Integer serviceCode,
           java.lang.String serviceDescription,
           java.lang.String transaction) {
           this.responseCode = responseCode;
           this.responseDescription = responseDescription;
           this.serviceCode = serviceCode;
           this.serviceDescription = serviceDescription;
           this.transaction = transaction;
    }


    /**
     * Gets the responseCode value for this ResponseHeader.
     * 
     * @return responseCode
     */
    public java.lang.Integer getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this ResponseHeader.
     * 
     * @param responseCode
     */
    public void setResponseCode(java.lang.Integer responseCode) {
        this.responseCode = responseCode;
    }


    /**
     * Gets the responseDescription value for this ResponseHeader.
     * 
     * @return responseDescription
     */
    public java.lang.String getResponseDescription() {
        return responseDescription;
    }


    /**
     * Sets the responseDescription value for this ResponseHeader.
     * 
     * @param responseDescription
     */
    public void setResponseDescription(java.lang.String responseDescription) {
        this.responseDescription = responseDescription;
    }


    /**
     * Gets the serviceCode value for this ResponseHeader.
     * 
     * @return serviceCode
     */
    public java.lang.Integer getServiceCode() {
        return serviceCode;
    }


    /**
     * Sets the serviceCode value for this ResponseHeader.
     * 
     * @param serviceCode
     */
    public void setServiceCode(java.lang.Integer serviceCode) {
        this.serviceCode = serviceCode;
    }


    /**
     * Gets the serviceDescription value for this ResponseHeader.
     * 
     * @return serviceDescription
     */
    public java.lang.String getServiceDescription() {
        return serviceDescription;
    }


    /**
     * Sets the serviceDescription value for this ResponseHeader.
     * 
     * @param serviceDescription
     */
    public void setServiceDescription(java.lang.String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }


    /**
     * Gets the transaction value for this ResponseHeader.
     * 
     * @return transaction
     */
    public java.lang.String getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this ResponseHeader.
     * 
     * @param transaction
     */
    public void setTransaction(java.lang.String transaction) {
        this.transaction = transaction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResponseHeader)) return false;
        ResponseHeader other = (ResponseHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responseCode==null && other.getResponseCode()==null) || 
             (this.responseCode!=null &&
              this.responseCode.equals(other.getResponseCode()))) &&
            ((this.responseDescription==null && other.getResponseDescription()==null) || 
             (this.responseDescription!=null &&
              this.responseDescription.equals(other.getResponseDescription()))) &&
            ((this.serviceCode==null && other.getServiceCode()==null) || 
             (this.serviceCode!=null &&
              this.serviceCode.equals(other.getServiceCode()))) &&
            ((this.serviceDescription==null && other.getServiceDescription()==null) || 
             (this.serviceDescription!=null &&
              this.serviceDescription.equals(other.getServiceDescription()))) &&
            ((this.transaction==null && other.getTransaction()==null) || 
             (this.transaction!=null &&
              this.transaction.equals(other.getTransaction())));
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
        if (getResponseCode() != null) {
            _hashCode += getResponseCode().hashCode();
        }
        if (getResponseDescription() != null) {
            _hashCode += getResponseDescription().hashCode();
        }
        if (getServiceCode() != null) {
            _hashCode += getServiceCode().hashCode();
        }
        if (getServiceDescription() != null) {
            _hashCode += getServiceDescription().hashCode();
        }
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "responseHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "responseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "responseDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "serviceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "serviceDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "transaction"));
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
