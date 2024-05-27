package dev.manish.productservicemanish.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
 public class BaseModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
}
//    public Long getId() {
//        return this.id;
//    }
//
//    public Date getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public Date getLastUpdatedAt() {
//        return this.lastUpdatedAt;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setLastUpdatedAt(Date lastUpdatedAt) {
//        this.lastUpdatedAt = lastUpdatedAt;
//    }
//}
