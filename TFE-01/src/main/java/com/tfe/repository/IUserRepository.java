package com.tfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	@Query(value="SELECT * FROM USERS u JOIN USER_ROLE ur ON u.id = ur.user_id JOIN ROLE r ON ur.role_id=r.id WHERE u.username=?1", nativeQuery=true)
	User findByUsernameWithRoles(String username);

}
