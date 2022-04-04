package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RatingsController {

	@Autowired
	private RatingsService ratingsService;


	//create a post method named submitRatings with return type as ResponseEntity
		//define the method parameter rating of type Rating, use @RequestBody for mapping
	@PostMapping("/ratings")
	public ResponseEntity<Void> submitRatings(@RequestBody Rating rating){

		//submit the ratings
		ratingsService.submitRatings(rating);
		//return http response with status set to OK
		return new ResponseEntity(HttpStatus.OK);
	}


	//Created a method to fetch check if appointment already exits or not, representing rating is already submitted or not
	@GetMapping(value = "/ratings/{appointmentId}")
	public ResponseEntity<Boolean> isNotRated(@PathVariable(name = "appointmentId") String appointmentId) {

		return new ResponseEntity<>(ratingsService.isRated(appointmentId), HttpStatus.OK);
	}

}
