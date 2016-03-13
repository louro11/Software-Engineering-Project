package pt.tecnico.mydrive.domain;

public class SuperUser extends SuperUser_Base {

    public SuperUser() {
        super();
    }

    public SuperUser(String username, String password, String name, String mask){
      set_username(username);
      set_password(password);
      set_name(name);
      set_mask(mask);
    }

}
