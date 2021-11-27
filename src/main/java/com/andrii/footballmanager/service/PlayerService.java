package com.andrii.footballmanager.service;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.exception.PlayerNotFoundException;
import com.andrii.footballmanager.repo.PlayerRepository;
import com.andrii.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Player create(Player player) {
        Optional<Team> optionalTeam = teamRepository.findById(player.getTeam().getId());
        optionalTeam.ifPresent(player::setTeam);
        return playerRepository.save(player);
    }

    public Set<Player> getAll() {
        Set<Player> players = new HashSet<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    public Player getById(Long id) throws PlayerNotFoundException {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException("Player not found!");
        }
    }

    public Player update(Long id, Player player) throws PlayerNotFoundException {
        Optional<Team> optionalTeam = teamRepository.findById(player.getTeam().getId());
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if(optionalTeam.isPresent() && optionalPlayer.isPresent()) {
            player.setTeam(optionalTeam.get());
            player.setId(optionalPlayer.get().getId());
            return playerRepository.save(player);
        } else {
            throw new PlayerNotFoundException("Player not found!");
        }
    }

    public void delete(Long id) throws PlayerNotFoundException {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if(optionalPlayer.isPresent()) {
            playerRepository.delete(optionalPlayer.get());
        } else {
            throw new PlayerNotFoundException("Player not found!");
        }
    }
}
