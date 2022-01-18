package com.sapient.sms;

import com.sapient.sms.entity.Company;
import com.sapient.sms.entity.Director;
import com.sapient.sms.entity.StockExchange;
import com.sapient.sms.repository.ICompanyRepository;
import com.sapient.sms.service.CompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    ICompanyRepository repository;

    @InjectMocks
    CompanyServiceImpl service;

    private Company company;
    private Company updatedCompany;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        Set<Director> directors = new HashSet<>();
        Set<StockExchange> stockExchanges = new HashSet<>();
        directors.add(new Director(1, "Pulkit"));
        stockExchanges.add(new StockExchange(1, "NSE", "NSE23"));

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
        when(repository.findById(1)).thenReturn(Optional.of(company));
        Company find = service.get(1);
        assertEquals("Pulkit", find.getCeo());
    }

    @Test
    public void getByNameCompanyTest() {
        when(repository.searchByNameStartsWith("Sapient")).thenReturn(Arrays.asList(company));
        List<Company> companies = service.getByName("Sapient");
        assertEquals("Sapient", companies.get(0).getName());
    }

    @Test
    public void getCompanyWithNullTest() {
        when(repository.findById(1)).thenReturn(null);
        Company find = service.get(5);
        assertEquals(null, find);
    }

    @Test
    public void getAllCompanyTest() {
        when(repository.findAll()).thenReturn(Arrays.asList(company));
        List<Company> companies = service.getAll();
        assertEquals("Pulkit", companies.get(0).getCeo());
    }

    @Test
    public void saveCompanyTest() {
        when(repository.save(company)).thenReturn(company);
        Company created = service.create(company);
        System.out.println(created);
        assertEquals("Sapient", created.getName());
    }

    @Test
    public void updateCompanyTest() {
        when(repository.findById(1)).thenReturn(Optional.of(company)).thenReturn(Optional.of(company));
        when(repository.save(company)).thenReturn(company);
        Company updated = service.update(1, updatedCompany);
        assertEquals("Pulkit Gupta", updated.getCeo());
    }

    @Test
    public void updateCompanyWithNullTest() {
        when(repository.findById(1)).thenReturn(null);
        Company updated = service.update(5, updatedCompany);
        assertEquals(null, updated);
    }

    @Test
    public void deleteCompanyTest() {
        when(repository.findById(1)).thenReturn(Optional.of(company));
        Company deleted = service.delete(1);
        assertEquals("Pulkit", deleted.getCeo());
    }

    @Test
    public void deleteCompanyWithNullTest() {
        when(repository.findById(1)).thenReturn(null);
        Company deleted = service.delete(5);
        assertEquals(null, deleted);
    }

}
