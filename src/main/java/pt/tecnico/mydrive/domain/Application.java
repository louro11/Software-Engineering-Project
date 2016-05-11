package pt.tecnico.mydrive.domain;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.mydrive.exceptions.ApplicationDoesntHasAssocException;

public class Application extends Application_Base {
    
    public Application() {
        super();
    }

    public Application(String name, String permission, Integer fileid, DateTime timestamp, User owner, String content) {
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        set_content(content);   
   } 

   @Override
   public void setAssociation(Association assoc) throws ApplicationDoesntHasAssocException{
    throw new ApplicationDoesntHasAssocException();
   }

   @Override
   public Association getAssociation() throws ApplicationDoesntHasAssocException{
    throw new ApplicationDoesntHasAssocException();
   }

    public Element xmlExport() {
		
        Element app = new Element("app");
        app.setAttribute("id", Integer.toString(get_fileid()));
       
        app.addContent(super.xmlExport());
        
        return app;
    }
    
    @Override
    public void runApp(String []args)throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
    										IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		 Method method;
		 Class<?> cls;
		 String splitcontent[]=get_content().split("\\.");
		 String [] auxclassname = Arrays.copyOf(splitcontent, splitcontent.length-1);
		 String classname ="";
		 for(String str: auxclassname)
			 classname=classname+str;
		 try{
			 cls = Class.forName(classname);
			 Collections.reverse(Arrays.asList(splitcontent));
			 method = cls.getMethod(splitcontent[0], String[].class);	 
		 }catch (ClassNotFoundException e) {
			 throw e;
			 //TODO other stuff??
		 }
		 method.invoke(null, (Object)args);
	 }
    
     @Override
     public boolean isApp(){
    	 return true;
     }
    	
}
