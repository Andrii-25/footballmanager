package com.andrii.footballmanager.service;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.exception.PlayerNotFoundException;
import com.andrii.footballmanager.repo.PlayerRepository;
import com.andrii.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public void transfer(Long playerId, Long teamId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if(optionalPlayer.isPresent() && optionalTeam.isPresent()) {
            Player player = optionalPlayer.get();
            Team team = optionalTeam.get();
            long numberOfMonths = getNumberOfMonths(player.getCareerStartDate());
            long transferCost = getTransferCost(numberOfMonths, player.getAge());
            double commission = getCommission(team.getMoneyBalance(), player.getTeam().getTransferFee());
            long transferSum = transferCost + (long)commission;
            team.setMoneyBalance(team.getMoneyBalance() - transferSum);
            player.getTeam().setMoneyBalance(player.getTeam().getMoneyBalance() + transferSum);
            player.setTeam(optionalTeam.get());
            playerRepository.save(player);
            teamRepository.save(team);
        }
    }

    private long getNumberOfMonths(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return ChronoUnit.MONTHS.between(LocalDate.parse(dateFormat.format(date)).withDayOfMonth(1),
                    LocalDate.parse(dateFormat.format(new Date())).withDayOfMonth(1));
    }

    private long getTransferCost(long numberOfMonths, int age) {
        return numberOfMonths * 100000 / age;
    }

    private double getCommission(double moneyBalance, int transferFee) {
        double transferRate = ((double)transferFee / 100);
        return moneyBalance * (transferRate / 100);
    }
}
