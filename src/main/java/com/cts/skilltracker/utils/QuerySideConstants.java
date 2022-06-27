package com.cts.skilltracker.utils;

import java.util.ArrayList;
import java.util.List;

public class QuerySideConstants {
	
	/*OpenAPI 3.0 constants*/
	
	public static final String OPEN_API_DEFAULT_PACKAGE = "com.cts.skilltracker.controllers";
	public static final String OPEN_API_GROUP_NAME = "Skill Tracker Query API";
	public static final String OPEN_API_TITLE = "Skill Tracker Restful API";
	public static final String OPEN_API_DESCRIPTION = "REST API Documentation for reference";
	public static final String OPEN_API_TC_URL = "http://springdoc.org";
	public static final String OPEN_API_LICENSE = "Apache 2.0";
	
	/*Endpoint definition*/
	public static final String ADMIN_ROUTE = "/admin";
	public static final String SEARCH_PROFILE_ROUTE = "/{criteria}/{criteriaValue}";
	
	public static final String SEARCH_PROFILE_DESC = "This API is used to search Full Stack Engineer profiles based upon given criteria i.e. Name, Associate Id or, Skill level";
	public static final String CRITERIA_DESC = "This is the search criteria. Valid values - Name, AssociateId, Skill Name";
	public static final String CRITERIA_VALUE_DESC = "This is the value for the search criteria";
	
	
	public static final String CRITERIA_NAME = "name";
	public static final String CRITERIA_ASSOCIATE_ID = "associateid";
	public static final String CRITERIA_SKILL = "skill";
	
	public static final String SKILL_TYPE_TECHNICAL = "TECHNICAL";
	public static final String SKILL_TYPE_NONTECHNICAL = "NON-TECHNICAL";
	
	public static final String SKILL_SPOKEN = "SPOKEN";
	public static final String SKILL_COMMUNICATION = "COMMUNICATION";
	public static final String SKILL_APTITUDE = "APTITUDE";
	
	public static final List<String> criteria = new ArrayList<>();
	
	static {
		criteria.add(CRITERIA_NAME);
		criteria.add(CRITERIA_ASSOCIATE_ID);
		criteria.add(CRITERIA_SKILL);
	}
	
	public static final List<String> nonTechSkills = new ArrayList<>();
	static {
		nonTechSkills.add(SKILL_SPOKEN);
		nonTechSkills.add(SKILL_COMMUNICATION);
		nonTechSkills.add(SKILL_APTITUDE);
	}
	
	/*Error Messages*/
	public static final String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";
	public static final String CRITERIA_NOT_FOUND_MSG = "Invalid Search Criteria";
	public static final String INVALID_EXPERTISE_LEVEL_MSG = "Invalid expertise level";
	public static final String ADD_PROFILE_ERROR_MSG = "Add profile operation failed";
	public static final String INVALID_JSON_MSG = "Malformed JSON request";
	public static final String VALIDATION_ERROR_MSG = "Validation Errors";
	public static final String TYPE_MISMATCH_ERROR_MSG = "Type Mismatch";
	public static final String CONSTRAINT_VIOLATION_ERROR_MSG = "Constraint Violations";
	public static final String MISSING_PARAM_ERROR_MSG = "Missing Parameters";
	public static final String UNSUPPORTED_MEDIA_TYPE_ERROR_MSG = "Unsupported Media Type";
	public static final String METHOD_NOT_FOUND_ERROR_MSG = "Method Not Found";
	public static final String EXCEPTION_MSG = "Error occurred during request processing";

}
