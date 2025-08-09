package com.microservice.company;

import java.util.List;

import org.springframework.stereotype.Service;
 
public interface CompanyService {
	List<Company> getAllCompanies();
	String addCompany(Company company);
	boolean updateCompany(Long id, Company company);
	boolean deleteCompany(Long id);
	Company getCompany(Long id);

}
