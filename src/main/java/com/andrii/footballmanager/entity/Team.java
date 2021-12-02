package com.andrii.footballmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory!")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters!")
    private String name;

    @NotEmpty(message = "City is mandatory!")
    @Size(min = 3, max = 30, message = "City should be between 3 and 30 characters!")
    private String city;

    @NotEmpty(message = "Country is mandatory!")
    @Size(min = 3, max = 30, message = "Country should be between 3 and 30 characters!")
    private String country;

    @Min(value = 0, message = "Transfer fee should be greater than 0!")
    @Max(value = 10, message = "Transfer fee should be less than 10!")
    private int transferFee;

    @DecimalMin(value = "0.0", message = "Transfer fee should be greater than 0!")
    @DecimalMax(value = "1000000000", message = "Transfer fee should be less than 1000000000!")
    private double moneyBalance;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Player> players = new HashSet<>();

    public Team() {
    }

    public Team(String name, String city, String country, int transferFee, double moneyBalance, Set<Player> players) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.transferFee = transferFee;
        this.moneyBalance = moneyBalance;
        this.players = players;
    }

    public Team(String name, String city, String country, int transferFee, double moneyBalance) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.transferFee = transferFee;
        this.moneyBalance = moneyBalance;
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
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
