package com.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.PharmacyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value="/pharmacy")
@RestController
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;
	

	/**Get Pharmacy Details
	 * 
	 * @param pharmacyId
	 * @param isInActiveFetch
	 * @return Employee
	 */
	@ApiOperation(value="Get Pharmacy Details",tags = "Pharmacy Query")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful Retrieved the Pharmacy details",response = Pharmacy.class) 
	})
	@GetMapping("/details/{pharmid}")
	public ResponseEntity<Pharmacy> getPharmacyById(
			@PathVariable(value="pharmid") Long pharmacyId,
			@RequestParam(value="inInActiveFetch",required = false) boolean isInActiveFetch) {
		return new ResponseEntity<>(this.pharmacyService.getPharmacyById(pharmacyId,isInActiveFetch), HttpStatus.OK);
	}
	
	/**Save Pharmacy Details
	 * 
	 * @param Employee
	 * @return Employee
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value="Save Pharmacy Details",tags = "Pharmacy Command")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful Saved the Pharmacy details",response = Pharmacy.class) 
	})
	@PostMapping("/details")
	public ResponseEntity<Pharmacy> savePharmacy(@RequestBody Pharmacy pharmacy) throws JsonProcessingException {
		return new ResponseEntity<>(this.pharmacyService.savePharmacy(pharmacy),HttpStatus.CREATED);
	}
	
	/**Save Pharmacy Details
	 * 
	 * @param Employee
	 * @return Employee
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value="Update Pharmacy Details",tags = "Pharmacy Command")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful updated the Pharmacy details",response = Pharmacy.class) 
	})
	@PutMapping("/details")
	public ResponseEntity<Pharmacy> editPharmacy(@RequestBody Pharmacy pharmacy) throws JsonProcessingException {
		return new ResponseEntity<>(this.pharmacyService.editPharmacy(pharmacy),HttpStatus.OK);
	}
	
	/**Delete Pharmacy Details
	 * 
	 * @param pharmid
	 * @return String
	 */
	@ApiOperation(value="Delete Pharmacy Details",tags = "Pharmacy Command")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful Deleted the Pharmacy details",response = String.class) 
	})
	@DeleteMapping( path = "/details/{pharmid}",produces = MediaType.TEXT_PLAIN_VALUE )
	public ResponseEntity<String>  deletePharmacy(@PathVariable(value="pharmid") Long pharmacyId) {
		return new ResponseEntity<>(this.pharmacyService.deletePharmacyById(pharmacyId),HttpStatus.OK);
	}
	
}
