package pt.tecnico.mydrive.domain;

import java.util.*;
import org.joda.time.DateTime;
import java.lang.String;


public class Directory extends Directory_Base {



    public Directory() {
        super();	
    }

    public Directory(String name, Integer fileid, DateTime timestamp, String permission, User owner, Directory parent )
    {  	
       set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(parent);
	   setSelf(this);
    }

   //retorna listagem de todos os ficheiros(ficheiros ou diretorios de um diretorio)
   //o diretorio tem uma estrutura de files do tipo set

   public String PrintFiles(){
	     String s= "";
	  
	       for(File f : getFiles()) {
		         s=s + f.get_name()+"\n";
	          }
         //s = s.substring(0,s.length()-1);
	     return s;
   }


    @Override
    public void remove(){
		
		if(getFilesCount()!=0)

        /*  sera necessario cortar todas as ligações do objecto? */
			deleteDomainObject();

    }

    //@Override
    public boolean isCDiable(){

        return true;}

    //@Override
    public boolean isAppendable(){

        return false;}
}
