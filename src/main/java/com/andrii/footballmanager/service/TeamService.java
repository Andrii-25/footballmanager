package com.andrii.footballmanager.service;

import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.exception.TeamNotFoundException;
import com.andrii.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team create(Team team) {
        return teamRepository.save(team);
    }

    public Set<Team> getAll() {
        Set<Team> teams = new HashSet<>();
        for(Team t : teamRepository.findAll()) {
            teams.add(t);
        }
        return teams;
    }

    public Team getById(Long id) throws TeamNotFoundException {
        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new TeamNotFoundException("Team not found!");
        }
    }

    public Team updateTeam(Long id, Team team) throws TeamNotFoundException {
        Optional<Team> teamToUpdate = teamRepository.findById(id);
        if(teamToUpdate.isPresent()) {
            team.setId(teamToUpdate.get().getId());
            return teamRepository.save(team);
        } else {
            throw new TeamNotFoundException("Team not found!");
        }
    }

    public void deleteTeam(Long id) throws TeamNotFoundException {
        Optional<Team> teamToDelete = teamRepository.findById(id);
        if(teamToDelete.isPresent()) {
            teamRepository.delete(teamToDelete.get());
        } else {
            throw new TeamNotFoundException("Team not found!");
        }
    }
}
