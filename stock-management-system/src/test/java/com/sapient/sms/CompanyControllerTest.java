package com.sapient.sms;

import com.sapient.sms.controller.CompanyController;
import com.sapient.sms.dto.CompanyDTO;
import com.sapient.sms.entity.Company;
import com.sapient.sms.entity.Director;
import com.sapient.sms.entity.StockExchange;
import com.sapient.sms.service.CompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    @InjectMocks
    CompanyController controller;
    @Mock
    CompanyServiceImpl service;

    private ModelMapper modelMapper = new ModelMapper();
    private CompanyDTO companyDTO;
    private Company company;
    private Company updatedCompany;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        Set<Director> directors = new HashSet<>();
        Set<StockExchange> stockExchanges = new HashSet<>();
        directors.add(new Director(1, "Pulkit"));
        stockExchanges.add(new StockExchange(1, "NSE", "NSE23"));

        companyDTO = new CompanyDTO(
                "Sapient",
                345.90f,
                "Pulkit",
                true,
                "IT",
                "Good company",
                "SDF23",
                directors,
                stockExchanges,
                false
        );

        company = new Company(
                1,
                "Sapient",
                345.90f,
                "Pulkit",
                true,
                "IT",
                "Good company",
                "SDF23",
                directors,
                stockExchanges,
                false
        );

        updatedCompany = new Company(
                1,
                "Sapient",
                345.90f,
                "Pulkit Gupta",
                true,
                "IT",
                "Good company",
                "SDF23",
                directors,
                stockExchanges,
                false
        );

    }

    @Test
    public void getCompanyTest() {
        when(service.get(1)).thenReturn(company);
        Company c = controller.getCompany(1);
        assertEquals("Pulkit", c.getCeo());
    }

    @Test
    public void getAllCompanyTest() {
        when(service.getAll()).thenReturn(Arrays.asList(company));
        List<Company> companies = controller.getAllCompany();
        assertEquals("Pulkit", companies.get(0).getCeo());
    }

    @Test
    public void saveCompanyTest() {
        when(service.create(Mockito.any(Company.class))).thenReturn(company);
        Company created = controller.createCompany(companyDTO);
        assertEquals("Pulkit", created.getCeo());
    }

     @Test
     public void updateCompanyTest() {
         when(service.update(Mockito.eq(1), Mockito.any(Company.class))).thenReturn(updatedCompany);
         Company c = controller.updateCompany(1, companyDTO);
         assertEquals("Pulkit Gupta", c.getCeo());
     }

    @Test
    public void getByNameCompanyTest() {
        when(service.getByName("Pulkit")).thenReturn(Arrays.asList(company));
        List<Company> searched = controller.getByNameCompany("Pulkit");
        assertEquals('P', searched.get(0).getCeo().charAt(0));
    }

    @Test
    public void deleteCompanyTest() {
        when(service.delete(1)).thenReturn(company);
        Company c = controller.deleteCompany(1);
        System.out.println(c);
        assertEquals("Pulkit", c.getCeo());
    }

}
