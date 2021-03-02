package com.pharmacy.service;

import java.util.Objects;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.repository.PharmacyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyService {

	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	/**
	 * Below service fetch the either active/inactive pharmacy records
	 * 
	 * @param pharmId
	 * @param isInActiveFetch
	 * @return Pharmacy
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW ,readOnly = true)
	public Pharmacy getPharmacyById(Long pharmId,boolean isInActiveFetch) {
		log.info("Pharmacy :: Get :: Input Request Pharma Id :{} and isInActiveFetch :{}",pharmId,isInActiveFetch);
		Pharmacy pharmacy=this.pharmacyRepository.findById(pharmId).orElse(null);
		if(Objects.nonNull(pharmacy) && BooleanUtils.isTrue(pharmacy.getIsClosed()) && !isInActiveFetch) {
			log.info("Pharmacy:: Condition Failed to fetch successful data");
			return null;
		}
		return pharmacy;
	}
	
	
	/**
	 * Below service save the pharmacy record
	 * 
	 * @param Pharmacy
	 * @return Pharmacy
	 * @throws JsonProcessingException 
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW ,readOnly = false)
	public Pharmacy savePharmacy(Pharmacy pharmacy) throws JsonProcessingException {
		log.info("Pharmacy:: Save :: Input Request :{}",this.objectMapper.writeValueAsString(pharmacy));
		return this.pharmacyRepository.save(pharmacy);
	}
	
	
	/**
	 * Below service update the pharmacy records
	 * 
	 * @param pharmId
	 * @param isInActiveFetch
	 * @return Pharmacy
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW ,readOnly = false)
	public Pharmacy editPharmacy(Pharmacy pharmacy) throws JsonProcessingException {
		log.info("Pharmacy:: Update :: Input Request :{}",this.objectMapper.writeValueAsString(pharmacy));
		return this.pharmacyRepository.save(pharmacy);
	}
	
	
	/**
	 * Below service delete the pharmacy records
	 * 
	 * @param pharmId
	 * @param isInActiveFetch
	 * @return Pharmacy
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW ,readOnly = true)
	public String deletePharmacyById(Long pharmId) {
		log.info("Pharmacy ::Delete :: Input Request Pharma Id :{} and isInActiveFetch :{}",pharmId);
		this.pharmacyRepository.deleteById(pharmId);
		return "Successfully deleted the pharmacy record for id :"+pharmId;
	}
	
	
	
}
