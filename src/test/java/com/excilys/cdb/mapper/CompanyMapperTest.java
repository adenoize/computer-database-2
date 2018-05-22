package test.java.com.excilys.cdb.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;

/**
 * Test for CompanyMapper.
 * @author Aurelien Denoize
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyMapperTest {

    ResultSet resultSet;

    @Autowired
    CompanyMapper companyMapper;

    /**
     * Mock of computer.
     */
    @Before
    public void before() {
        resultSet = mock(ResultSet.class);

    }

    /**
     * Test method for
     * {@link main.java.com.excilys.cdb.mapper.CompanyMapper#map(java.sql.ResultSet)}.
     * @throws Exception Exception
     */
    @Test
    public void testMap() throws Exception {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("test");

        Company company = companyMapper.map(resultSet);

        assertEquals(1, company.getId().longValue());
        assertTrue(company.getName().equals("test"));
    }

}
