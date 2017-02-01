/**
 * EbsProviderImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class EbsProviderImplServiceLocator extends org.apache.axis.client.Service implements com.unicomer.demo.ws.EbsProviderImplService {

    public EbsProviderImplServiceLocator() {
    }


    public EbsProviderImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EbsProviderImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EbsProviderPort
    private java.lang.String EbsProviderPort_address = "http://uinhsap1wldev.datacenter.milady.local:7102/EbsProviderImpl/EbsProviderImplService";

    public java.lang.String getEbsProviderPortAddress() {
        return EbsProviderPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EbsProviderPortWSDDServiceName = "EbsProviderPort";

    public java.lang.String getEbsProviderPortWSDDServiceName() {
        return EbsProviderPortWSDDServiceName;
    }

    public void setEbsProviderPortWSDDServiceName(java.lang.String name) {
        EbsProviderPortWSDDServiceName = name;
    }

    public com.unicomer.demo.ws.EbsProvider getEbsProviderPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EbsProviderPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEbsProviderPort(endpoint);
    }

    public com.unicomer.demo.ws.EbsProvider getEbsProviderPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.unicomer.demo.ws.EbsProviderPortBindingStub _stub = new com.unicomer.demo.ws.EbsProviderPortBindingStub(portAddress, this);
            _stub.setPortName(getEbsProviderPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEbsProviderPortEndpointAddress(java.lang.String address) {
        EbsProviderPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.unicomer.demo.ws.EbsProvider.class.isAssignableFrom(serviceEndpointInterface)) {
                com.unicomer.demo.ws.EbsProviderPortBindingStub _stub = new com.unicomer.demo.ws.EbsProviderPortBindingStub(new java.net.URL(EbsProviderPort_address), this);
                _stub.setPortName(getEbsProviderPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EbsProviderPort".equals(inputPortName)) {
            return getEbsProviderPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "EbsProviderImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "EbsProviderPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EbsProviderPort".equals(portName)) {
            setEbsProviderPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
