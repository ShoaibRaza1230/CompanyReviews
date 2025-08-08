package com.ms.reviewms.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class ReviewServiceImp implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepo;
 
	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		return reviews;
	}
	@Override
	public boolean addReview(Long companyId, Review review) {
		//Company company = companyService.getCompany(companyId);
		
		if(companyId!=null)
		{
			review.setCompanyId(companyId);
			reviewRepo.save(review);
			return true;
		}
		return false;
		
	}
	@Override
	public Review getReview(Long reviewId) {	
		return reviewRepo.findById(reviewId).orElse(null);
	}
	@Override
	public boolean updateReview(Long reviewId, Review updateReview) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
		if(review!=null)
		{
			review.setTitle(updateReview.getTitle());
			review.setCompanyId(updateReview.getCompanyId());
			review.setRating(updateReview.getRating());
			review.setDescription(updateReview.getDescription());
			
			 
			reviewRepo.save(review);
			return true;
		}
		
		return false;
		 
	}
	@Override
	public boolean deleteReview(Long reviewId) {
		Review review= reviewRepo.findById(reviewId).orElse(null);
		if(review!=null)
		{
			reviewRepo.delete(review);;
			return true;
		}
		else
		{
			return false;
		}
		
		

	}

}
