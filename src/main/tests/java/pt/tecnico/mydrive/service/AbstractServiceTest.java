package pt.tecnico.mydrive.domain;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.core.WriteOnReadError;
import pt.tecnico.mydrive.MyDriveApplication;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;



public abstract class AbstractServiceTest {
    protected static final Logger log = LogManager.getRootLogger();

    @BeforeClass
    public static void setUpBeforeAll() throws Exception {

	MyDriveApplication.init();
    }

    @Before
    public void setUp() throws Exception {
        try {
            FenixFramework.getTransactionManager().begin(false);
            populate();
        } catch (WriteOnReadError | NotSupportedException | SystemException e1) {
            e1.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            FenixFramework.getTransactionManager().rollback();
        } catch (IllegalStateException | SecurityException | SystemException e) {
            e.printStackTrace();
        }
    }

    protected abstract void populate();
}
