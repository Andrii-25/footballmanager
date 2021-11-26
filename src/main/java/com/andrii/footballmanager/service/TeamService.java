package com.andrii.footballmanager.service;

import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.exception.TeamNotFoundException;
import com.andrii.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team getOneTeam(int id) throws TeamNotFoundException {
        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new TeamNotFoundException("Team not found!");
        }
    }

    public Team updateTeam(Team team, int id) throws TeamNotFoundException {
        Optional<Team> teamToUpdate = teamRepository.findById(id);
        if(teamToUpdate.isPresent()) {
            Team updatedTeam = new Team();
            updatedTeam.setName(team.getName());
            updatedTeam.setCity(team.getCity());
            updatedTeam.setCountry(team.getCountry());
            updatedTeam.setPlayerList(team.getPlayerList());
            updatedTeam.setTransferFee(team.getTransferFee());
            return updatedTeam;
        } else {
            throw new TeamNotFoundException("Team not found!");
        }
    }

    public void deleteTeam(int id) {
        teamRepository.deleteById(id);
    }
}
