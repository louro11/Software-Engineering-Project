package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.DirectoryCantBeDeletedException;

public class DeleteFileService extends MyDriveService {

    private String fileName;

    public DeleteFileService(String name) {
        fileName = name;
    }

    public final void dispatch() throws FileNotFoundException, DirectoryCantBeDeletedException  {
        
       getMydrive().removeFile(fileName); 
        
    }
}
