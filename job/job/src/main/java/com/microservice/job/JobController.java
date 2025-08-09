package com.microservice.job;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.microservice.company.Company;

@RestController
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/getAllJobs")
	public ResponseEntity< List<Job> >findAll()
	{
		return ResponseEntity.ok(jobService.findAllJobs()) ;
	}
	@PostMapping("/createJobs")
	public ResponseEntity<String> createJobs(@RequestBody Job job)
	{
		jobService.createJob(job);
	//	Company company = job.getCompany();
		
		return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getJob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable Long id)
	{
		 
		Job job= jobService.getJob(id);
		if(job!=null)
		{
			return new ResponseEntity<>(job,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteJob/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id)
	{
		String str = jobService.deleteJob(id);
		if(str!=null)
		{
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/updateJob/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob)
	{
		 boolean updated = jobService.updateJob(id, updateJob);
		 if(updated)
		 {
			 return new ResponseEntity<String>("Updated successfulyy",HttpStatus.OK);
			 
		 }
		 else
			 return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
