package com.rafal.family.repositories;

import com.rafal.family.model.ApplicationGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<ApplicationGroup,Long>
{
    //repository
}
