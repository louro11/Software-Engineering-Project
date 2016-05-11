/*package pt.tecnico.mydrive.system;
import static org.junit.Assert.*;
import org.junit.Test;

import pt.tecnico.mydrive.service.AbstractServiceTest;
import pt.tecnico.mydrive.service.presentation.*;

public class SystemTest extends AbstractServiceTest{

 	private pbShell sh;

 	protected void populate(){
 		sh=new pbShell();
 	}

/* 
 	@Test
 	public void sucess(){

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
 	} */

 //}