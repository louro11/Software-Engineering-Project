package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.service.dto.FileDto;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;
import pt.tecnico.mydrive.domain.File;



import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ListDirectoryService extends MyDriveService {

  private long _token;
  private  Directory _dir;
  private List<FileDto> _files;

  public ListDirectoryService(long token){

    _token = token;
    Login log = getMydrive().getLoginbyToken(token);
    _dir = log.getCurrentdirectory();
  }

  public ListDirectoryService(long tokenL, String path){
      _token = tokenL;

      String[] token = path.split("/");

  		Directory aux = getMydrive().getFilesystem().getMaindir();

  		for(int i=1; i<token.length-1; i++){

  			for (File file: aux.getFilesSet()){

  				if (file.get_name().equals(token[i])){

  					aux = (Directory) file;
          }
        }
      }

      _dir = aux;

  }

  public Long getToken(){
    return _token;
  }

  public void setToken( long token){
      _token = token;
  }

  public final void dispatch() {

      try{
             _files = getMydrive().listDirectory(_token, _dir);

             Collections.sort(_files);
      }
      catch (LoginDoesNotExistException e){ System.out.println(e.getMessage());}
				catch (PermitionException e) {System.out.println(e.getMessage());}

  }

  public final List<FileDto> result() {
        return _files;
    }


}
