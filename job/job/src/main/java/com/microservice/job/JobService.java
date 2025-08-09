package com.microservice.job;

import java.util.List;

public interface JobService {
	
    List<Job> findAllJobs();
	String createJob(Job job);
	Job getJob(Long id);
	String deleteJob(Long id);
	boolean updateJob(Long id,Job job);
	
	

}
