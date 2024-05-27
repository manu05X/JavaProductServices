package dev.manish.productservicemanish.inheritanceexamples.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_user") //as we already have user defined before in mappedsuper module, so we gave a custom name to it
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/*
It will create table for all entites with columns inherited from Parent
* */

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}
