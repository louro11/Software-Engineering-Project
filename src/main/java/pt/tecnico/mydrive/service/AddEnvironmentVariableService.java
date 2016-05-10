package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.domain.EnvironmentVar;

import java.util.List;

public class AddEnvironmentVariableService extends MyDriveService {

	  private long _token;
	  private String _name, _value;
	  private List<EnvironmentVar> _vars;

	  public AddEnvironmentVariableService(long token, String name, String value){
		  
		  if(value != null && !value.isEmpty()){
			  
			this.setToken(token);
			this.setName(name);
			this.setValue(value);
			
		  }
	  }

	  public long getToken(){

		return _token;

	  }

	  public void setToken(long token){

		  _token = token;
	  }

	  public String getName(){

		return _name;

	  }

	  public void setName(String name){

		  _name = name;

	  }

	  public String getValue(){

		return _value;

	  }

	  public void setValue(String value){

		  _value = value;

	  }

	  public final void dispatch() throws LoginDoesNotExistException, PermitionException {

		 _vars = getMydrive().addEnvironmentvar(this.getToken(), this.getName(), this.getValue());
	  
	  }

	  public final List<EnvironmentVar> result() {

		return _vars;

	  }

}
