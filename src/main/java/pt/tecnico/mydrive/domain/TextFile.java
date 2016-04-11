package pt.tecnico.mydrive.domain;

import org.joda.time.DateTime;

import java.util.Scanner;
import java.io.IOException;
import org.jdom2.Element;

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


	public boolean isCDiable(){

        return false;}

  
    public boolean isAppendable(){

        return true;}

   
    public String readfile(){

	
		    return get_content();


    }

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
    /*
    @Override
    public void xmlImport(Element fileElement){

      set_name(new String(fileElement.getChildren("name").getAttribute("name").getValue().getBytes("UTF-8")));
    }
	*/
}


