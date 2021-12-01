package com.andrii.footballmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory!")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname is mandatory!")
    @Size(min = 3, max = 30, message = "Surname should be between 3 and 30 characters!")
    @Column(name = "surname")
    private String surname;

    @Min(value = 0, message = "Age should be greater than 0!")
    @Max(value = 200, message = "Age should be less than 200!")
    @Column(name = "age")
    private int age;

    @Column(name = "career_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date careerStartDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Team team;

    public Player() {
    }

    public Player(String name, String surname, int age, Date careerStartDate, Team team) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.careerStartDate = careerStartDate;
        this.team = team;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCareerStartDate() {
        return careerStartDate;
    }

    public void setCareerStartDate(Date careerStartDate) {
        this.careerStartDate = careerStartDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
