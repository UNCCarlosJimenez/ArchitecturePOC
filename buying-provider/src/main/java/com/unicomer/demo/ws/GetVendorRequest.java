/**
 * GetVendorRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class GetVendorRequest  implements java.io.Serializable {
    private com.unicomer.demo.ws.RequestHeader requestHeader;

    private com.unicomer.demo.ws.GetVendorRequestVendorType3 vendor;

    public GetVendorRequest() {
    }

    public GetVendorRequest(
           com.unicomer.demo.ws.RequestHeader requestHeader,
           com.unicomer.demo.ws.GetVendorRequestVendorType3 vendor) {
           this.requestHeader = requestHeader;
           this.vendor = vendor;
    }


    /**
     * Gets the requestHeader value for this GetVendorRequest.
     * 
     * @return requestHeader
     */
    public com.unicomer.demo.ws.RequestHeader getRequestHeader() {
        return requestHeader;
    }


    /**
     * Sets the requestHeader value for this GetVendorRequest.
     * 
     * @param requestHeader
     */
    public void setRequestHeader(com.unicomer.demo.ws.RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }


    /**
     * Gets the vendor value for this GetVendorRequest.
     * 
     * @return vendor
     */
    public com.unicomer.demo.ws.GetVendorRequestVendorType3 getVendor() {
        return vendor;
    }


    /**
     * Sets the vendor value for this GetVendorRequest.
     * 
     * @param vendor
     */
    public void setVendor(com.unicomer.demo.ws.GetVendorRequestVendorType3 vendor) {
        this.vendor = vendor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetVendorRequest)) return false;
        GetVendorRequest other = (GetVendorRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestHeader==null && other.getRequestHeader()==null) || 
             (this.requestHeader!=null &&
              this.requestHeader.equals(other.getRequestHeader()))) &&
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
        if (getRequestHeader() != null) {
            _hashCode += getRequestHeader().hashCode();
        }
        if (getVendor() != null) {
            _hashCode += getVendor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetVendorRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">GetVendorRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "RequestHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "requestHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "Vendor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">>GetVendorRequest>Vendor"));
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
