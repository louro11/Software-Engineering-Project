package pt.tecnico.mydrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.mydrive.domain.MyDrive;


public abstract class MyDriveService {
    protected static final Logger log = LogManager.getRootLogger();

    @Atomic
    public final void execute() /*throws MyDriveException */{
        dispatch();
    }

    static MyDrive getMydrive() {
        return MyDrive.getInstance();
    }

    //static Person getPerson(String personName) throws PersonDoesNotExistException {
        //Person p = getPhoneBook().getPersonByName(personName);

        //if (p == null)
            //throw new PersonDoesNotExistException(personName);       /* isto estava no phonebook, verificar se necessario  */

        //return p;
    //}

    protected abstract void dispatch() /*throws MyDriveException*/;
}
