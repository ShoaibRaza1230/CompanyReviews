package com.ms.reviewms.review;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.reviewms.messaging.ReviewMessageProducer;
 
@RestController
@RequestMapping("/reviews")
public class ReviewController {

	
	  @Autowired 
	  private ReviewService reviewService;  
	  
	  @Autowired
	  private ReviewMessageProducer reviewMessageProducer;
	   
	  
	  @GetMapping()
	  public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
	  return new ResponseEntity<>(reviewService.getAllReviews(companyId),
	  HttpStatus.OK);   }
	 

	  
	  @PostMapping() 
	  public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review)
	  {
		 boolean check= reviewService.addReview(companyId,review); 
		 if(check)
		 {
			 reviewMessageProducer.sendMessage(review);
			 return new ResponseEntity<>("Review added Sucessfully",HttpStatus.OK);
 
		 }
		 else
			  return new ResponseEntity<>("Operation failed",HttpStatus.NOT_FOUND);

	  }
	  
	  @GetMapping("/{reviewId}")
	  public ResponseEntity<Review> getReview(@PathVariable Long reviewId)
	  {
		  return new ResponseEntity<> (reviewService.getReview(reviewId),HttpStatus.OK);
	  }
		  
		
		  @PutMapping("/{reviewId}") 
		  public ResponseEntity<String> updateReview(@PathVariable Long reviewId ,@RequestBody Review review) {
			  boolean updated = reviewService.updateReview(reviewId ,review); 
			  if (updated) 
				  return new ResponseEntity<String>("Updated successfulyy", HttpStatus.OK); 
			  else 
				  return new ResponseEntity<String>(HttpStatus.NOT_FOUND); }
		 
		
		  @DeleteMapping("/{reviewId}") 
		  public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
			  boolean del = reviewService.deleteReview(reviewId); 
			  if (del)
				  return new ResponseEntity<>("deleted sucessfully", HttpStatus.OK); 
			  else 
				  return new  ResponseEntity<>(HttpStatus.NOT_FOUND); }
		 
		  @GetMapping("/averageRating")
		  public Double getAverageRating(@RequestParam Long companyId)
		  {
			  List<Review> reviewList=reviewService.getAllReviews(companyId);
			  return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
		  }
	 
}
