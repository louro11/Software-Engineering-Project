package pt.tecnico.mydrive.domain;


import java.util.*;
import org.joda.time.DateTime;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;
import pt.tecnico.mydrive.exceptions.InvalidPasswordLengthException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;

public class Login extends Login_Base{

  public Login(User usr, String password) throws WrongPasswordException{

    try{

      checkPassword(usr, password);

      this.setUser(usr);

      this.setCurrentdirectory(usr.getHomedirectory());

      Random rand = new Random();

      long token = rand.nextLong();

      this.set_token( token );

      DateTime now = new DateTime();

      DateTime limit = now.plusHours(2);

      this.set_timeout(limit);

      this.set_token(token);
    }

    catch (WrongPasswordException e){ throw e;}


  }

  //@Override
  //public set

  private boolean checkPassword( User user, String password ) throws InvalidPasswordLengthException, WrongPasswordException{

    if(( user.get_password() ).equals( password )){

      if( password.length() >= 8){

              return true;
      }

      else{

        throw new InvalidPasswordLengthException();
      }


    }

    else{

      throw new WrongPasswordException();

    }
  }

  protected void resetTimer(){

    DateTime now = new DateTime();

    DateTime limit = now.plusHours(2);

    this.set_timeout(limit);

  }


  //as permissoes do login devem ser feitas por override dos getters e setters?


}
