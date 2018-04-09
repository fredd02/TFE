package com.tfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long>{

}
