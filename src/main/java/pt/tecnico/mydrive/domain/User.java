package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exceptions.InvalidUserNameException;

public class User extends User_Base {

    public User(){
      super();
    }

    public User(String name) throws InvalidUserNameException {
        super();

        if( name.isEmpty() || name == null){
          throw new InvalidUserNameException("username is empty");
        }
        else{
             char c;
             for (int i = 0; i < name.length(); i++) {
                    c = name.charAt(i);
                    if(Character.isLetterOrDigit(c)){
                    //  setname(name);
                    }
                    else{
                      throw new InvalidUserNameException("username contains wrong Character");
                    }

              }
        }
      }
}
