package com.cts.skilltracker.handlers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.skilltracker.domain.ProfileRsp;
import com.cts.skilltracker.entities.ProfileEntity;
import com.cts.skilltracker.repository.ProfileRepository;

@Component
public class RabbitMqReceiver {
	
	@Autowired
	ProfileRepository profileRepository;
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(ProfileRsp profile) {
		String METHOD = "receivedMessage() - ";
        logger.info(METHOD + "Entry -> Profile Details Received is:: " + profile);
        if(profile!=null) {
        	this.saveOrUpdateProfile(profile);
        }
        
        logger.info(METHOD + "Exit");
    }
	
	private void saveOrUpdateProfile(ProfileRsp profile) {
		
		String METHOD = "saveOrUpdateProfile() - ";
		 logger.info(METHOD + "Entry -> Profile Details to persist:: " + profile);
		 
		 try {
			 
			 Optional<ProfileEntity> profileOpt = profileRepository.findByAssociateId(profile.getAssociateId());
			 ProfileEntity entity = new ProfileEntity();
			 if(profileOpt.isPresent()) {
				 //Update profile
				 entity = profileOpt.get();
				 entity.setSkills(profile.getSkills());
				 profileRepository.save(entity);
				
			 } else {
				 //Insert profile
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
