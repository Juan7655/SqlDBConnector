/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juandavid
 */
public class DeleteTest {
    
    public DeleteTest() {
    }
    
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
     * Test of addStatement method, of class Delete.
     */
    @Test
    public void testAddStatement() {
        HashMap<String, String> values = new HashMap<>();
        values.put("col1", "val1");
        values.put("col2", "val2");
        values.put("col3", "val3");
        
        Delete instance = new Delete(values, true);
        instance.setTable("tbl1");
        
        assertEquals("DELETE FROM tbl1 WHERE col2 = 'val2' AND col3 = 'val3' AND col1 = 'val1'",
                instance.getStatement());
    }
}
