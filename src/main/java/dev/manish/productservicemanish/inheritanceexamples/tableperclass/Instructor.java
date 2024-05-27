package dev.manish.productservicemanish.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_instructor")
public class Instructor extends User{
    private boolean isHandsome;
}
/*
 * Have all the attributes of parent and its own i.e isHandsome in the db table
 * */