package pt.tecnico.mydrive.domain;



public class Association extends Association_Base {

    public Association() {
       
        super();
   
    }

    public Association(String name, File file){
      
      setFile(file);  
      set_name(name);
     
    }

    

}
