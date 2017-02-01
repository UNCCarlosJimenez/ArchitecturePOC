/**
 * UpdateVendorResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class UpdateVendorResponse  implements java.io.Serializable {
    private com.unicomer.demo.ws.ResponseHeader responseHeader;

    private com.unicomer.demo.ws.OmcInterfaceVendor vendor;

    public UpdateVendorResponse() {
    }

    public UpdateVendorResponse(
           com.unicomer.demo.ws.ResponseHeader responseHeader,
           com.unicomer.demo.ws.OmcInterfaceVendor vendor) {
           this.responseHeader = responseHeader;
           this.vendor = vendor;
    }


    /**
     * Gets the responseHeader value for this UpdateVendorResponse.
     * 
     * @return responseHeader
     */
    public com.unicomer.demo.ws.ResponseHeader getResponseHeader() {
        return responseHeader;
    }


    /**
     * Sets the responseHeader value for this UpdateVendorResponse.
     * 
     * @param responseHeader
     */
    public void setResponseHeader(com.unicomer.demo.ws.ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }


    /**
     * Gets the vendor value for this UpdateVendorResponse.
     * 
     * @return vendor
     */
    public com.unicomer.demo.ws.OmcInterfaceVendor getVendor() {
        return vendor;
    }


    /**
     * Sets the vendor value for this UpdateVendorResponse.
     * 
     * @param vendor
     */
    public void setVendor(com.unicomer.demo.ws.OmcInterfaceVendor vendor) {
        this.vendor = vendor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateVendorResponse)) return false;
        UpdateVendorResponse other = (UpdateVendorResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responseHeader==null && other.getResponseHeader()==null) || 
             (this.responseHeader!=null &&
              this.responseHeader.equals(other.getResponseHeader()))) &&
            ((this.vendor==null && other.getVendor()==null) || 
             (this.vendor!=null &&
              this.vendor.equals(other.getVendor())));
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
        if (getResponseHeader() != null) {
            _hashCode += getResponseHeader().hashCode();
        }
        if (getVendor() != null) {
            _hashCode += getVendor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateVendorResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">updateVendorResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "ResponseHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "responseHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "Vendor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "omcInterfaceVendor"));
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
