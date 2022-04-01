package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.entity.User;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import com.upgrad.bookmyconsultation.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

	
	
	//mark it autowired
	//create an instance of AppointmentRepository called appointmentRepository
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;


	//create a method name appointment with the return type of String and parameter of type Appointment
	//declare exceptions 'SlotUnavailableException' and 'InvalidInputException'
	public String appointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException{
		String date=appointment.getAppointmentDate().substring(0, appointment.getAppointmentDate().indexOf('T'));
		//validate the appointment details using the validate method from ValidationUtils class
		ValidationUtils.validate(appointment);

		appointment.setCreatedDate(LocalDate.now().toString());

		//find if an appointment exists with the same doctor for the same date and time
		if (appointmentRepository.findByDoctorIdAndTimeSlotAndAppointmentDate(appointment.getDoctorId()
				,appointment.getTimeSlot()
				,date) != null){

			//if the appointment exists throw the SlotUnavailableException
			throw new SlotUnavailableException();
		} else {
			//save the appointment details to the database
			appointment.setAppointmentDate(date);
			appointmentRepository.save(appointment).getAppointmentId();
			//return the appointment id
			return appointment.getAppointmentId();
		}
	}

//	public User getUser(String id) {
//		return Optional.ofNullable(userRepository.findById(id))
//				.get()
//				.orElseThrow(ResourceUnAvailableException::new);
//	} by me

	//create a method getAppointment of type Appointment with a parameter name appointmentId of type String
	public Appointment getAppointment(String appointmentId){

		//Use the appointmentid to get the appointment details
		return Optional.ofNullable(appointmentRepository.findById(appointmentId).get())
				.orElseThrow(ResourceUnAvailableException :: new);
		//if the appointment exists return the appointment
		//else throw ResourceUnAvailableException
		//tip: use Optional.ofNullable(). Use orElseThrow() method when Optional.ofNullable() throws NULL
	}

	
	public List<Appointment> getAppointmentsForUser(String userId) {
		return appointmentRepository.findByUserId(userId);
	}
}
