package com.microservice.job;

 
import java.util.List;
import java.util.Optional;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImp implements JobService{
 //  private List<Job> jobs =new ArrayList<Job>();

    @Autowired
	JobRepository jobRepo;
	
	@Override
	public List<Job> findAllJobs() {
		return jobRepo.findAll();
	}

	@Override
	public String createJob(Job job) {
		jobRepo.save(job);
		return "Job added Successfully";
	}

	@Override
	public Job getJob(Long id) {
		return jobRepo.findById(id).orElse(null);
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
				 job.setDescription(updateJob.getDescription());
				 job.setLocation(updateJob.getLocation());
				 job.setMaxSalary(updateJob.getMaxSalary());
				 job.setMinSalary(updateJob.getMinSalary());
				 job.setTitle(updateJob.getTitle());
				 jobRepo.save(job);
				 return true;
			 }
		 
		 return false;
	}

}
