/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

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
public class SelectTest {
    
    public SelectTest() {
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
    public void testAllNull() {
        Select instance = new Select(false, null, null, null, null, null, null, null);
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testDistinct() {
        Select instance = new Select(true, null, null, null, null, null, null, null);
        
        instance.setTable("tbl");
        String expected="SELECT DISTINCT * FROM tbl";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testColumns() {
        Select instance = new Select(false, new String[]{"col1", "col2", "col3"}, "col4 LIKE '%Me%'", null, null, null, null, null);
        
        instance.setTable("tbl");
        String expected="SELECT col1, col2, col3 FROM tbl WHERE col4 LIKE '%Me%'";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testWhereArgs() {
        Select instance = new Select(false, null, "col5 = ? AND col8 = ?", new String[]{"ad", "db"}, null, null, null, null);
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl WHERE col5 = ad AND col8 = db";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testGroupBy() {
        Select instance = new Select(false, null, null, null, "profesor_id", null, null, null);
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl GROUP BY profesor_id";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testHaving() {
        Select instance = new Select(false, null, null, null, null, "col2 < 5", null, null);
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl HAVING col2 < 5";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testOrderBy() {
        Select instance = new Select(false, null, null, null, null, null, "col3 DESC", null);
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl ORDER BY col3 DESC";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testLimit() {
        Select instance = new Select(false, null, null, null, "profesor_id", null, "col6 ASC", "56");
        
        instance.setTable("tbl");
        String expected="SELECT * FROM tbl GROUP BY profesor_id ORDER BY col6 ASC LIMIT 56";
        
        assertEquals(expected, instance.getStatement());
    }
    
    @Test
    public void testAll() {
        Select instance = new Select(true, new String[]{"col1", "col2", "col3"}, 
                "col5 = ? AND col8 = ?", new String[]{"ad","db"}, "profesor_id", "exams > 7", "col6 ASC", "56");
        
        instance.setTable("tbl");
        String expected="SELECT DISTINCT col1, col2, col3 FROM tbl "
                + "WHERE col5 = ad AND col8 = db GROUP BY profesor_id "
                + "HAVING exams > 7 ORDER BY col6 ASC LIMIT 56";
        
        assertEquals(expected, instance.getStatement());
    }
}
