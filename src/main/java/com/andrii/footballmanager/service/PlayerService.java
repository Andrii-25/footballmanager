package com.andrii.footballmanager.service;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.exception.PlayerNotFoundException;
import com.andrii.footballmanager.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    public Player getOnePlayer(int id) throws PlayerNotFoundException {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException("Player not found!");
        }
    }

    public Player updatePlayer(Player player, int id) throws PlayerNotFoundException {
        Optional<Player> playerToUpdate = playerRepository.findById(id);
        if(playerToUpdate.isPresent()) {
            Player updatedPlayer = playerToUpdate.get();
            updatedPlayer.setName(player.getName());
            updatedPlayer.setName(player.getSurname());
            updatedPlayer.setAge(player.getAge());
            updatedPlayer.setCareerStartDate(player.getCareerStartDate());
            updatedPlayer.setTeam(player.getTeam());
            return updatedPlayer;
        } else {
            throw new PlayerNotFoundException("Player not found!");
        }
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }
}
