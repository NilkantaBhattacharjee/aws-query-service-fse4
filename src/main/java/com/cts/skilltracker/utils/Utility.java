package com.cts.skilltracker.utils;

import java.util.Map;
import java.util.stream.Collectors;

import com.cts.skilltracker.domain.SkillDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Utility {

	public static List<SkillDTO> mapToSortedList(Map<String, Integer> skillMap) {

		List<SkillDTO> skillList = new ArrayList<>();

		if (skillMap != null) {
			for (Map.Entry<String, Integer> entry : skillMap.entrySet()) {
				SkillDTO skill = new SkillDTO();
				skill.setType(QuerySideConstants.nonTechSkills.contains(entry.getKey())
						? QuerySideConstants.SKILL_TYPE_NONTECHNICAL
						: QuerySideConstants.SKILL_TYPE_TECHNICAL);
				skill.setName(entry.getKey());
				skill.setLevel(entry.getValue());
				skillList.add(skill);
			}

			// Sort list based on level
			skillList = skillList.stream().sorted(Comparator.comparingInt(SkillDTO::getLevel).reversed())
					.collect(Collectors.toList());

		}

		return skillList;

	}
	
	public static List<SkillDTO> mapToFilteredList(Map<String, Integer> skillMap, String skillName) {

		List<SkillDTO> skillList = new ArrayList<>();

		if (skillMap != null) {
			for (Map.Entry<String, Integer> entry : skillMap.entrySet()) {
				SkillDTO skill = new SkillDTO();
				skill.setType(QuerySideConstants.nonTechSkills.contains(entry.getKey())
						? QuerySideConstants.SKILL_TYPE_NONTECHNICAL
						: QuerySideConstants.SKILL_TYPE_TECHNICAL);
				skill.setName(entry.getKey());
				skill.setLevel(entry.getValue());
				skillList.add(skill);
			}

			// Filter list based on skill and level
			skillList = skillList.stream().filter(x -> x.getName().equalsIgnoreCase(skillName) && x.getLevel() > 10)
					.collect(Collectors.toList());

		}

		return skillList;

	}

}
