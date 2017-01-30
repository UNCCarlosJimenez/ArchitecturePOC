/**
 * 
 */
package com.unicomer.demo.ws;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.unicomer.demo.message.AddVendorRequest;
import com.unicomer.demo.message.AddVendorResponse;
import com.unicomer.demo.message.DeleteVendorRequest;
import com.unicomer.demo.message.DeleteVendorResponse;
import com.unicomer.demo.message.GetVendorRequest;
import com.unicomer.demo.message.GetVendorResponse;
import com.unicomer.demo.message.UpdateVendorRequest;
import com.unicomer.demo.message.UpdateVendorResponse;

/**
 * @author oracle
 *
 */
@WebService(name = "EbsProvider", targetNamespace = "http://ws.demo.unicomer.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@XmlSeeAlso({ com.unicomer.demo.message.ObjectFactory.class })
@Local
public interface EbsProvider {
	@WebMethod(operationName = "GetVendor", action = "process")
	@WebResult(name = "GetVendorResponse", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload")
	public GetVendorResponse getVendor(
			@WebParam(name = "GetVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") GetVendorRequest paramGetVendorRequest);
	
	
	@WebMethod(operationName = "AddVendor", action = "process")
	@WebResult(name = "AddVendorResponse", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload")
	public AddVendorResponse addVendor(
			@WebParam(name = "AddVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") AddVendorRequest paramAddVendorRequest);
	
	
	@WebMethod(operationName = "UpdateVendor", action = "process")
	@WebResult(name = "UpdateVendorResponse", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload")
	public UpdateVendorResponse updateVendor(
			@WebParam(name = "UpdateVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") UpdateVendorRequest paramUpdateVendorRequest);
	
	
	@WebMethod(operationName = "DeleteVendor", action = "process")
	@WebResult(name = "DeleteVendorResponse", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload")
	public DeleteVendorResponse deleteVendor(
			@WebParam(name = "AddVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") DeleteVendorRequest paramDeleteVendorRequest);
	
}
