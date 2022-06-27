package com.cts.skilltracker.domain;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 */

@Schema(name = "SkillDTO", description = "Placeholder for capturing skills associated with a profile")
public class SkillDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(name = "type", description = "Type of skill", type = "String", required = true, allowableValues = {"TECHNICAL","NON-TECHNICAL"}, example = "TECHNICAL")
	private String type;
	
	@Schema(name = "name", description = "Name of skill", type = "String", required = true, example = "SPRING")
	private String name;
	
	@Schema(name = "level", description = "Expertise level of skill. Valid value between 0 and 20", type = "Integer", required = true, example = "12")
	private Integer level;

	public SkillDTO() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillDTO [type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", level=");
		builder.append(level);
		builder.append("]");
		return builder.toString();
	}

}
