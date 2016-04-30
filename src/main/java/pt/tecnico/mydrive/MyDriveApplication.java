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
import pt.tecnico.mydrive.domain.FileSystem;
import pt.tecnico.mydrive.domain.Link;
import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.SuperUser;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.User;

public class MyDriveApplication{


	static final Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
	//
	//
	// 	System.out.println("*** Welcome to the MyDrive application! ***");
	//
	// 	try {
	//
	// 	init();
	// 	//setup();
	//
	//
	// 	//for (String s: args) scanXml(new File(s));
	//
	// 	//print();
	// 	printXml();
	//
	// 	}finally { FenixFramework.shutdown(); }
	//
	// }
  //   //close issue
	//
	//
	//
	//
  //   @Atomic
  //   public static void init() { // empty mydrive
	//
  //       log.trace("Init: " + FenixFramework.getDomainRoot());
	// 	MyDrive.getInstance();
	// 	/** MyDrive.getInstance().cleanup();    deviamos pensar em fazer isto, pode ser importante  **/
	//
  //   }
	//
	//
  //   @Atomic
  //   public static void setup() { // mydrive with debug data
	//
	//
  //       //log.trace("Setup: " + FenixFramework.getDomainRoot());
	// 	//MyDrive md = MyDrive.getInstance();
    	//FileSystem fs = md.getFileSystem();
	//
	// 	//Directory maindirectory= md.getCurrentdirectory();
	//
  //       //ponto 1
	//
	// 	//md.changeCurrentDirectory("/home"); another arguments ---> token
	// 	//md.createTextFile("README", "lista de utilizadores");
	//
	//
	//
	// ////closed exceptions issue
	//
	// 	//ponto2
	// 	//md.createDirectory("/usr/local/bin");
	//
	//
	// 	//ponto 3
	// 	//String content = md.readfile("/home/README");
	// 	//System.out.println(content);
	//
	//
	// 	//ponto 4
	// 	//md.removeFile("/usr/local/bin");
	//
	// 	//closed issue remove()
	// 	//ponto 6
	// 	//md.removeFile("/home/README");
	//
	//
	// 	//ponto 7
	// 	//closed issue
	// 	//String files = md.listDirectory("/home");
	// 	//String cont = md.readfile("/home/badjoraz");
	// 	//System.out.println(cont);
	// 	//System.out.println(files);
	//
	//
  //   }
	//
  //   //ponto 5????
  //   @Atomic
  //   public static void printXml() {
	//
	//
  //       log.trace("xmlPrint: " + FenixFramework.getDomainRoot());
	// 	Document doc = MyDrive.getInstance().xmlExport();
	// 	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	//
	// 	try {
	// 		xmlOutput.output(doc, new PrintStream(System.out));
	//
	// 	} catch (IOException e) { System.out.println(e); }
  //   }
	//
  //   @Atomic
  //   public static void scanXml(File file) {
	//
	//
	// 	log.trace("xmlScan: " + FenixFramework.getDomainRoot());
	// 	MyDrive md = MyDrive.getInstance();
	// 	SAXBuilder builder = new SAXBuilder();
	//
	//
	// 	try {
	// 	    Document document = (Document)builder.build(file);
	// 	    md.xmlImport(document.getRootElement());
	// 	}catch (JDOMException | IOException e) {
	// 	    e.printStackTrace();
	// 	}
    }


}
