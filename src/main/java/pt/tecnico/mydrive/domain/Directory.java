package pt.tecnico.mydrive.domain;

public class Directory extends Directory_Base {
    

    //nao sei se o super tem efeito
    public Directory() {
        super();
    }

   //retorna listagem de todos os ficheiros(ficheiros ou diretorios de um diretorio)
   //o diretorio tem uma estrutura de files do tipo set
   public String PrintFiles(){
	String s= "";
	//existe o metodo getFiles() --> retorna o set do diretorio
	Set<Files> _files = this.getFiles()
	for(Files f : _files) {
		s=s + f.getName()+"\n";
	}
	s=s.substring(0,s.lenght()-1);
	return s;
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
