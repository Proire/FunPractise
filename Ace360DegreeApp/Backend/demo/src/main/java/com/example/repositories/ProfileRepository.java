package com.example.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.UserProfile;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE UserProfile i SET i.profilePhoto = :profilePhoto WHERE i.id = :id")
	void updateImageDataById(@Param("id") Long id, @Param("profilePhoto") byte[] profilePhoto);

}