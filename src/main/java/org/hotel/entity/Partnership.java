package org.hotel.entity;

import org.hotel.key.PartnershipKey;

import jakarta.persistence.*;


@Entity
@Table(name = "partnership")
public class Partnership {
    @EmbeddedId
    PartnershipKey id;
    @ManyToOne
    @MapsId("agencyId")
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @ManyToOne
    @MapsId("hotelId")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "percentage")
    private float percentage;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Partnership other = (Partnership) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
