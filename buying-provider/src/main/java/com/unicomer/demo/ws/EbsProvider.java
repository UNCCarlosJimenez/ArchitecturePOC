/**
 * EbsProvider.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicomer.demo.ws;

public interface EbsProvider extends java.rmi.Remote {
    public com.unicomer.demo.ws.GetVendorResponse getVendor(com.unicomer.demo.ws.GetVendorRequest payload) throws java.rmi.RemoteException;
    public com.unicomer.demo.ws.AddVendorResponse addVendor(com.unicomer.demo.ws.AddVendorRequestType0 payload) throws java.rmi.RemoteException;
    public com.unicomer.demo.ws.UpdateVendorResponse updateVendor(com.unicomer.demo.ws.UpdateVendorRequest payload) throws java.rmi.RemoteException;
    public com.unicomer.demo.ws.DeleteVendorResponse deleteVendor(com.unicomer.demo.ws.DeleteVendorRequest payload) throws java.rmi.RemoteException;
}
