package com.rafal.family.repositories;

import com.rafal.family.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
}
