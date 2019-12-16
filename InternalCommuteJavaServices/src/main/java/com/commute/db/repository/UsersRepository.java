package com.commute.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Modifying(clearAutomatically = true)
	@Query(value="update users set first_name= ?2, last_name=?3, email=?4, mobile_number=?5, role=?6, office_address=?7, home_address=?8, "
			+ "available_seats=?9, vehicle=?10 where user_id=?1 ", nativeQuery = true)
	Users saveById(int userId, String firstName, String lastName, String email, String mobileNumber, String role,
			String officeAddress, String homeAddress, String availableSeats, String vehicle);

	@Query(value="select * from users where user_id=?1 limit 1", nativeQuery = true)
	Users findByUserId(String userId);
	
	@Modifying(clearAutomatically = true)
	@Query(value="update users set password=?2 where user_id=?1", nativeQuery = true)
	void updatePasswordByUserId(String userId, String newPassword);

}
