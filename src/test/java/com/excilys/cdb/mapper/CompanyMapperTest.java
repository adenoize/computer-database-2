package test.java.com.excilys.cdb.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;

/**
 * Test for CompanyMapper.
 * @author Aurelien Denoize
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CompanyMapperTest {


    @Mock
    ResultSet resultSet;

    CompanyMapper companyMapper = new CompanyMapper();

    /**
     * Test method for {@link main.java.com.excilys.cdb.mapper.CompanyMapper#map(java.sql.ResultSet)}.
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
