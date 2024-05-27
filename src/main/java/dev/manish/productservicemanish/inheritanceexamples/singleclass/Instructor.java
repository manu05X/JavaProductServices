package dev.manish.productservicemanish.inheritanceexamples.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_instructor")
@DiscriminatorValue("3")
public class Instructor extends User {
    private boolean isHandsome;
}
/*
 * Have all the attributes of parent and its own i.e isHandsome in the db table
 * */