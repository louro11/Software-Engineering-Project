package pt.tecnico.mydrive.domain;

public class TextFile extends TextFile_Base {
    
    public TextFile() {
        super();
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
		
    
}
