//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.01.03 a las 03:07:25 PM CST 
//


package com.unicomer.demo.buying.message.ebs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.unicomer.demo.message package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.unicomer.demo.message
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetVendorRequest }
     * 
     */
    public GetVendorRequest createGetVendorRequest() {
        return new GetVendorRequest();
    }

    /**
     * Create an instance of {@link AddVendorRequest }
     * 
     */
    public AddVendorRequest createAddVendorRequest() {
        return new AddVendorRequest();
    }

    /**
     * Create an instance of {@link RequestHeader }
     * 
     */
    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    /**
     * Create an instance of {@link OmcInterfaceVendor }
     * 
     */
    public OmcInterfaceVendor createOmcInterfaceVendor() {
        return new OmcInterfaceVendor();
    }

    /**
     * Create an instance of {@link AddVendorResponse }
     * 
     */
    public AddVendorResponse createAddVendorResponse() {
        return new AddVendorResponse();
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link DeleteVendorRequest }
     * 
     */
    public DeleteVendorRequest createDeleteVendorRequest() {
        return new DeleteVendorRequest();
    }

    /**
     * Create an instance of {@link UpdateVendorResponse }
     * 
     */
    public UpdateVendorResponse createUpdateVendorResponse() {
        return new UpdateVendorResponse();
    }

    /**
     * Create an instance of {@link GetVendorRequest.Vendor }
     * 
     */
    public GetVendorRequest.Vendor createGetVendorRequestVendor() {
        return new GetVendorRequest.Vendor();
    }

    /**
     * Create an instance of {@link UpdateVendorRequest }
     * 
     */
    public UpdateVendorRequest createUpdateVendorRequest() {
        return new UpdateVendorRequest();
    }

    /**
     * Create an instance of {@link GetVendorResponse }
     * 
     */
    public GetVendorResponse createGetVendorResponse() {
        return new GetVendorResponse();
    }

    /**
     * Create an instance of {@link DeleteVendorResponse }
     * 
     */
    public DeleteVendorResponse createDeleteVendorResponse() {
        return new DeleteVendorResponse();
    }

}
