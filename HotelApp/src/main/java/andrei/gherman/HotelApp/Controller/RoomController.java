package andrei.gherman.HotelApp.Controller;

import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Entity.RoomType;
import andrei.gherman.HotelApp.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/filter")
    public List<Room> getAvailableRooms(@RequestParam RoomType type,
                                        @RequestParam Double minPrice,
                                        @RequestParam Double maxPrice,
                                        @RequestParam Long hotelId) {
        return roomService.findAvailableRooms(type, minPrice, maxPrice, hotelId);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        return roomService.updateRoom(id, roomDetails);
    }

    @DeleteMapping("/{hotelId}/{roomId}")
    public void deleteRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
        roomService.deleteRoom(hotelId);
    }

    @GetMapping("/{hotelId}/rooms/{roomId}")
    public Room getRoomById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        return roomService.getRoomById(hotelId, roomId);
    }
}
