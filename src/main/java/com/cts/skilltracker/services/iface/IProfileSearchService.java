package com.cts.skilltracker.services.iface;

import java.util.List;

import com.cts.skilltracker.domain.Profile;

public interface IProfileSearchService {
	
	public List<Profile> searchByCriteriaNameAndValue(String criteria, String value);

}
