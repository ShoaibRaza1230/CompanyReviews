package com.ms.jobms.clients;

import java.util.List;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.jobms.job.external.Review;

@FeignClient(name="reviewms",
			   url="${reviewms.url}")
public interface ReviewsClient {

	@GetMapping("/reviews")
	
	List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
