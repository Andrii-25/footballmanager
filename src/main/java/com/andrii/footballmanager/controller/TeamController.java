package com.andrii.footballmanager.controller;

import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        try {
            return ResponseEntity.ok(teamService.create(team));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong...", e);
        }
    }

    @GetMapping
    public ResponseEntity<Set<Team>> getAll() {
        try {
            return ResponseEntity.ok(teamService.getAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong...", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong...", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team team) {
        try {
            return ResponseEntity.ok(teamService.updateTeam(id, team));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong...", e);
        }
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong...", e);
        }
    }
}