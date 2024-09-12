package com.ravinder.project.simple.crud.repository;

import com.ravinder.project.simple.crud.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
