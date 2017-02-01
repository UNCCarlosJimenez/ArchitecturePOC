package com.unicomer.demo.ws;

public class EbsProviderProxy implements com.unicomer.demo.ws.EbsProvider {
  private String _endpoint = null;
  private com.unicomer.demo.ws.EbsProvider ebsProvider = null;
  
  public EbsProviderProxy() {
    _initEbsProviderProxy();
  }
  
  public EbsProviderProxy(String endpoint) {
    _endpoint = endpoint;
    _initEbsProviderProxy();
  }
  
  private void _initEbsProviderProxy() {
    try {
      ebsProvider = (new com.unicomer.demo.ws.EbsProviderImplServiceLocator()).getEbsProviderPort();
      if (ebsProvider != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ebsProvider)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ebsProvider)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ebsProvider != null)
      ((javax.xml.rpc.Stub)ebsProvider)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.unicomer.demo.ws.EbsProvider getEbsProvider() {
    if (ebsProvider == null)
      _initEbsProviderProxy();
    return ebsProvider;
  }
  
  public com.unicomer.demo.ws.GetVendorResponse getVendor(com.unicomer.demo.ws.GetVendorRequest payload) throws java.rmi.RemoteException{
    if (ebsProvider == null)
      _initEbsProviderProxy();
    return ebsProvider.getVendor(payload);
  }
  
  public com.unicomer.demo.ws.AddVendorResponse addVendor(com.unicomer.demo.ws.AddVendorRequestType0 payload) throws java.rmi.RemoteException{
    if (ebsProvider == null)
      _initEbsProviderProxy();
    return ebsProvider.addVendor(payload);
  }
  
  public com.unicomer.demo.ws.UpdateVendorResponse updateVendor(com.unicomer.demo.ws.UpdateVendorRequest payload) throws java.rmi.RemoteException{
    if (ebsProvider == null)
      _initEbsProviderProxy();
    return ebsProvider.updateVendor(payload);
  }
  
  public com.unicomer.demo.ws.DeleteVendorResponse deleteVendor(com.unicomer.demo.ws.DeleteVendorRequest payload) throws java.rmi.RemoteException{
    if (ebsProvider == null)
      _initEbsProviderProxy();
    return ebsProvider.deleteVendor(payload);
  }
  
  
}