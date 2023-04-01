package org.hotel.key;

import org.hibernate.action.internal.DelayedPostInsertIdentifier;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class BookingKey implements Serializable {
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "client_id")
    private Long clientId;

    public BookingKey() {
    }

    public BookingKey(Long roomId, Long clientId) {
        this.roomId = roomId;
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
        result = prime * result + ((this.roomId == null) ? 0 : this.roomId.hashCode());
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
        BookingKey other = (BookingKey) obj;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (roomId == null) {
            return other.roomId == null;
        } else return roomId.equals(other.roomId);
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }
}

