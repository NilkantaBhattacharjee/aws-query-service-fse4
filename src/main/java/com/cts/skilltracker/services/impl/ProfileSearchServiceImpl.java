package com.cts.skilltracker.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.skilltracker.domain.Profile;
import com.cts.skilltracker.helpers.SearchHelper;
import com.cts.skilltracker.services.iface.IProfileSearchService;

@Service
public class ProfileSearchServiceImpl implements IProfileSearchService{
	
	private static Logger logger = LoggerFactory.getLogger(ProfileSearchServiceImpl.class);
	
	@Autowired
	SearchHelper helper;

	@Override
	public List<Profile> searchByCriteriaNameAndValue(String criteria, String value) {
		
		String METHOD = "searchByCriteriaNameAndValue() - ";
		logger.info(METHOD + "Entry ->criteria:: " + criteria + ", value:: " + value);
		
		List<Profile> profiles = null;
		
		try {
			profiles = helper.searchByCriteria(criteria, value);
		} catch (Exception ex) {
			logger.error(METHOD + "Exception occurred:: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		logger.info(METHOD + "Exit");
		return profiles;
	}

}
