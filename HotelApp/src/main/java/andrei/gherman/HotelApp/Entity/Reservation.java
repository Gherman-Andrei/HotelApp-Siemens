package andrei.gherman.HotelApp.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Nume",nullable = false)
    private String Nume;
    @Column(name = "checkInDate", nullable = false)
    private LocalDateTime checkInDate;
    @Column(name = "checkOutDate", nullable = false)
    private LocalDateTime checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
