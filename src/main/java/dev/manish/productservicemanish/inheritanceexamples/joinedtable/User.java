package dev.manish.productservicemanish.inheritanceexamples.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}

/**
 * 1 table for each class. Each class' table ONLY ITS OWN
 * attributes. Attributes of parent are accessed via a foreign
 * key to the parent class.
 *
 * */