package pt.tecnico.mydrive.system;
import static org.junit.Assert.*;

import org.apache.ojb.jdo.jdoql.Import;
import org.junit.Test;

import pt.tecnico.mydrive.presentation.ChangeWorkingDirectory;
import pt.tecnico.mydrive.presentation.Execute;
import pt.tecnico.mydrive.presentation.Login;
import pt.tecnico.mydrive.presentation.Shell;
import pt.tecnico.mydrive.presentation.Write;
import pt.tecnico.mydrive.service.AbstractServiceTest;
import pt.tecnico.mydrive.presentation.*;

public class SystemTest extends AbstractServiceTest{

 	private MdShell sh;

 	protected void populate(){
 		sh = new MdShell();
 	}


 	@Test
 	public void success(){

 		//vai ser importado um xml para preencher a base de dados 
 		//new Import(sh).execute(new String[] {"other.xml"});
 		//verificar se a primeira diretoria é mesmo home+username
 		new ChangeWorkingDirectory(sh).execute(new String[] {"/home/claudiaamorim/project"} );
 		//teste sem password
 		new Login(sh).execute(new String[] {"claudiaamorim"} );
 		//teste com password
 		new Login(sh).execute(new String[] {"claudiaamorim", "nhanha"} );
 		//existindo um README no project, acrescentando conteudo
 		new Write(sh).execute(new String[] {"/homeclaudiaamorim/project/README", "project working"} );
 		//com argumentos
 		new Execute(sh).execute(new String[] {"/homeclaudiaamorim/project/test"} );
 		//sem argumentos 
 		new Execute(sh).execute(new String[] {"/homeclaudiaamorim/project/sum", "1", "2"} );
 	} 

}