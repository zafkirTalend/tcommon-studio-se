package org.talend.repository.registeruser.proxy;

public class RegisterUserPortTypeProxy implements org.talend.repository.registeruser.proxy.RegisterUserPortType {
  private String _endpoint = null;
  private org.talend.repository.registeruser.proxy.RegisterUserPortType registerUserPortType = null;
  
  public RegisterUserPortTypeProxy() {
    _initRegisterUserPortTypeProxy();
  }
  
  public RegisterUserPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegisterUserPortTypeProxy();
  }
  
  private void _initRegisterUserPortTypeProxy() {
    try {
      registerUserPortType = (new org.talend.repository.registeruser.proxy.RegisterUserLocator()).getRegisterUserPort();
      if (registerUserPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registerUserPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registerUserPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registerUserPortType != null)
      ((javax.xml.rpc.Stub)registerUserPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.talend.repository.registeruser.proxy.RegisterUserPortType getRegisterUserPortType() {
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType;
  }
  
  public boolean registerUser(java.lang.String email, java.lang.String country, java.lang.String designerversion) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.registerUser(email, country, designerversion);
  }
  
  public boolean registerUserWithProductName(java.lang.String email, java.lang.String country, java.lang.String designerversion, java.lang.String productname) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.registerUserWithProductName(email, country, designerversion, productname);
  }
  
  public boolean registerUserWithAllUserInformations(java.lang.String email, java.lang.String country, java.lang.String designerversion, java.lang.String productname, java.lang.String projectLanguage, java.lang.String osName, java.lang.String osVersion, java.lang.String javaVersion, java.lang.String totalMemory, java.lang.String memRAM, java.lang.String nbProc) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.registerUserWithAllUserInformations(email, country, designerversion, productname, projectLanguage, osName, osVersion, javaVersion, totalMemory, memRAM, nbProc);
  }
  
  public java.math.BigInteger registerUserWithAllUserInformationsAndReturnId(java.lang.String email, java.lang.String country, java.lang.String designerversion, java.lang.String productname, java.lang.String projectLanguage, java.lang.String osName, java.lang.String osVersion, java.lang.String javaVersion, java.lang.String totalMemory, java.lang.String memRAM, java.lang.String nbProc) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.registerUserWithAllUserInformationsAndReturnId(email, country, designerversion, productname, projectLanguage, osName, osVersion, javaVersion, totalMemory, memRAM, nbProc);
  }
  
  public org.talend.repository.registeruser.proxy.UserRegistration[] listUsers() throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.listUsers();
  }
  
  public java.lang.String checkUser(java.lang.String email) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.checkUser(email);
  }
  
  public java.math.BigInteger createUser(java.lang.String email, java.lang.String pseudo, java.lang.String password, java.lang.String firstname, java.lang.String lastname, java.lang.String country, java.lang.String designerversion, java.lang.String productname, java.lang.String osName, java.lang.String osVersion, java.lang.String javaVersion, java.lang.String totalMemory, java.lang.String memRAM, java.lang.String nbProc) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.createUser(email, pseudo, password, firstname, lastname, country, designerversion, productname, osName, osVersion, javaVersion, totalMemory, memRAM, nbProc);
  }
  
  public java.math.BigInteger updateUser(java.lang.String email, java.lang.String pseudo, java.lang.String passwordOld, java.lang.String passwordNew, java.lang.String firstname, java.lang.String lastname, java.lang.String country, java.lang.String designerversion, java.lang.String productname, java.lang.String osName, java.lang.String osVersion, java.lang.String javaVersion, java.lang.String totalMemory, java.lang.String memRAM, java.lang.String nbProc) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.updateUser(email, pseudo, passwordOld, passwordNew, firstname, lastname, country, designerversion, productname, osName, osVersion, javaVersion, totalMemory, memRAM, nbProc);
  }
  
  public java.math.BigInteger createUser50(java.lang.String pseudo, java.lang.String password, java.lang.String firstname, java.lang.String lastname, java.lang.String country, java.lang.String designerversion, java.lang.String productname, java.lang.String osName, java.lang.String osVersion, java.lang.String javaVersion, java.lang.String totalMemory, java.lang.String memRAM, java.lang.String nbProc) throws java.rmi.RemoteException{
    if (registerUserPortType == null)
      _initRegisterUserPortTypeProxy();
    return registerUserPortType.createUser50(pseudo, password, firstname, lastname, country, designerversion, productname, osName, osVersion, javaVersion, totalMemory, memRAM, nbProc);
  }
  
  
}