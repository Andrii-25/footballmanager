package com.andrii.footballmanager.repo;

import com.andrii.footballmanager.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
