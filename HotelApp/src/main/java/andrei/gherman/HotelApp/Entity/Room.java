package andrei.gherman.HotelApp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "roomNumber", nullable = false)
    private Integer roomNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "roomType", nullable = false)
    private RoomType roomType;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "Available", nullable = false)
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;



    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RoomType getType() {
        return roomType;
    }
    public void setType(RoomType roomType) {
        this.roomType = roomType;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
