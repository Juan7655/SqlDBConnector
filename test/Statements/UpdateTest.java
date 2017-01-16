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
public class UpdateTest {
    
    private final HashMap<String, String> values = new HashMap<>();
    private final boolean allText = true;
    private final HashMap<String, String> condition = new HashMap<>();
    private final boolean exact = false;
    
    public UpdateTest() {
        values.put("col1", "val1");
        values.put("col2", "val2");
        condition.put("col3", "val3");
        condition.put("col4", "val4");
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

    @Test
    public void testSomeMethod() {
        Update instance = new Update(values, allText, condition, exact);
        instance.setTable("tbl");
        
        assertEquals("UPDATE tbl SET col2 = 'val2', col1 = 'val1' WHERE col4 LIKE 'val4' AND col3 LIKE 'val3'",
                instance.getStatement());
    }
    
}
