package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.service.dto.FileDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ListDirectoryService extends MyDriveService {

  private long _token;
  private List<FileDto> _files;

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
             _files = getMydrive().listDirectory(_token);

             Collections.sort(_files);
      }
      catch (LoginDoesNotExistException e){ System.out.println(e.getMessage());}
				catch (PermitionException e) {System.out.println(e.getMessage());}

  }

  public final List<FileDto> result() {
        return _files;
    }


}
