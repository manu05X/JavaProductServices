package dev.manish.productservicemanish.inheritanceexamples.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentor")
@DiscriminatorValue("2")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}

/*
 * Have all the attributes of parent and its own i.e numberOfSessions and numberOfMentees in the db table
 * */