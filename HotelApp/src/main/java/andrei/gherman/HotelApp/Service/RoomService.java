package andrei.gherman.HotelApp.Service;

import andrei.gherman.HotelApp.DTO.RoomDTO;
import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Entity.RoomType;
import andrei.gherman.HotelApp.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAvailableRooms(RoomType type, Double minPrice, Double maxPrice, Long hotelId) {
        return roomRepository.findByRoomTypeAndPriceBetweenAndHotel_IdAndIsAvailableTrue(type, minPrice, maxPrice, hotelId);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room getRoomById(Long id, Long roomId) {
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setRoomType(roomDetails.getType());
        room.setPrice(roomDetails.getPrice());
        room.setAvailable(roomDetails.isAvailable());
        return roomRepository.save(room);
    }




}