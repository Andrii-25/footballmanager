package com.andrii.footballmanager.repo;

import com.andrii.footballmanager.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
