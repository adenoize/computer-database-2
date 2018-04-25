package test.java.com.excilys.cdb.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.com.excilys.cdb.controller.CliController;

/**
 * @author Aurelien Denoize
 */
@RunWith(MockitoJUnitRunner.class)
public class CliControllerTest {

    CliController cliController = new CliController();

    @Mock
    Map<String, String> args;

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputer1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=null, discontinued=null".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputer2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=null".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputer3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-01".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputer4() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-01".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadDate1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01-01-2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Error on date".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadDate2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01-01-2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Error on date".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadCompanyId1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "abc");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("Error on company id".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadCompanyId2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "-1");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadCompanyId3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadKey1() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadKey2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("fail", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadKey3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadKey4() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputerBadKey5() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("fail", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.addComputer(m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     *************************** UPDATE
     */

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#updateComputer(java.lang.String, java.util.Map)}.
     */
    @Test
    public void testUpdateComputer() {
        Map<String, String> m = new HashMap<>();
        m.put("name", "test");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Computer 1 test, introduced=null, discontinued=null".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputer2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=null".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputer3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-01".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputer4() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-01".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadDate1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01-01-2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Error on date".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadDate2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01-01-2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Error on date".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadCompanyId1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "abc");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("Error on company id".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadCompanyId2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "-1");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadCompanyId3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadKey1() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadKey2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("fail", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadKey3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadKey4() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadKey5() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("fail", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.updateComputer("1", m);

        System.out.println(result);
        assertTrue("An error occurred !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId1() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("fail", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.updateComputer("-1", m);

        System.out.println(result);
        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId2() {

        Map<String, String> m = new HashMap<>();
        m.put("fail", "test");
        m.put("fail", "01/01/2000");
        m.put("fail", "01/01/2000");
        m.put("fail", "100000000000000");

        String result = cliController.updateComputer("100000000000", m);

        System.out.println(result);
        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#listCompany(int)}.
     */
    @Test
    public void testListCompany() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#listComputer(int)}.
     */
    @Test
    public void testListComputer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     */
    @Test
    public void testGetCompany() {
        fail("Not yet implemented");
    }

}
