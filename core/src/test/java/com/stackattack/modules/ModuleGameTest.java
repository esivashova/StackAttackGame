
package test.java.com.stackattack.modules;

import com.stackattack.ModuleGame;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;


public class ModuleGameTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  

    /**
     * Test of run method, of class ModuleGame.
     */
    @org.junit.Test
    public void testRun() {
        
    }

    /**
     * Test of keyUp method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyUp() {
        System.out.println("keyUp");
        ModuleGame instance = new ModuleGame();
        int expResult = 0;
        int result = instance.keyUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of keyLeft method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyLeft() {
        System.out.println("keyLeft");
        ModuleGame instance = new ModuleGame();
        int expResult = 1;
        int result = instance.keyLeft();
        assertEquals(expResult, result);   
    }


    /**
     * Test of keyRight method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyRight() {
        System.out.println("keyRight");
        ModuleGame instance = new ModuleGame();
        int expResult = 2;
        int result = instance.keyRight();
        assertEquals(expResult, result);   
    }
    
    /**
     * Test of keyLeftUp method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyLeftUp() {
        System.out.println("keyLeftUp");
        ModuleGame instance = new ModuleGame();
        int expResult = 3;
        int result = instance.keyLeftUp();
        assertEquals(expResult, result);   
    }
    
    /**
     * Test of keyRightUp method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyRightUp() {
        System.out.println("keyRightUp");
        ModuleGame instance = new ModuleGame();
        int expResult = 4;
        int result = instance.keyRightUp();
        assertEquals(expResult, result);   
    }

    /**
     * Test of unload method, of class ModuleGame.
     */
    @org.junit.Test
    public void testUnload() {
        System.out.println("unload");
        ModuleGame instance = new ModuleGame();
        instance.unload(); 
    }
    
}
