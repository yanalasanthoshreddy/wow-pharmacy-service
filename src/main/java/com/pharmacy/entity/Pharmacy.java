package com.pharmacy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pharmId;
	
	private String pharmacyName;
	
	private String address;
	
	private String contactNumber;
	
	private LocalDate establishedDate;
	
	private Boolean isClosed;
	
	private LocalDate closedDate;
	
	private String closedReason;

}
