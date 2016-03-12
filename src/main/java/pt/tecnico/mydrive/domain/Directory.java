package pt.tecnico.mydrive.domain;

public class Directory extends Directory_Base {
    
    public Directory() {
        super();
    }
    
    @Override
    public void remove(){
		
		
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
