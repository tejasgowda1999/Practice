package com.sapient.sms.service;

import com.sapient.sms.ExcludeFromJacocoGeneratedReport;
import com.sapient.sms.entity.Company;
import com.sapient.sms.entity.Director;
import com.sapient.sms.entity.StockExchange;
import com.sapient.sms.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyRepository repository;

    @Override
    @Transactional
    public Company create(Company company) {
        return repository.save(company);
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = repository.findAll();
        for (Company c : companies)
            if (c.getFlag()) {
                companies.remove(c);
            }
        return companies;
    }

    @Override
    public Company get(int id) {
        Company company = repository.findById(id).orElse(null);
        if (company != null && !company.getFlag())
            return company;
        return null;
    }

    @Override
    @Transactional
    public Company update(int id, Company company) {
        Company updateCompany = repository.findById(id).orElse(null);
        if (updateCompany == null)
            return null;

        updateCompany.setTurnover(company.getTurnover() != null ? company.getTurnover() : updateCompany.getTurnover());
        updateCompany.setBrief(company.getBrief() != null ? company.getBrief() : updateCompany.getBrief());
        updateCompany.setCeo(company.getCeo() != null ? company.getCeo() : updateCompany.getCeo());
        updateCompany.setListed(company.getListed() != null ? company.getListed() : updateCompany.getListed());
        updateCompany.setName(company.getName() != null ? company.getName() : updateCompany.getName());
        updateCompany.setSector(company.getSector() != null ? company.getSector() : updateCompany.getSector());
        updateCompany.setStockCode(company.getStockCode() != null ? company.getStockCode() : updateCompany.getStockCode());

        Set<Director> directorMergedSet = mergeSet(company.getDirectors(), updateCompany.getDirectors());
        updateCompany.setDirectors(directorMergedSet);

        Set<StockExchange> stockExchangeMergedSet = mergeSet(company.getStockExchanges(), updateCompany.getStockExchanges());
        updateCompany.setStockExchanges(stockExchangeMergedSet);

        return repository.save(updateCompany);
    }

    @Override
    public Company delete(int id) {
        Company deletedCompany = repository.findById(id).orElse(null);
        if (deletedCompany == null)
            return null;
        deletedCompany.setFlag(true);
        return deletedCompany;
    }

    @Override
    public List<Company> getByName(String name) {
        return repository.searchByNameStartsWith(name);
    }

    @ExcludeFromJacocoGeneratedReport
    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {
        Set<T> mergedSet = new HashSet<>();
        if (a != null)
            mergedSet.addAll(a);
        if (b != null)
            mergedSet.addAll(b);

        return mergedSet;
    }

}
