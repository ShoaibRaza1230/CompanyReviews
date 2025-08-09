package com.microservice.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.job.Job;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Company>> getAllCompanies()
	{
		return new ResponseEntity<>( companyService.getAllCompanies(),HttpStatus.OK);
	}
	
	@GetMapping("/getCompany/{id}")
	public ResponseEntity<Company> getJob(@PathVariable Long id)
	{
		 
		Company company= companyService.getCompany(id);
		if(company!=null)
		{
			return new ResponseEntity<>(company,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/addCompany")
	public ResponseEntity<String> addCompany(@RequestBody Company company)
	{
		return new ResponseEntity<>(companyService.addCompany(company),HttpStatus.OK); 
	}
	
	@PutMapping("/updateCompany/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company)
	{
		 boolean updated = companyService.updateCompany(id, company);
		 if(updated)
			 return new ResponseEntity<String>("Updated successfulyy",HttpStatus.OK); 
		 else
			 return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteCompany/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id)
	{
		boolean del = companyService.deleteCompany(id);
		if(del)
			return new ResponseEntity<>("deleted sucessfully",HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
