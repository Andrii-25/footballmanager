package com.andrii.footballmanager.dataseed;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.repo.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            loadTeamData();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void loadTeamData() throws ParseException {
        if(teamRepository.count() == 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Team realMadrid = new Team("Real Madrid", "Madrid", "Spain", 5, 100000000);
            Player player1 = new Player("Karim", "Benzema", 33, sdf.parse("1993-01-01"), realMadrid);
            Player player2 = new Player("Eden", "Hazard", 30, sdf.parse("1995-01-01"), realMadrid);
            Set<Player> players1 = new HashSet<>();
            players1.add(player1);
            players1.add(player2);
            realMadrid.setPlayers(players1);

            Team barcelona = new Team("Barcelona", "Barcelona", "Spain", 7, 100000000);
            Player player3 = new Player("Lionel", "Messi", 33, sdf.parse("1992-01-01"), barcelona);
            Player player4 = new Player("Gerard", "Pique", 34, sdf.parse("1992-01-01"), barcelona);
            Set<Player> players2 = new HashSet<>();
            players1.add(player3);
            players1.add(player4);
            barcelona.setPlayers(players2);

            Set<Team> teams = new HashSet<>();
            teams.add(realMadrid);
            teams.add(barcelona);
            teamRepository.saveAll(teams);
        }
        log.info("Count of teamRepository: " + teamRepository.count());
    }
}
