package com.ms.companyms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.companyms.DTO.ReviewMessage;
import com.ms.companyms.company.CompanyService;

@Service
public class ReviewMessageConsumer {

	@Autowired
	private CompanyService companyService;
	
	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage)
	{
		 companyService.updateCompanyRating(reviewMessage);
	}
}
