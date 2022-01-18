package com.sapient.sms.service;

import com.sapient.sms.entity.Company;

import java.util.List;

public interface ICompanyService {

    Company create(Company company);

    List<Company> getAll();

    Company get(int id);

    Company update(int id, Company company);

    Company delete(int id);

    List<Company> getByName(String name);
}