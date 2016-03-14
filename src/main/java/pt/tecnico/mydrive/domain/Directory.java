package pt.tecnico.mydrive.domain;

import java.util.*;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.FileNotFoundException;

import java.lang.String;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import org.jdom2.Element;


public class Directory extends Directory_Base {



    public Directory() {
        super();	
    }

    public Directory(String name, int fileid, DateTime timestamp, String permission, User owner, Directory parent )
    {  	
       set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(parent);
	   setSelf(this);
       setFilesystem(owner.getFilesystem());
    }

    public Directory(String name, int fileid, DateTime timestamp, String permission, User owner)
    {  	
       set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(this);
	   setSelf(this);
       setFilesystem(owner.getFilesystem());
    }

   //retorna listagem de todos os ficheiros(ficheiros ou diretorios de um diretorio)
   //o diretorio tem uma estrutura de files do tipo set

   public void createTextFile(String name, String permission, int fileid, DateTime timestamp, User owner, String content ){


   		TextFile tf = new TextFile(name, permission, fileid , timestamp, owner, content);
   		addFiles(tf);

   }


   public void createSubDirectory(String name, User owner, Directory parent){
	    Directory subdirectory = new Directory(name, 3, new DateTime(), owner.get_mask(), owner, parent);
	    parent.addFiles(subdirectory);
   }

   public String printFiles(){
	   String s= "";
       for(File f : getFilesSet()) {
	         s=s + f.get_name()+"\n";
          }
         //s = s.substring(0,s.length()-1);
	     return s;
   }


    public File getFile(String name) throws FileNotFoundException{
        for(File f : getFilesSet()) {
             if(f.get_name().equals(name))
                  return f;
            }

          throw new FileNotFoundException(name);

    }
	
	
	@Override
    public void setOwner(User owner) {
        if (owner == null) {
            super.setOwner(null);
            return;
        }
		super.setOwner(owner);
        //owner.addContact(this);
    }
    
    @Override
    public void setParent(Directory parent) {
        if (parent == null) {
            super.setParent(null);
            return;
        }
		super.setParent(parent);
        //owner.addContact(this);
    }

 

    @Override
    public void remove(){

		if(getFilesSet().size()==0){
			setOwner(null);
			setParent(null);
			deleteDomainObject();}

    }
    
    //@Override
    public boolean isCDiable(){

        return true;}

    //@Override
    public boolean isAppendable(){

        return false;
    }
	
	public Element xmlExport() {
		
        Element dir = new Element("dir");
        dir.setAttribute("id", Integer.toString(get_fileid()));
        
        dir.addContent(super.xmlExport());
                
        Element dirname = new Element(get_name());
        
        for(File file : getFilesSet()){
        	
        	dirname.addContent(file.xmlExport());
        }
        
        dir.addContent(dirname);
        
        return dir;
	}
}
