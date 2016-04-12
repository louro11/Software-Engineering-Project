package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;

public class ListDirectoryService extends MyDriveService {

  private long _token;

  public ListDirectoryService(long token){

    _token = token;
  }

  public Long getToken(){
    return _token;
  }

  public void setToken( long token){
      _token = token;
  }

  public final void dispatch() {

      try{

          System.out.println(getMydrive().listDirectory(_token));
      }
      catch (LoginDoesNotExistException e){ System.out.println(e.getMessage());}
				catch (PermitionException e) {System.out.println(e.getMessage());}

  }


}
