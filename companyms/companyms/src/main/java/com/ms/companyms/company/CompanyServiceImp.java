package com.ms.companyms.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ms.companyms.DTO.ReviewMessage;
import com.ms.companyms.clients.ReviewClient;

import jakarta.ws.rs.NotFoundException;
 

@Service
public class CompanyServiceImp implements CompanyService {

	@Autowired
	private ReviewClient reviewClient;
	
	@Autowired
	private CompanyRepository companyRepo;
	@Override
	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
		
	}
	@Override
	public String addCompany(Company company) {
		companyRepo.save(company);
		return "Company added successfully";
	}
	@Override
	public boolean updateCompany(Long id, Company company) {
		Optional<Company> companyOptional = companyRepo.findById(id);
		 
			if(companyOptional.isPresent())
			{
				Company companyToUpdate = companyOptional.get();
				companyToUpdate.setDescription(company.getDescription());
				companyToUpdate.setName(company.getName());
				companyRepo.save(companyToUpdate);
				return true;
			}
		
		return false;
	}
	@Override
	public boolean deleteCompany(Long id) {
		try {
			companyRepo.deleteById(id);
			return true;
			
		}catch (Exception e)
		{
			return false;
		}
		 
	}
	@Override
	public Company getCompany(Long id) {
		return companyRepo.findById(id).orElse(null);
	}
	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		Company company = companyRepo.findById(reviewMessage.getCompanyId()).orElseThrow(() -> new NotFoundException("Company Not found"+reviewMessage.getCompanyId()));
	    System.out.println("Description:  "+reviewMessage.getDescription());
		
		
		double averageRating=reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(averageRating);
		companyRepo.save(company);
	}
	
	 

}
