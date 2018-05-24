package main.java.com.excilys.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.dto.ComputerDTO;
import main.java.com.excilys.cdb.exception.MapperException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;

/**
 * @author Aurelien Denoize Excilys 2018
 */
@Component
public class ComputerDTOMapper {

    @Autowired
    private CompanyService companyService;

    /**
     * Mapper for ComputerDTO to Computer.
     * @param computerDTO The computerDTO to map
     * @return The computer
     * @throws MapperException MapperException
     */
    public Computer toComputer(ComputerDTO computerDTO) throws MapperException {

        Long id = null;
        String name = computerDTO.getName();
        LocalDate introduced = null;
        LocalDate discontinued = null;
        Company company = null;

        if (computerDTO.getId() != null) {
            try {
                id = Long.parseLong(computerDTO.getId());
            } catch (NumberFormatException e) {
                throw new MapperException("Bad id");
            }
        }
        try {
            introduced = LocalDate.parse(computerDTO.getIntroduced(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            introduced = null;
        }
        try {
            discontinued = LocalDate.parse(computerDTO.getDiscontinued(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            discontinued = null;
        }
        try {
            Long companyId = Long.valueOf(computerDTO.getCompany());
            if (companyId > 0) {
                try {
                    company = companyService.findById(companyId);
                } catch (NoSuchElementException e) {
                    company = null;
                }
            }
        } catch (NumberFormatException e) {
            company = null;
        }

        return new Computer(id, name, introduced, discontinued, company);
    }

    /**
     * Mapper for Computer to ComputerDTO.
     * @param computer The computer to map
     * @return The ComputerDTO
     */
    public ComputerDTO toDTO(Computer computer) {
        ComputerDTO computerDTO = new ComputerDTO();
        Long id = computer.getId();
        String name = computer.getName();
        LocalDate introduced = computer.getIntroduced();
        LocalDate discontinued = computer.getDiscontinued();
        Company company = computer.getCompany();

        if (id != null) {
            computerDTO.setId(id.toString());
        }
        if (name != null) {
            computerDTO.setName(name);
        }
        if (introduced != null) {
            computerDTO.setIntroduced(introduced.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        if (discontinued != null) {
            computerDTO.setDiscontinued(discontinued.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        if (company != null && company.getId() != null) {
            computerDTO.setCompany(company.getId().toString());
        }

        return computerDTO;

    }

}
