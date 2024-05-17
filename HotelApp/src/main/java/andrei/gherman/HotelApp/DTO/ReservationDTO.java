package andrei.gherman.HotelApp.DTO;


import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private String Nume;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Long roomId;


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
        LocalDateTime from = LocalDateTime.from(checkInDate);
        return from;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        LocalDateTime from = LocalDateTime.from(checkOutDate);
        return from;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
