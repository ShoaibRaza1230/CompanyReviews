package com.ms.jobms.mapper;

import java.util.List;

import com.ms.jobms.DTO.JobDTO;
import com.ms.jobms.job.Job;
import com.ms.jobms.job.external.Company;
import com.ms.jobms.job.external.Review;

public class JobMapper {
	
	public static JobDTO mapToJobWithCompanyDTO(Job job,Company company,List<Review> reviews)
	{
		JobDTO jobDTO = new JobDTO();
		jobDTO.setCompany(company);
		jobDTO.setDescription(job.getDescription());
		jobDTO.setId(job.getId());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setReview(reviews);
		
		return jobDTO;
	}

}
