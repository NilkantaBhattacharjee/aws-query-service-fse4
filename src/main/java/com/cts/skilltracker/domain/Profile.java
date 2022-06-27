package com.cts.skilltracker.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 */

@Schema(name = "Profile", description = "Placeholder for message structure from Rabbit MQ")
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(name = "userId", description = "Unique Id of profile", type = "String", required = true, example = "578cb3d2-159e-4dba-9bcf-8d37943e4835")
	private String userId;
	
	@Schema(name = "associateId", description = "Associate Id of profile", type = "String", minimum = "5", maximum = "30", required = true, example = "CTS001")
	private String associateId;
	
	@Schema(name = "name", description = "Name of profile", type = "String", minimum = "5", maximum = "30", required = true, example = "John Doe")
	private String name;
	
	@Schema(name = "email", description = "Email adrdess of profile", type = "String", required = true, example = "john.doe@cognizant.com")
	private String email;
	
	@Schema(name = "mobile", description = "Mobile number of profile", type = "String", minimum = "10", maximum = "10", required = true, example = "1234567890")
	private String mobile;
	
	@Schema(name = "skills", description = "Technical and non-technical skills associated with profile", type = "array", required = true)
	private List<SkillDTO> skills;

	public Profile() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDTO> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [userId=");
		builder.append(userId);
		builder.append(", associateId=");
		builder.append(associateId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", skills=");
		builder.append(skills);
		builder.append("]");
		return builder.toString();
	}

}
