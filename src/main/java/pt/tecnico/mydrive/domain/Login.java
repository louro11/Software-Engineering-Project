package pt.tecnico.mydrive.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;

import org.joda.time.DateTime;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.InvalidPasswordLengthException;


public class Login extends Login_Base{

  public Login(User usr, String password) throws WrongPasswordException{

  

      checkPassword(usr, password);

      this.setUser(usr);

      this.setCurrentdirectory(usr.getHomedirectory());

      Random rand = new Random();

      long token = rand.nextLong();

      this.set_token( token );

      DateTime now = new DateTime();

      //now passa a ser a data de criação do login

      this.set_timeout(now);

      this.set_token(token);
    


  }

  private boolean checkPassword( User user, String password ) throws WrongPasswordException{

    if(user.get_password().equals(password)){

      if(password.length() >= 8){

        return true;

      }

      else{
        throw new InvalidPasswordLengthException();
      }

    }

      else {throw new WrongPasswordException();

    }
  }

  protected void resetTimer(){

    DateTime now = new DateTime();

    DateTime limit = now.plusHours(2);

    this.set_timeout(limit);

  }
  
  
  
  public List<EnvironmentVar> listVariables(){

    
        List<EnvironmentVar> varArray = new ArrayList<EnvironmentVar>();

        for(EnvironmentVar var : this.getVarsSet()) {
          
          
           varArray.add(new EnvironmentVar(var.get_name(), var.get_value()));
         
        }

	   
        return varArray;

   }
  


  //as permissoes do login devem ser feitas por override dos getters e setters?


}
