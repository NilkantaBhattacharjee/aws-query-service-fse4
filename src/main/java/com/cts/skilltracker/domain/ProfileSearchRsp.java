package com.cts.skilltracker.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 */

@Schema(name = "ProfileSearchRsp", description = "Response structure for Search Profile API")
public class ProfileSearchRsp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(name = "profiles", description = "List of profiles returned by search operation", type = "array", required = true)
	private List<Profile> profiles;

	public ProfileSearchRsp() {

	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

}
