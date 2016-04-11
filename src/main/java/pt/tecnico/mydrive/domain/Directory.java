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

      //criacao de ponteiros auxiliares com o nome . e .. para pai e ele proprio (resulta???)
      Directory fatheraux = parent;
      fatheraux.set_name("..");

      Directory me = this;
      me.set_name(".");

     set_name(name); set_permission(permission); set_fileid(fileid);set_timestamp(timestamp);setOwner(owner);
	   setParent(fatheraux); setSelf(me);
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


   public void createSubDirectory(String name, int fileid, User owner, Directory parent){
	  
	  Directory subdirectory = new Directory(name, fileid, new DateTime(), owner.get_mask(), owner, parent);
	    
      parent.addFiles(subdirectory);
   }

   public String printFiles(){
	   String s= "";
       for(File f : getFilesSet()) {
	         s=s + f.get_name()+"\n";
          }
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
    public void setUser(User user) {
        if (user == null) {
            super.setUser(null);
            return;
        }
    super.setUser(user);
    
    }

  @Override
    public void setMydrive(MyDrive md) {
        if (md == null) {
            super.setMydrive(null);
            return;
        }
    super.setMydrive(md);
    
    }

  @Override
    public void setFilesystem(FileSystem fs) {
        if (fs == null) {
            super.setFilesystem(null);
            return;
        }
    super.setFilesystem(fs);
    
    }

  @Override
    public void setParent(Directory parent) {
        if (parent == null) {
            super.setParent(null);
            return;
        }
    super.setParent(parent);
    
    }

  @Override
    public void setDir(Directory dir) {
        if (dir == null) {
            super.setDir(null);
            return;
        }
    super.setDir(dir);
    
    }

   @Override
    public void setSelf(Directory self) {
        if (self == null) {
            super.setSelf(null);
            return;
        }
    super.setSelf(self);
    
    }

    @Override
    public void setDirctory(Directory dir) {
        if (dir == null) {
            super.setDirctory(null);
            return;
        }
    super.setDirctory(dir);
    
    }

	
	@Override
    public void setOwner(User owner) {
        if (owner == null) {
            super.setOwner(null);
            return;
        }
		super.setOwner(owner);
    
    }

    @Override
    public void setDirectory(Directory dir) {
        if (dir == null) {
            super.setDirectory(null);
            return;
        }
    super.setDirectory(dir);
    
    }
    

    @Override
    public void remove(){

		if(getFilesSet().size()==0){
			
      setUser(null);
      setMydrive(null);
      setFilesystem(null);
      setParent(null);
      setDir(null);
      setSelf(null);
      setDirctory(null);
      setOwner(null);
      setDirectory(null);

			deleteDomainObject();}

    }
    

    public boolean isCDiable(){

        return true;}

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
