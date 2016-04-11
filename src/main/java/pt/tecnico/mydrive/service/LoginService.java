package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoginIsInvalidException;

public class LoginService extends MyDriveService {

  private String username;

  private String password;

  public LoginService(String username, String password){

    this.setUsername(username);

    this.setPassword(password);
  }

  public void setUsername(String usrname){

    this.username = usrname;

  }

  public void setPassword(String passwrd){

    this.password = passwrd;

  }

  public String getUsername(){

    return this.username;

  }

  public String getPassword(){

    return this.password;

  }

  public final void dispatch() throws LoginDoesNotExistException, LoginIsInvalidException  {

     getMydrive().loginUser(username, password);

  }
}
