package dev.manish.productservicemanish.inheritanceexamples.joinedtable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private boolean isHandsome;
}
/**
 * As every class will have its own table and id of its parent
 * i.e user here and its denoted by @PrimaryKeyJoinColumn(name = "user_id")
 * */