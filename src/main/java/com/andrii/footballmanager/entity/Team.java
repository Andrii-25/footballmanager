package com.andrii.footballmanager.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "transfer_fee")
    private int transferFee;

    @Column(name = "money_balance")
    private long moneyBalance;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Player> players = new HashSet<>();

    public long getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(long moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(int transferFee) {
        this.transferFee = transferFee;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;

        for(Player p : players) {
            p.setTeam(this);
        }
    }
}
