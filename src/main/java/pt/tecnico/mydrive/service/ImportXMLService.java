package pt.tecnico.mydrive.service;

import org.jdom2.Element;


public class ImportXMLService extends MyDriveService {
	
	private Element _el;
	
	public ImportXMLService(Element el){
		
		_el = el;

	}
	
	public Element getElement(){
		
		return _el;
	}
		
	
	public void setElement(Element el){
		
		_el = el;
	}
		
	
	
	public final void dispatch(){


		getMydrive().xmlImport(this.getElement());

	}
}
