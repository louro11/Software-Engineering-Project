package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exceptions.InvalidUserNameException;

public class User extends User_Base {

    public User(String name) throws InvalidUserNameException {
        super();

        if( name.isEmpty() || name == null){
          throw new InvalidUserNameException();
        }
        else{
             char c;
             for (int i = 0; i < name.length(); i++) {
                    c = name.charAt(i);
                    if(Character.isLetterOrDigit(c)){
                      setname(name);
                    }
                    else{
                      throw new InvalidUserNameException();
                    }

              }
        }
      }
}
