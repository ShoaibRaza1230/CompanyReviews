package com.ms.jobms.job;

import java.util.List;

import com.ms.jobms.DTO.JobDTO;

public interface JobService {
	
    List<JobDTO> findAllJobs();
	String createJob(Job job);
	JobDTO getJob(Long id);
	String deleteJob(Long id);
	boolean updateJob(Long id,Job job);
	
	

}
