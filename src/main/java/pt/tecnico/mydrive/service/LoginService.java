package pt.tecnico.mydrive.service;

import java.util.*;

import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;


public class LoginService extends MyDriveService {

  private String username;

  private String password;

  private long token;

  //caso em que faz login sem password 

  public LoginService(String username){

    this.setUsername(username);

    this.setPassword(username);

    Random rand = new Random();

    token = rand.nextLong();

    this.setToken(token);
  }

  //caso em que faz login com password

  public LoginService(String username, String password){

    this.setUsername(username);

    this.setPassword(password);

    Random rand = new Random();

    token = rand.nextLong();

    this.setToken(token);
  }


  public void setUsername(String usrname){

    this.username = usrname;

  }

  public void setPassword(String passwrd){

    this.password = passwrd;

  }

  public void setToken(long tken){

    this.token = tken;

  }

  public String getUsername(){

    return this.username;

  }

  public String getPassword(){

    return this.password;

  }


  public long getToken(){

    return token;

  }

  public final void dispatch() throws UserDoesNotExistException, WrongPasswordException {

    getMydrive().loginUser(username, password);

  }

  public final long result(){

    return getToken();

  }
  
}
