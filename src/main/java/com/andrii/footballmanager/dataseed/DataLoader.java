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
            Player player1 = new Player("Karim", "Benzema", 33, sdf.parse("2004-01-01"), realMadrid);
            Player player2 = new Player("Eden", "Hazard", 30, sdf.parse("2007-01-01"), realMadrid);
            Set<Player> players1 = new HashSet<>();
            players1.add(player1);
            players1.add(player2);
            realMadrid.setPlayers(players1);

            Team barcelona = new Team("Barcelona", "Barcelona", "Spain", 7, 100000000);
            Player player3 = new Player("Gerard", "Pique", 34, sdf.parse("2004-01-01"), barcelona);
            Set<Player> players2 = new HashSet<>();
            players2.add(player3);
            barcelona.setPlayers(players2);

            Team psg = new Team("Paris Saint Germain", "Paris", "France", 4, 100000000);
            Player player4 = new Player("Lionel", "Messi", 34, sdf.parse("2004-01-01"), psg);
            Player player5 = new Player("Kylian", "Mbappe", 22, sdf.parse("2015-01-01"), psg);
            Set<Player> players3 = new HashSet<>();
            players3.add(player4);
            players3.add(player5);
            psg.setPlayers(players3);

            Set<Team> teams = new HashSet<>();
            teams.add(realMadrid);
            teams.add(barcelona);
            teamRepository.saveAll(teams);
        }
        log.info("Count of teamRepository: " + teamRepository.count());
    }
}
