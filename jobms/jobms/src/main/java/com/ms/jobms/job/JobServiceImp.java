package com.ms.jobms.job;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.jobms.DTO.JobDTO;
import com.ms.jobms.clients.CompanyClient;
import com.ms.jobms.clients.ReviewsClient;
import com.ms.jobms.job.external.Company;
import com.ms.jobms.job.external.Review;
import com.ms.jobms.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImp implements JobService{
 //  private List<Job> jobs =new ArrayList<Job>();

	@Autowired
	private CompanyClient companyClient;
	int attempt=0;
	
	@Autowired
	private ReviewsClient reviewClientt;
	
    @Autowired
	JobRepository jobRepo;
    
    @Autowired
    RestTemplate restTemplate;
	
	@Override
	//@CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
	//@Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
	@RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
	public List<JobDTO> findAllJobs() {
		List<Job> jobs =jobRepo.findAll();
		List<JobDTO> jobDTOs = new ArrayList<>();
		System.out.println("Attempt"+ ++attempt);
		
		/*
		 * for(Job job:jobs) { JobWithCompanyDTO jobDto = new JobWithCompanyDTO();
		 * jobDto.setJob(job); Company company=
		 * restTemplate.getForObject("http://localhost:8082/companies/getCompany/"+job.
		 * getCompanyId(),Company.class); jobDto.setCompany(company);
		 * jobWithCompanyDTOs.add(jobDto); }
		 */
		
		return jobs.stream().map(this::convertIntoDTO).collect(Collectors.toList());
		 
		//return jobWithCompanyDTOs;
	}
	
	public List<String> companyBreakerFallback(Exception e)
	{
		List<String> list =new ArrayList<String>();
		list.add("Dummy Data");
		return list;
	}
	
	private JobDTO convertIntoDTO(Job job)
	{
	//	RestTemplate restTemplate = new RestTemplate();
	//	JobWithCompanyDTO jobDto = new JobWithCompanyDTO();
		
		
		//jobDto.setJob(job);
//		Company company=	restTemplate.getForObject("http://COMPANYMS:8082/companies/getCompany/"+job.getCompanyId(),Company.class);

		Company company = companyClient.getCompany(job.getCompanyId());
		
		
//		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8083/reviews/getAllReviews?companyId="+job.getCompanyId(), HttpMethod.GET
//				,null,new ParameterizedTypeReference<List<Review>>() {
//		});

		List<Review> reviews =reviewClientt.getReviews(job.getCompanyId());
		
		//List<Review> reviews = reviewResponse.getBody();
		
		JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
		//jobDto.setCompany(company);
		return jobDTO;
		/*
		 * RestTemplate restTemplate = new RestTemplate(); JobWithCompanyDTO jobDto =
		 * new JobWithCompanyDTO(); jobDto.setJob(job); Company company=
		 * restTemplate.getForObject("http://localhost:8082/companies/getCompany/"+job.
		 * getCompanyId(),Company.class); jobDto.setCompany(company); return jobDto;
		 */
		
	}

	@Override
	public String createJob(Job job) {
		jobRepo.save(job);
		return "Job added Successfully";
	}

	@Override
	public JobDTO getJob(Long id) {
		Job job= jobRepo.findById(id).orElse(null);
		return convertIntoDTO(job);
	}

	@Override
	public String deleteJob(Long id) {
		try {
			jobRepo.deleteById(id);
			return "Successfully deleted";
			
		}catch (Exception e)
		{
			return null;
		}
		 
		 
	}

	@Override
	public boolean updateJob(Long id,Job updateJob) {
		
		Optional<Job> jobOptional = jobRepo.findById(id);
		 
			 if(jobOptional.isPresent())
			 {
				 Job job=jobOptional.get();
				 if(updateJob.getDescription()!=null)
					 job.setDescription(updateJob.getDescription());
				 if(updateJob.getLocation()!=null)
					 job.setLocation(updateJob.getLocation());
				 if(updateJob.getMaxSalary()!=null)
					 job.setMaxSalary(updateJob.getMaxSalary());
				 if(updateJob.getMinSalary()!=null)
					 job.setMinSalary(updateJob.getMinSalary());
				 if(updateJob.getTitle()!=null)
					 job.setTitle(updateJob.getTitle());
				 if(updateJob.getCompanyId()!=null)
					 job.setCompanyId(updateJob.getCompanyId());
					
				 jobRepo.save(job);
				 return true;
			 }
		 
		 return false;
	}

}
