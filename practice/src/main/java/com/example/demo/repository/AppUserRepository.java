package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser,Long> {
	Optional<AppUser> findByEmail(String email);
}
