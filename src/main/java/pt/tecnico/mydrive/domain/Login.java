package pt.tecnico.mydrive.domain;

import java.util.*;
import org.joda.time.DateTime;

public class Login extends Login_Base{

  public Login(User user){

    this.setUser(user);

    this.setCurrentdirectory(user.getHomedirectory());

    this.set_valid(true);

    Random rand = new Random();

    long token = rand.nextLong();

    this.set_token(token);

    this.set_timeout(0);
  }
}
