package com.upgrad.bookmyconsultation.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

//mark this class as an 'entity class'
@Entity
//Use Data annotation to generate boilerplate code
@Data
//Use NoArgsConstructor annotation
@NoArgsConstructor
//create a class name Rating
public class Rating {
    //create a primary key called 'id' of type String
    //initialise id with a UUID using UUID.randomUUID
    @Id
    private String id = UUID.randomUUID().toString();

    //create appointmentId of type String
    private String appointmentId;

    //create doctorId of type String
    private String doctorId;

    //create rating of type Integer
    private Integer Rating;
    // Check with int as well

    //create comments of type String
    private String comments;
    //Set access modifiers for all these members to 'private'
}
