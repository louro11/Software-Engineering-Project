package pt.tecnico.mydrive.domain;

import java.util.*;
import org.joda.time.DateTime;

public class Login extends Login_Base{

  public Login(User user){

    this.setUser(user);

    this.setCurrentdirectory(user.getHomedirectory());

    Random rand = new Random();

    long token = rand.nextLong();

    DateTime now = new DateTime();

    DateTime limit = now.plusHours(2);

    this.set_timeout(limit);
  }

  public void resetTimer(){

    DateTime now = new DateTime();

    DateTime limit = now.plusHours(2);

    this.set_timeout(limit);

  }

}
