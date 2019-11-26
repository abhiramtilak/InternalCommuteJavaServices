package com.commute.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.db.model.Users;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, String>{

	@Query(value="select * from users where email=?1 or mobile_number=?2 limit 1", nativeQuery = true)
	Users findByemailormobileNumber(String email, String mobileNumber);

	@Query(value="select * from users where email=?1 and password=?2 limit 1", nativeQuery = true)
	Users findByEmailandPassword(String email, String password);

}
