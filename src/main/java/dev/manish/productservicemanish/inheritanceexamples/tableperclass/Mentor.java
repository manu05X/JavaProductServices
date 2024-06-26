package dev.manish.productservicemanish.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_mentor")
public class Mentor extends User{
    private int numberOfSessions;
    private int numberOfMentees;
}

/*
 * Have all the attributes of parent and its own i.e numberOfSessions and numberOfMentees in the db table
 * */