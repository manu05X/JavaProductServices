package dev.manish.productservicemanish.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_ta")
public class TA extends User{
    private double averageRating;
}

/*
* Have all the attributes of parent and its own i.e averageRating in the db table
* */