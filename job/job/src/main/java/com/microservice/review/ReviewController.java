package com.microservice.review;

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

import com.microservice.company.Company;
import com.microservice.company.CompanyService;

@RestController
@RequestMapping("/companies/{companyId}/")
public class ReviewController {

	
	  @Autowired 
	  private ReviewService reviewService;  
	   
	  @GetMapping("/getAllReviews")
	  public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
	  return new ResponseEntity<>(reviewService.getAllReviews(companyId),
	  HttpStatus.OK);   }
	 
	
	   
	  
	  
	  
	  @PostMapping("/addReview") 
	  public ResponseEntity<String> addCompany(@PathVariable Long companyId, @RequestBody Review review)
	  {
		 boolean check= reviewService.addReview(companyId,review); 
		 if(check)
			 return new ResponseEntity<>("Review added Sucessfully",HttpStatus.OK);
		 else
			  return new ResponseEntity<>("Operation failed",HttpStatus.NOT_FOUND);

	  }
	  
	  @GetMapping("/getReview/{reviewId}")
	  public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId)
	  {
		  return new ResponseEntity<> (reviewService.getReview(companyId, reviewId),HttpStatus.OK);
	  }
		  
		
		  @PutMapping("/updateReview/{reviewId}") 
		  public ResponseEntity<String> updateCompany(@PathVariable Long companyId,@PathVariable Long reviewId ,@RequestBody Review review) {
			  boolean updated = reviewService.updateReview(companyId,reviewId ,review); 
			  if (updated) 
				  return new ResponseEntity<String>("Updated successfulyy", HttpStatus.OK); 
			  else 
				  return new ResponseEntity<String>(HttpStatus.NOT_FOUND); }
		 
		
		  @DeleteMapping("/deleteReview/{reviewId}") 
		  public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId) {
			  boolean del = reviewService.deleteReview(companyId,reviewId); 
			  if (del)
				  return new ResponseEntity<>("deleted sucessfully", HttpStatus.OK); 
			  else 
				  return new  ResponseEntity<>(HttpStatus.NOT_FOUND); }
		 
	 
}
