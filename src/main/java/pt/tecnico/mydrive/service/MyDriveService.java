package pt.tecnico.mydrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.FileSystem;
import pt.tecnico.mydrive.exceptions.MyDriveException;
import pt.tecnico.mydrive.exceptions.FileSystemException;




public abstract class MyDriveService {
    protected static final Logger log = LogManager.getRootLogger();

    @Atomic
    public final void execute() throws MyDriveException {
        dispatch();
    }

    static MyDrive getMydrive() {
        return MyDrive.getInstance();
    }

    static FileSystem getFilesystem() throws FileSystemException{

        FileSystem fs = getMydrive().getFilesystem();

        return fs;

    }
    protected abstract void dispatch() throws MyDriveException;
}
