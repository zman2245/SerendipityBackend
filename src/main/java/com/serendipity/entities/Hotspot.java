package com.serendipity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Hotspot")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class Hotspot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long id;

    @Column
    private String title;

    @Column
    private String about;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private Integer radiusInFeet;

    @Column
    private Integer creator;

    @OneToMany(mappedBy = "currentHotspot")
    private Set<Memory> memories;

    @OneToMany(mappedBy = "originalHotspot")
    private Set<Memory> originalMemories;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getRadiusInFeet() {
        return radiusInFeet;
    }

    public void setRadiusInFeet(Integer radiusInFeet) {
        this.radiusInFeet = radiusInFeet;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Set<Memory> getMemories() {
        return memories;
    }

    public void setMemories(Set<Memory> memories) {
        this.memories = memories;
    }

    public Set<Memory> getOriginalMemories() {
        return originalMemories;
    }

    public void setOriginalMemories(Set<Memory> originalMemories) {
        this.originalMemories = originalMemories;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
