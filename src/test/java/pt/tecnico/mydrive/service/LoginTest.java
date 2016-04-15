package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;

import org.junit.*;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.service.MyDriveService;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class LoginTest extends AbstractServiceTest{

    // static members

    // one-time initialization and clean-up
    @BeforeClass
    public static void oneTimeSetUp() {
    }

    @AfterClass
    public static void oneTimeTearDown() {
    }

    // members

    private Login login;
    private User user;
    private Directory dir;
    private DateTime dt;


    // initialization and clean-up for each test
    @Before
    public void setUp() {
        dt = new DateTime();
        user = new User("HenriqueCarloss", "password123", "Henrique", "rwxd---", dir);
        dir = new Directory("Henrique", 4, dt, "rwxd---", user);
        login = new Login(user);

        Random rand = new Random();
        long token = rand.nextLong();

    }

    @After
    public void tearDown() {
        login = null;
    }

    // tests
    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUserNotExist() throws Exception{
        set_username("Madalena");
        login.getUser();
    }

    @Test(expected = LoginDoesNotExistException.class)
    public void testLoginNotExist() throws Exception{
        token = rand.nextLong();
    }

    @Test(expected = LoginInvalidException.class)
}	
	
	
    