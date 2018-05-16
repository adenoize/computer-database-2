package test.java.com.excilys.cdb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.com.excilys.cdb.controller.CliController;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
@RunWith(MockitoJUnitRunner.class)
public class CliControllerTest {

    CliController cliController = new CliController();

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testAddComputer1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");

        String result = cliController.addComputer(m);

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
        m.put("discontinued", "02/01/2000");

        String result = cliController.addComputer(m);

        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-02".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        assertTrue("Computer 0 test, introduced=2000-01-01, discontinued=2000-01-02".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

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
        m.put("discontinued", "02-01-2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

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
        m.put("discontinued", "02/01/2000");
        m.put("company", "abc");

        String result = cliController.addComputer(m);

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
        m.put("discontinued", "02/01/2000");
        m.put("company", "-1");

        String result = cliController.addComputer(m);

        assertTrue("Company ID have to be positive".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "100000000000000");

        String result = cliController.addComputer(m);

        assertTrue("Error on company id".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        assertTrue("Bad argument".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        assertTrue("Bad argument".equals(result));
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
        m.put("fail", "02/01/2000");
        m.put("company", "1");

        String result = cliController.addComputer(m);

        assertTrue("Bad argument".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("fail", "1");

        String result = cliController.addComputer(m);

        assertTrue("Bad argument".equals(result));
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
        m.put("fail", "02/01/2000");
        m.put("fail", "1");

        String result = cliController.addComputer(m);

        assertTrue("Bad argument".equals(result));
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

        assertTrue("Computer 1 test, introduced=2000-01-01, discontinued=null".equals(result));
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
        m.put("discontinued", "02/01/2000");

        String result = cliController.updateComputer("1", m);

        assertTrue("Computer 1 test, introduced=2000-01-01, discontinued=2000-01-02".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Computer 1 test, introduced=2000-01-01, discontinued=2000-01-02".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

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
        m.put("discontinued", "02-01-2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

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
        m.put("discontinued", "02/01/2000");
        m.put("company", "abc");

        String result = cliController.updateComputer("1", m);

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
        m.put("discontinued", "02/01/2000");
        m.put("company", "-1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Company ID have to be positive".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "");

        String result = cliController.updateComputer("1", m);

        assertTrue("Error on company id".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Bad argument".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Bad argument".equals(result));
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
        m.put("fail", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Bad argument".equals(result));
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
        m.put("discontinued", "02/01/2000");
        m.put("fail", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Bad argument".equals(result));
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
        m.put("fail", "02/01/2000");
        m.put("fail", "1");

        String result = cliController.updateComputer("1", m);

        assertTrue("Bad argument".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId1() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("-1", m);

        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId2() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("100000000000", m);

        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId3() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("", m);

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#addComputer(java.util.Map)}.
     */
    @Test
    public void testUpdateComputerBadId4() {

        Map<String, String> m = new HashMap<>();
        m.put("name", "test");
        m.put("introduced", "01/01/2000");
        m.put("discontinued", "02/01/2000");
        m.put("company", "1");

        String result = cliController.updateComputer("abc", m);

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputer() {
        String result = cliController.removeComputer("123");

        assertTrue("Computer is remove".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputerNotFound1() {
        String result = cliController.removeComputer("-1");

        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputerNotFound2() {
        String result = cliController.removeComputer("100000000000");

        assertTrue("Computer not found".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputerBadId1() {
        String result = cliController.removeComputer("");

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#removeComputer(java.lang.String)}.
     */
    @Test
    public void testRemoveComputerBadId2() {
        String result = cliController.removeComputer("abc");

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#listCompany(int)}.
     */
    @Test
    public void testListCompany() {
        Page<Company> page = cliController.listCompany(1);
        assertNotNull(page);
        assertEquals(10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#listComputer(int)}.
     */
    @Test
    public void testListComputer() {
        Page<Computer> page = cliController.listComputer(1);
        assertNotNull(page);
        assertEquals(10, page.getPage().size());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputer() {
        Computer computer = new Computer("Apple II Plus", null, null, null);
        computer.setId(11L);
        String result = cliController.showComputer("11");

        assertTrue(computer.toString().equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputerBadId1() {

        String result = cliController.showComputer("");

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputerBadId2() {

        String result = cliController.showComputer("abc");

        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputerBadId3() {

        String result = cliController.showComputer("-1");

        assertTrue("Computer not found !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#showComputer(java.lang.String)}.
     */
    @Test
    public void testShowComputerBadId4() {

        String result = cliController.showComputer("10000000000");

        assertTrue("Computer not found !".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     */
    @Test
    public void testGetCompany() {
        Optional<Company> company = cliController.getCompany(1L);
        assertTrue(company.isPresent());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testGetCompanyBadId1() throws Exception {
        Optional<Company> company = cliController.getCompany(100000000000L);
        assertTrue(!company.isPresent());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testGetCompanyBadId2() throws Exception {
        Optional<Company> company = cliController.getCompany(-1L);
        assertTrue(!company.isPresent());
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testRemoveCompany() throws Exception {
        String result = cliController.removeCompany("40");

        assertTrue("Company is remove".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testRemoveCompanyBadIdFormat1() throws Exception {
        String result = cliController.removeCompany("");
        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testRemoveCompanyBadIdFormat2() throws Exception {
        String result = cliController.removeCompany("abc");
        assertTrue("Error on id format".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testRemoveCompanyBadId1() throws Exception {
        String result = cliController.removeCompany("1000000000");
        assertTrue("Error : company not found or database error".equals(result));
    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.controller.CliController#getCompany(java.lang.Long)}.
     * @throws Exception Exception
     */
    @Test
    public void testRemoveCompanyBadId2() throws Exception {
        String result = cliController.removeCompany("-1");
        assertTrue("Error : company not found or database error".equals(result));
    }

}