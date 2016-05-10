package pt.tecnico.mydrive.presentation;

public class MdShell extends Shell {

  public static void main(String[] args) throws Exception {
    MdShell sh = new MdShell();
    sh.execute();
  }

  public MdShell() {

    super("MyDrive");

    new Login(this);
    new ChangeWorkingDirectory(this);
    new List(this);
    new Execute(this);
    new Write(this);
    //new Environment(this);
    new Key(this);
  }
}
