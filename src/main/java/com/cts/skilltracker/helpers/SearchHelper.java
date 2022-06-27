package com.cts.skilltracker.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.skilltracker.domain.Profile;
import com.cts.skilltracker.domain.SkillDTO;
import com.cts.skilltracker.entities.ProfileEntity;
import com.cts.skilltracker.repository.ProfileRepository;
import com.cts.skilltracker.utils.QuerySideConstants;
import com.cts.skilltracker.utils.Utility;

@Component
public class SearchHelper {

	private static Logger logger = LoggerFactory.getLogger(SearchHelper.class);

	@Autowired
	ProfileRepository profileRepository;

	public List<Profile> searchByCriteria(String criteria, String value) {

		String METHOD = "searchByCriteria() - ";
		logger.info(METHOD + "Entry -> criteria:: " + criteria + ", value:: " + value);

		List<Profile> profiles = null;

		switch (criteria) {
		case QuerySideConstants.CRITERIA_NAME:
			profiles = searchByName(value);
			break;
		case QuerySideConstants.CRITERIA_ASSOCIATE_ID:
			profiles = searchByAssociateId(value);
			break;
		case QuerySideConstants.CRITERIA_SKILL:
			profiles =  searchBySkill(value);
			break;
		default:
			profiles = null;
			break;

		}
		logger.info(METHOD + "Exit");
		return profiles;

	}

	private List<Profile> searchByName(String value) {

		String METHOD = "searchByName() - ";
		logger.info(METHOD + "Entry -> value:: " + value);
		List<Profile> profiles = new ArrayList<>();
		List<ProfileEntity> rows = profileRepository.findByNameContaining(value);

		if (rows != null && rows.size() > 0) {
			for (ProfileEntity row : rows) {
				Profile profile = new Profile();
				profile.setUserId(row.getUserId());
				profile.setAssociateId(row.getAssociateId());
				profile.setName(row.getName());
				profile.setEmail(row.getEmail());
				profile.setMobile(row.getMobile());
				profile.setSkills(Utility.mapToSortedList(row.getSkills()));
				profiles.add(profile);
			}

		}

		logger.info(METHOD + "Exit");
		return profiles;

	}

	private List<Profile> searchByAssociateId(String value) {

		String METHOD = "searchByAssociateId() - ";
		logger.info(METHOD + "Entry -> value:: " + value);
		List<Profile> profiles = new ArrayList<>();

		Optional<ProfileEntity> profileOpt = profileRepository.findByAssociateId(value);

		if (profileOpt.isPresent()) {
			ProfileEntity row = profileOpt.get();
			Profile profile = new Profile();
			profile.setUserId(row.getUserId());
			profile.setAssociateId(row.getAssociateId());
			profile.setName(row.getName());
			profile.setEmail(row.getEmail());
			profile.setMobile(row.getMobile());
			profile.setSkills(Utility.mapToSortedList(row.getSkills()));
			profiles.add(profile);
		}

		logger.info(METHOD + "Exit");
		return profiles;

	}
	
	private List<Profile> searchBySkill(String value) {
		
		String METHOD = "searchBySkill() - ";
		logger.info(METHOD + "Entry -> value:: " + value);
		List<Profile> profiles = new ArrayList<>();
		
		List<ProfileEntity> rows = profileRepository.findAll();
		
		if (rows != null && rows.size() > 0) {
			for (ProfileEntity row : rows) {
				List<SkillDTO> skillList = new ArrayList<>();
				skillList = Utility.mapToFilteredList(row.getSkills(), value);
				if(skillList!=null && skillList.size() > 0) {
					Profile profile = new Profile();
					profile.setUserId(row.getUserId());
					profile.setAssociateId(row.getAssociateId());
					profile.setName(row.getName());
					profile.setEmail(row.getEmail());
					profile.setMobile(row.getMobile());
					profile.setSkills(skillList);
					profiles.add(profile);
				}
				
			}

		}
		
		logger.info(METHOD + "Exit");
		return profiles;
	}

}
