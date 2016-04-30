package pt.tecnico.mydrive.domain;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.CantReadDirectoryException;
import pt.tecnico.mydrive.exceptions.InvalidContentException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;

import pt.tecnico.mydrive.service.dto.FileDto;

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

    @Override
    public String readfile(){

        throw new CantReadDirectoryException(get_name());

    }

    @Override
    public void writefile(String content){

        throw new CantWriteToDirectoryException(get_name());
    }










   public List<FileDto> listDirectory(){

    //falta retornar o conteudo dos Links existentes quando lista a diretoria
        List<FileDto> fileArray = new ArrayList<FileDto>();;

       for(File f : getFilesSet()) {
          if ( f instanceof Link ) { /*TODO:conteudo dos Links*/ }

          else{
            fileArray.add(new FileDto(f.get_name(), f.get_permission(), f.get_timestamp(), f.getOwner()));
          }

	      }

        return fileArray;

   }




    @Override
    public void addFiles(File f){
      if(hasFile(f.get_name()))
        throw new FileAlreadyExistsException(f.get_name());


      super.addFiles(f);
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

    for(File f : getFilesSet()) {
      f.remove();}

    setUser(null);
    setFilesystem(null);
    setParent(null);
    setDir(null);
    setSelf(null);
    setDirctory(null);
    setOwner(null);
    setDirectory(null);

    deleteDomainObject();

    }


    public void createFile(String type , String filename, User user, int fileid, DateTime timestamp,String content ) throws FileAlreadyExistsException, InvalidContentException{

      if(type.equals("application")){

          Application app = new Application(filename, user.get_mask(), fileid, timestamp, user, content);

      try{ addFiles(app);} catch(FileAlreadyExistsException e){throw e;}

      }
      if(type.equals("textfile")){


          TextFile tf = new TextFile(filename, user.get_mask(), fileid , timestamp, user, content);

      try{ addFiles(tf); } catch(FileAlreadyExistsException e){throw e;}

      }
      if(type.equals("link")){

          if (!(content.startsWith("/"))){throw new InvalidContentException(content);}

          Link link = new Link(filename, user.get_mask(), fileid, timestamp, user, content);

       try{ addFiles(link); } catch(FileAlreadyExistsException e){throw e;}
      }
  }



   public void createSubDirectory(String filename, User owner, int fileid, DateTime timestamp){

	    Directory subdirectory = new Directory(filename, fileid, timestamp, owner.get_mask(), owner, this);

      addFiles(subdirectory); // o addFiles tem que ser override?
   }

   public boolean hasFile(String name){
          return getFile(name) != null;

   }



    public File getFile(String name) throws FileNotFoundException{
        File file= null;

        for(File f : getFilesSet()) {
             if(f.get_name().equals(name))
                  file = f;
            }

          throw new FileNotFoundException(name);

    }



   public void cleanup(){

      for (File f: getFilesSet()) f.remove();

   }


    //    nao fazer isto, metodos abstratos e redefiniçao para cada tipo






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
