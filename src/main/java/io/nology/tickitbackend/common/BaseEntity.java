package io.nology.tickitbackend.common;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt; 

    @Temporal(TemporalType.TIMESTAMP)
    @Column 
    private Date updatedAt;
    
    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void onCreate() {
        Date timestamp = new Date();
        createdAt = timestamp;
        updatedAt = timestamp;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Date();
    }
}
