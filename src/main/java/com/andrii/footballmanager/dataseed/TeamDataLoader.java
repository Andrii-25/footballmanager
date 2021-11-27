package com.andrii.footballmanager.dataseed;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.repo.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TeamDataLoader implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(TeamDataLoader.class);

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
//        loadTeamData();
    }

    private void loadTeamData() {
        if(teamRepository.count() == 0) {
//            Team realMadrid = new Team("Real Madrid", "Madrid", "Spain", 5);
//            Team barcelona = new Team("Barcelona", "Barcelona", "Spain", 7);
//            Team valencia = new Team("Valencia", "Valencia", "Spain", 3);
//            teamRepository.save(realMadrid);
//            teamRepository.save(barcelona);
//            teamRepository.save(valencia);
        }
        log.info("Count of teamRepository: " + teamRepository.count());
    }
}
