package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;
import pt.tecnico.mydrive.domain.Link;


public class ChangeDirectoryTest extends AbstractServiceTest {

    protected void populate() {
      
        MyDrive md = MyDrive.getInstance();
		
		
		md.createUser("henrique");
		
		md.createUser("leonor");
        
        
    }

    //private Contact getContact(String personName, String contactName) {
        //Person p = PhoneBookService.getPhoneBook().getPersonByName(personName);
        //return p.getContactByName(contactName);
    //}

    //@Test
    //public void success() {
        //final String personName = "João";
        //RemoveContactService service = new RemoveContactService(personName, "António");
        //service.execute();

        //// check contact was removed
        //Contact c = getContact(personName, "António");
        //assertNull("contact was not removed", c);
        //assertEquals("Invalid number of contacts", 0, PhoneBookService.getPerson(personName).getContactSet().size());
    //}

    //@Test(expected = ContactDoesNotExistException.class)
    //public void removeInvalidContact() {
        //final String personName = "João";
        //RemoveContactService service = new RemoveContactService(personName, "Ant");
        //service.execute();
    //}

    //@Test(expected = PersonDoesNotExistException.class)
    //public void invalidPerson() {
        //final String personName = "José";
        //RemoveContactService service = new RemoveContactService(personName, "António");
        //service.execute();
    //}
}
