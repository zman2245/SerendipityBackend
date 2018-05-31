package com.serendipity.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Memory")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long id;

    @Column
    private String message;

    @Column
    private String imageLink;

    @Column
    private String artworkLink;

    @Column
    private int bgColor;

    @Column
    private Integer creator;

    @Column
    private Integer owner;

    @ManyToOne
    @JoinColumn(name="currentHotspot", nullable=true)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    private Hotspot currentHotspot;

    @ManyToOne
    @JoinColumn(name="originalHotspot", nullable=true)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    private Hotspot originalHotspot;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getArtworkLink() {
        return artworkLink;
    }

    public void setArtworkLink(String artworkLink) {
        this.artworkLink = artworkLink;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Hotspot getCurrentHotspot() {
        return currentHotspot;
    }

    public void setCurrentHotspot(Hotspot currentHotspot) {
        this.currentHotspot = currentHotspot;
    }

    public Hotspot getOriginalHotspot() {
        return originalHotspot;
    }

    public void setOriginalHotspot(Hotspot originalHotspot) {
        this.originalHotspot = originalHotspot;
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
