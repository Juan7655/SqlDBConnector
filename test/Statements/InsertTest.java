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
public class InsertTest {
    
    private HashMap<String, String> values = new HashMap<>();
    private String[] columns;
    private String[][] matrix;
    
    public InsertTest() {
        values.put("key1", "value1");
        values.put("key2", "value2");
        values.put("key3", "value3");
        
        this.columns = new String[]{"col1", "col2", "col3"};
        this.matrix = new String[5][3];
        
        for (int i = 0; i < 5; i++) {
            this.matrix[i][0] = "value"+i+"1";
            this.matrix[i][1] = "value"+i+"2";
            this.matrix[i][2] = "value"+i+"3";
        }
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
    public void testInsert1() {
        Insert instance = new Insert(values, true);
        instance.setTable("tbl1");
        String expected="INSERT INTO tbl1(key1, key2, key3)"
                +" VALUES('value1', 'value2', 'value3')";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testInsert2() {
        Insert instance = new Insert(columns, matrix, false);
        instance.setTable("tbl2");
        String expected="INSERT INTO tbl2(col1, col2, col3)"
                +" VALUES(value01, value02, value03), "
                +"(value11, value12, value13), "
                +"(value21, value22, value23), "
                +"(value31, value32, value33), "
                + "(value41, value42, value43)";
        
        assertEquals(expected, instance.getStatement());
    }
}
