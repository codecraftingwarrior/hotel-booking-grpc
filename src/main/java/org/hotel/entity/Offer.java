package org.hotel.entity;

import org.hotel.enums.OfferState;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    private OfferState state;
    private Date fromDate;
    private Date toDate;
    private int lodgedPeopleCount;
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;


    @ManyToMany
    @JoinTable(
            name = "offer_room",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    Set<Room> rooms;

    public Long getId() {
        return id;
    }

    public OfferState getState() {
        return state;
    }

    public void setState(OfferState state) {
        this.state = state;
    }

    public Agency getAgency() {
        return this.agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getLodgedPeopleCount() {
        return lodgedPeopleCount;
    }

    public void setLodgedPeopleCount(int lodgedPeopleCount) {
        this.lodgedPeopleCount = lodgedPeopleCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Room> getRooms() {
        return rooms;
    }
}
