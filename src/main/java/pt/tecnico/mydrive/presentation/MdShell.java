package pt.tecnico.mydrive.presentation;

public class MdShell extends Shell {

  public static void main(String[] args) throws Exception {
    MdShell sh = new MdShell();
    sh.execute();
  }

  public MdShell() { // mininos, adicionar os vossos comandos aqui
    
    super("MyDrive");
    new Login(this);
    new Write(this);
    //new Execute(this);
    
  }
}
