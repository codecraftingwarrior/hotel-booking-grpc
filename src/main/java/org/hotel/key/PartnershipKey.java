package org.hotel.key;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PartnershipKey implements Serializable {
    @Column(name = "room_id")
    private Long agencyId;

    @Column(name = "client_id")
    private Long hotelId;

    public PartnershipKey() {

    }

    public PartnershipKey(Long hotelId, Long agencyId) {
        this.agencyId = agencyId;
        this.hotelId = hotelId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.agencyId == null) ? 0 : this.agencyId.hashCode());
        result = prime * result + ((this.hotelId == null) ? 0 : this.hotelId.hashCode());
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
        PartnershipKey other = (PartnershipKey) obj;
        if (agencyId == null) {
            if (other.agencyId != null)
                return false;
        } else if (!agencyId.equals(other.agencyId))
            return false;
        if (hotelId == null) {
            return other.hotelId == null;
        } else return hotelId.equals(other.hotelId);
    }
}
