package main.java.com.excilys.cdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import main.java.com.excilys.cdb.validator.DatePeriod;

/**
 * @author Aurelien Denoize Excilys 2018
 */
@DatePeriod(discontinued = "discontinued", introduced = "introduced")
public class ComputerDTO {

    private String id;

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    private String introduced;
    private String discontinued;
    private String company;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the introduced
     */
    public String getIntroduced() {
        return introduced;
    }

    /**
     * @param introduced the introduced to set
     */
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * @return the discontinued
     */
    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued the discontinued to set
     */
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

}
