package com.microservice.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.company.Company;
import com.microservice.company.CompanyService;

@Service
public class ReviewServiceImp implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private CompanyService companyService;
	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		return reviews;
	}
	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company = companyService.getCompany(companyId);
		if(company!=null)
		{
			review.setCompany(company);
			reviewRepo.save(review);
			return true;
		}
		return false;
		
	}
	@Override
	public Review getReview(Long companyId, Long reviewId) {
		List<Review> reviews=reviewRepo.findByCompanyId(companyId);
		return reviews.stream().filter(review->review.getId().equals(reviewId))
				.findFirst().orElse(null);
	}
	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
		if(companyService.getCompany(companyId)!=null)
		{
			updateReview.setCompany(companyService.getCompany(companyId));
			updateReview.setId(reviewId);
			reviewRepo.save(updateReview);
			return true;
		}
		
		return false;
		 
	}
	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		if(companyService.getCompany(companyId)!=null &&
				reviewRepo.existsById(reviewId))
		{
			Review review = reviewRepo.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(companyId, company);
			reviewRepo.deleteById(reviewId);
			return true;
			
		}
		else
		{
			return false;
		}
		
		

	}

}
