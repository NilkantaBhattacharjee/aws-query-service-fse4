package com.cts.skilltracker.repository;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.skilltracker.entities.ProfileEntity;

@EnableScan
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {

	Optional<ProfileEntity> findByUserId(String userId);

	List<ProfileEntity> findByNameContaining(String name);

	Optional<ProfileEntity> findByAssociateId(String associateId);
	
	@Override
    List<ProfileEntity> findAll();
}
