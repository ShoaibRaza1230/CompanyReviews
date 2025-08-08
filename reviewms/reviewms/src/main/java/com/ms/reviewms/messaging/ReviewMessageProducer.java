package com.ms.reviewms.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.ms.reviewms.DTO.ReviewMessage;
import com.ms.reviewms.review.Review;

@Service
public class ReviewMessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
//	public ReviewMessageProducer(RabbitTemplate rabbitTemplate)
//	{
//		this.rabbitTemplate=rabbitTemplate;
//	}
	
	public void sendMessage(Review review)
	{
		ReviewMessage reviewMessage = new ReviewMessage();
		reviewMessage.setId(review.getId());
		reviewMessage.setTitle(review.getTitle());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setCompanyId(review.getCompanyId());
		reviewMessage.setRate(review.getRating());
		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
	}
}
