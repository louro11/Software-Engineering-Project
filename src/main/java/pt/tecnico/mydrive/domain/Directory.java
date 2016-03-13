package pt.tecnico.mydrive.domain;

import java.util.*;
import org.joda.time.DateTime;
import java.lang.String;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


public class Directory extends Directory_Base {



    public Directory() {
        super();	
    }

    public Directory(String name, int fileid, DateTime timestamp, String permission, User owner, Directory parent )
    {  	
       set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(parent);
	   setSelf(this);
    }

    public Directory(String name, int fileid, DateTime timestamp, String permission, User owner)
    {  	
       set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(this);
	   setSelf(this);
    }

   //retorna listagem de todos os ficheiros(ficheiros ou diretorios de um diretorio)
   //o diretorio tem uma estrutura de files do tipo set

   public void createSubDirectory(String name, User owner, Directory parent){
    Directory subdirectory = new Directory(name, 3, new DateTime(), owner.get_mask(), owner, parent);
    parent.addFiles(subdirectory);
   }

   public String PrintFiles(){
	     String s= "";
	  
	       for(File f : getFiles()) {
		         s=s + f.get_name()+"\n";
	          }
         //s = s.substring(0,s.length()-1);
	     return s;
   }


    public File getFile(String name) throws FileNotFoundException{
        for(File f : getFiles()) {
             if(f.get_name().equals(name))
                  return f;
            }

          throw new FileNotFoundException(name);

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
	
	
	public Element xmlExport() {
        Element element = new Element("directory");
        element.setAttribute("name", getName());
        element.setAttribute("owner", getOwner().getName());
        element.setAttribute("perm", getPermission());
        ///element.setAttribute("path", get());
       // element.setAttribute("date", DateTime.toString(getTimestamp()));
        element.setAttribute("id", Integer.toString(getFileid())); 
        
        
        for(File file : getFilesSet()){
        	
        	element.addContent(file.xmlExport());
        }
        
        return element;
        
    }

}
