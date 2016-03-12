package pt.tecnico.mydrive.domain;

import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    //@Override
    public void readfile(){

    //get path
	Scanner input = new Scanner(System.in);
	String path = input.nextLine();
	
	//get file name
	int index = path.lastIndexOf("/");
	String file = path.substring(index + 1);
	System.out.println(file);
	//tenho de verificar permissões??

	//read file

	try{
		FileReader content = new FileReader(file);
		BufferedReader readcontent = new BufferedReader(content);

		String line = readcontent.readLine(); //read the 1st line

		while (line!=null){
			System.out.printf ("%s\n", line);

			line = readcontent.readLine(); //read until the last line
		}

		content.close();
	} catch (IOException e)
{}

}

    }
