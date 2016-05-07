package pt.tecnico.mydrive.domain;


import java.util.*;
import org.joda.time.DateTime;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;

public class Login extends Login_Base{

  public Login(User usr, String password) throws WrongPasswordException{

    if( checkPassword(usr, password) ){

      this.setUser(usr);

      this.setCurrentdirectory(usr.getHomedirectory());

      Random rand = new Random();

      long token = rand.nextLong();

      DateTime now = new DateTime();

      DateTime limit = now.plusHours(2);

      this.set_timeout(limit);
    }

    else{

      throw new WrongPasswordException();

    }


  }

  //@Override
  //public set

  private boolean checkPassword( User user, String password ){

    if(( user.get_password() ).equals( password )){

      return true;

    }

    else{

      return false;

    }
  }

  protected void resetTimer(){

    DateTime now = new DateTime();

    DateTime limit = now.plusHours(2);

    this.set_timeout(limit);

  }


  //as permissoes do login devem ser feitas por override dos getters e setters?


}
