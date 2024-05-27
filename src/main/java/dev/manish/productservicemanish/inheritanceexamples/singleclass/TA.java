package dev.manish.productservicemanish.inheritanceexamples.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")
@DiscriminatorValue("1")
public class TA extends User {
    private double averageRating;
}

/*
* Have all the attributes of parent and its own i.e averageRating in the db table
* */