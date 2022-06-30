package com.cts.skilltracker.helpers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.cts.skilltracker.domain.ProfileRsp;
import com.cts.skilltracker.entities.ProfileEntity;
import com.cts.skilltracker.repository.ProfileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SQSConsumer {

	private static Logger logger = LoggerFactory.getLogger(SQSConsumer.class);

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProfileRepository profileRepository;

	@SqsListener("skill-tracker-queue")
	public void receiveMessage(String stringJson) {
		String METHOD = "receiveMessage() - ";
		logger.info(METHOD + "Message Received using SQS Listener:: " + stringJson);
		if (stringJson != null) {
			try {
				ProfileRsp profile = objectMapper.readValue(stringJson, ProfileRsp.class);
				this.saveOrUpdateProfile(profile);
			} catch (JsonMappingException jme) {
				logger.error(METHOD + "Exception occurred:: " + jme.getMessage());
			} catch (JsonProcessingException jpe) {
				logger.error(METHOD + "Exception occurred:: " + jpe.getMessage());
			}
		}
		logger.info(METHOD + "Exit");
	}

	private void saveOrUpdateProfile(ProfileRsp profile) {

		String METHOD = "saveOrUpdateProfile() - ";
		logger.info(METHOD + "Entry -> Profile Details to persist:: " + profile);

		try {

			Optional<ProfileEntity> profileOpt = profileRepository.findByAssociateId(profile.getAssociateId());
			ProfileEntity entity = new ProfileEntity();
			if (profileOpt.isPresent()) {
				// Update profile
				entity = profileOpt.get();
				entity.setSkills(profile.getSkills());
				profileRepository.save(entity);

			} else {
				// Insert profile
				entity.setUserId(profile.getUserId());
				entity.setAssociateId(profile.getAssociateId());
				entity.setName(profile.getName());
				entity.setEmail(profile.getEmail());
				entity.setMobile(profile.getMobile());
				entity.setSkills(profile.getSkills());
				profileRepository.save(entity);
			}

		} catch (Exception ex) {
			logger.error(METHOD + "Exception occurred:: " + ex.getMessage());
		}

		logger.info(METHOD + "Exit");

	}

}
