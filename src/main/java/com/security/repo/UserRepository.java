package com.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.models.DAOUser;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long>{
	DAOUser findByUsername(String username);
}
