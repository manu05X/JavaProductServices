package dev.manish.productservicemanish.inheritanceexamples.singleclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}

/*
* Create only 1 table. But in that 1 table in DB it Will insert the data for every object of user as well as its child
* Therefor we need a column that will categorise that this row is for user, mentor, ta, instructor.
* This is done by @DiscriminatorColumn at the top of base class.
* @DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
*
* We also give child class its @DiscriminatorValue("1") to diffrentiate from another childs
* */