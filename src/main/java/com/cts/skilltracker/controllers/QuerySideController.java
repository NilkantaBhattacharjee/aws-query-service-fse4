package com.cts.skilltracker.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.skilltracker.domain.Profile;
import com.cts.skilltracker.domain.ProfileSearchRsp;
import com.cts.skilltracker.exceptions.CriteriaNotFoundException;
import com.cts.skilltracker.exceptions.ResourceNotFoundException;
import com.cts.skilltracker.services.iface.IProfileSearchService;
import com.cts.skilltracker.utils.QuerySideConstants;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = QuerySideConstants.ADMIN_ROUTE)
public class QuerySideController {
	
	private static Logger logger = LoggerFactory.getLogger(QuerySideController.class);
	
	@Autowired
	IProfileSearchService searchService;
	
	@Operation(summary = "Search profile", description = QuerySideConstants.SEARCH_PROFILE_DESC, tags = { "query-side-controller" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileSearchRsp.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json")) })
	@GetMapping(value = QuerySideConstants.SEARCH_PROFILE_ROUTE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileSearchRsp> searchProfile(
			@Parameter(description = QuerySideConstants.CRITERIA_DESC, required = true) @PathVariable(value = "criteria") String criteria, 
			@Parameter(description = QuerySideConstants.CRITERIA_VALUE_DESC, required = true) @PathVariable(value = "criteriaValue") String criteriaValue) throws Exception {

		String METHOD = "searchProfile() - ";
		logger.info(METHOD + "Entry -> criteria:: " + criteria + ", criteriaValue:: " + criteriaValue);
		ProfileSearchRsp responseDTO = new ProfileSearchRsp();
		List<Profile> profiles = new ArrayList<>();

		// Add validations here
		if(!QuerySideConstants.criteria.contains(criteria)) {
			throw new CriteriaNotFoundException("Invalid search criteria - " + criteria);
		}

		// Call service layer
		profiles = searchService.searchByCriteriaNameAndValue(criteria, criteriaValue);
		if(profiles!=null && profiles.size() > 0) {
			responseDTO.setProfiles(profiles);
		} else {
			//throw exception
			throw new ResourceNotFoundException("No records found. Please refine your search criteria.");
		}
		logger.info(METHOD + "Exit");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}
	

}
