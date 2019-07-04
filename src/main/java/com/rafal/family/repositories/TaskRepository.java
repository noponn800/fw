package com.rafal.family.repositories;

import com.rafal.family.model.ApplicationTask;
import com.rafal.family.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<ApplicationTask, Long> {
}
