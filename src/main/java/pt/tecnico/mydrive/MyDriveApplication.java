package pt.tecnico.mydrive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
//import java.io.File; usar o nome do import na chamada

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.mydrive.domain.Application;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.File;
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
			/*
		setup(); //TODO
		for (String s: args) scanXml(new File(s));
		print();
		*/
			printXml();
		}finally { FenixFramework.shutdown(); }
    }
    
    @Atomic
    public static void printXml() {
        log.trace("xmlPrint: " + FenixFramework.getDomainRoot());
		Document doc = MyDrive.getInstance().xmlExport();
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try { 
			xmlOutput.output(doc, new PrintStream(System.out));
		} catch (IOException e) { System.out.println(e); }
    }
    
    
}