package com.ms.companyms.company;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ms.companyms.DTO.ReviewMessage;
 
public interface CompanyService {
	List<Company> getAllCompanies();
	String addCompany(Company company);
	boolean updateCompany(Long id, Company company);
	boolean deleteCompany(Long id);
	Company getCompany(Long id);
	public void updateCompanyRating(ReviewMessage reviewMessage);

}
