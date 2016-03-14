package pt.tecnico.mydrive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.mydrive.domain.Application;
import pt.tecnico.mydrive.domain.Directory;
//import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.FileSystem;
import pt.tecnico.mydrive.domain.Link;
import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.SuperUser;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.User;

public class MyDriveApplication{
	static final Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {
		
		setup();

		/*
		for (String s: args) scanXml(new File(s));
		print();
		
		
		printXml();
		*/
		}finally { FenixFramework.shutdown(); }

	}


    @Atomic
    public static void setup() { // mydrive with debug data
        log.trace("Setup: " + FenixFramework.getDomainRoot());
		MyDrive md = MyDrive.getInstance();

		Directory maindirectory= md.getCurrentdirectory();
		System.out.println("Antes do ponto 1");

        //ponto 1

		md.changeCurrentDirectory("/home");
		md.createTextFile("README", "lista de utilizadores"); 

		System.out.println("Acabei ponto 1");
        //


		//ponto2
		md.createDirectory("/usr/local/bin");
		System.out.println("Acabei ponto 2");
		//




		//ponto 3
		String content = md.readfile("/home/README"); 
		System.out.println(content);
		System.out.println("Acabei ponto 3");
		//


		//ponto 4
		md.removeFile("/usr/local/bin"); 
		System.out.println("Acabei ponto 4");
		//

		//ponto 6
		md.removeFile("/home/README"); 
		System.out.println("Acabei ponto 6");
		//

		//ponto 7
		String files = md.printFiles("/home"); 
		System.out.println(files);

		System.out.println("Acabei ponto 7");
		//

    }
/*
    //ponto 5????
    @Atomic
    public static void printXml() {
        	//log.trace("xmlPrint: " + FenixFramework.getDomainRoot());
		Document doc = MyDrive.getInstance().xmlExport();
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try { 
			xmlOutput.output(doc, new PrintStream(System.out));
		} catch (IOException e) { System.out.println(e); }*/
    }

}
