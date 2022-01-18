package com.sapient.sms.repository;

import com.sapient.sms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> searchByNameStartsWith(String name);
}
