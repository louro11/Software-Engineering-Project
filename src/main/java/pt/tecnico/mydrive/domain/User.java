package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exceptions.InvalidUserNameException;

public class User extends User_Base {

    public User(){
      super();
    }

    public User(String username, String password, String name, String mask) throws InvalidUserNameException {
        set_password(password);
        set_name(name);
        set_mask(mask);
        if( username.isEmpty() || username == null){
          throw new InvalidUserNameException("username is empty");
        }
        else{
             char c;
             for (int i = 0; i < username.length(); i++) {
                    c = username.charAt(i);
                    if(Character.isLetterOrDigit(c)){
                      set_username(username);
                    }
                    else{
                      throw new InvalidUserNameException("username contains wrong Character");
                    }

              }
        }
      }
}
