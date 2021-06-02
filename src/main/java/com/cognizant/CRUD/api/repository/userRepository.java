package com.cognizant.CRUD.api.repository;

import com.cognizant.CRUD.api.domain.user;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<user,Long> {

    user findByid(Long id);
}
