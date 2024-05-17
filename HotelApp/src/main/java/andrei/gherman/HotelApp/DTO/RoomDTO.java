package andrei.gherman.HotelApp.DTO;

import andrei.gherman.HotelApp.Entity.RoomType;

public class RoomDTO {
    private Long id;
    private int roomNumber;
    private RoomType roomType;
    private Double price;
    private boolean isAvailable;
    private HotelDTO hoteldto;


    public RoomDTO() {
    }

    public RoomDTO(Long id, int roomNumber, RoomType roomType, Double price, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
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
        isAvailable = available;
    }
}
