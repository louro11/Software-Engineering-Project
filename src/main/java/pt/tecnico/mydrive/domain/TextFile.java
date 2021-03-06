package pt.tecnico.mydrive.domain;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.jdom2.Element;

import pt.tecnico.mydrive.exceptions.CantReadDirectoryException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
import pt.tecnico.mydrive.exceptions.LoopFoundException;
import pt.tecnico.mydrive.exceptions.RunException;

public class TextFile extends TextFile_Base {

    public TextFile() {
        super();
    }
     public TextFile(String name, String permission, Integer fileid, DateTime timestamp, User owner, String content) {
        set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        set_content(content);
    }



    //falta verificar permissoes aqui?

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


      setDirectory(null);
  		setOwner(null);

  		deleteDomainObject();

    }

 
  
    public boolean isAppendable(){

        return true;}

    @Override
    public String readfile(){

	     
		    return get_content();

    }
    
    @Override
    public void writefile(String content){

         set_content(content);
    }

  

    public Element xmlExport(){
          Element text = new Element("PlainFile");
          text.setAttribute("id", Integer.toString(get_fileid()));

          text.addContent(super.xmlExport());

          Element content = new Element("content");
          content.addContent(get_content());
          text.addContent(content);


          return text;

    }
    @Override
    public boolean isDir(){
    	return false;
    }
 
    
    @Override
    public void run(User user, String[] args)throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
    		IllegalArgumentException, IllegalAccessException, InvocationTargetException, LoopFoundException, RunException{
    
	    String[] lines = this.get_content().split("\n");
		FileSystem fs = user.getFilesystem();
		String[] line;
		for(int i = 0;i<lines.length;i++){
			line = lines[i].split("\\s+");
			File file = fs.getFile(line[0]);
			if (line.length == 1){
				file.run(user, null);
			}
			else{
				file.run(user,Arrays.copyOfRange(line, 1, line.length -1));
			}	
		}
    }
}


