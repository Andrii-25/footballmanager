package com.andrii.footballmanager.repo;

import com.andrii.footballmanager.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query(value = "select * from player where team_id = ?1", nativeQuery = true)
    Set<Player> findAllByTeamId(Long team_id);
}
