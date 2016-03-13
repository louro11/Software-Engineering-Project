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
    public void remove(){

		/*  sera necessario cortar todas as ligações do objecto? */
		deleteDomainObject();

	}


	//@Override
	public boolean isCDiable(){

        return false;}

    //@Override
    public boolean isAppendable(){

        return true;}

    //@Override
    public String readfile(){

	//tenho de verificar permissões??
		return get_content();


    }

    @Override
    public void xmlImport(Element fileElement){




    }
}
