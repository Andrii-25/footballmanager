package com.andrii.footballmanager.dataseed;

import com.andrii.footballmanager.entity.Player;
import com.andrii.footballmanager.entity.Team;
import com.andrii.footballmanager.repo.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class PlayerDataLoader implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(TeamDataLoader.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
//        loadPlayerData();
    }

    private void loadPlayerData() {
        if(playerRepository.count() == 0) {
//            Player player1 = new Player("Karim", "Benzema", 33, new GregorianCalendar(1993, Calendar.JANUARY, 19));
//            Player player2 = new Player("Lionel", "Messi", 34, new GregorianCalendar(1993, Calendar.JANUARY, 24));
//            Player player3 = new Player("Eden", "Hazard", 30, new GregorianCalendar(1995, Calendar.JANUARY, 7));
//            playerRepository.save(player1);
//            playerRepository.save(player2);
//            playerRepository.save(player3);
        }
        log.info("Count of playerRepository: " + playerRepository.count());
    }
}
