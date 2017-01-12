/**
 * 
 */
package com.unicomer.demo.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.unicomer.demo.message.GetVendorRequest;
import com.unicomer.demo.message.GetVendorResponse;

/**
 * @author oracle
 *
 */
@WebService(name = "EbsProvider", targetNamespace = "http://ws.demo.unicomer.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ com.unicomer.demo.message.ObjectFactory.class })
public interface EbsProvider {
	@WebMethod(operationName = "GetVendor", action = "process")
	@WebResult(name = "GetVendorResponse", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload")
	public GetVendorResponse getVendor(
			@WebParam(name = "GetVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") GetVendorRequest paramGetVendorRequest);
	
}
