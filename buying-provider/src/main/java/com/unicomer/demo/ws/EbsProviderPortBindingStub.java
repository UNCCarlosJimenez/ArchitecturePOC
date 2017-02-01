/**
 * EbsProviderPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public class EbsProviderPortBindingStub extends org.apache.axis.client.Stub implements com.unicomer.demo.ws.EbsProvider {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVendor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "GetVendorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">GetVendorRequest"), com.unicomer.demo.ws.GetVendorRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">getVendorResponse"));
        oper.setReturnClass(com.unicomer.demo.ws.GetVendorResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "getVendorResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addVendor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "AddVendorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">AddVendorRequest"), com.unicomer.demo.ws.AddVendorRequestType0.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">addVendorResponse"));
        oper.setReturnClass(com.unicomer.demo.ws.AddVendorResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "addVendorResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateVendor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "UpdateVendorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">UpdateVendorRequest"), com.unicomer.demo.ws.UpdateVendorRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">updateVendorResponse"));
        oper.setReturnClass(com.unicomer.demo.ws.UpdateVendorResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "updateVendorResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteVendor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "DeleteVendorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">DeleteVendorRequest"), com.unicomer.demo.ws.DeleteVendorRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">deleteVendorResponse"));
        oper.setReturnClass(com.unicomer.demo.ws.DeleteVendorResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "deleteVendorResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

    }

    public EbsProviderPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public EbsProviderPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public EbsProviderPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">>GetVendorRequest>Vendor");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.GetVendorRequestVendorType3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">AddVendorRequest");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.AddVendorRequestType0.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">addVendorResponse");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.AddVendorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">DeleteVendorRequest");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.DeleteVendorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">deleteVendorResponse");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.DeleteVendorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">GetVendorRequest");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.GetVendorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">getVendorResponse");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.GetVendorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">UpdateVendorRequest");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.UpdateVendorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", ">updateVendorResponse");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.UpdateVendorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "omcInterfaceVendor");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.OmcInterfaceVendor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "requestHeader");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.RequestHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.demo.unicomer.com/", "responseHeader");
            cachedSerQNames.add(qName);
            cls = com.unicomer.demo.ws.ResponseHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.unicomer.demo.ws.GetVendorResponse getVendor(com.unicomer.demo.ws.GetVendorRequest payload) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getVendor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {payload});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.unicomer.demo.ws.GetVendorResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.unicomer.demo.ws.GetVendorResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.unicomer.demo.ws.GetVendorResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.unicomer.demo.ws.AddVendorResponse addVendor(com.unicomer.demo.ws.AddVendorRequestType0 payload) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "addVendor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {payload});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.unicomer.demo.ws.AddVendorResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.unicomer.demo.ws.AddVendorResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.unicomer.demo.ws.AddVendorResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.unicomer.demo.ws.UpdateVendorResponse updateVendor(com.unicomer.demo.ws.UpdateVendorRequest payload) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateVendor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {payload});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.unicomer.demo.ws.UpdateVendorResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.unicomer.demo.ws.UpdateVendorResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.unicomer.demo.ws.UpdateVendorResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.unicomer.demo.ws.DeleteVendorResponse deleteVendor(com.unicomer.demo.ws.DeleteVendorRequest payload) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteVendor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {payload});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.unicomer.demo.ws.DeleteVendorResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.unicomer.demo.ws.DeleteVendorResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.unicomer.demo.ws.DeleteVendorResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
