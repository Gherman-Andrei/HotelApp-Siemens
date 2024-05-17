package andrei.gherman.HotelApp.Repository;

import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Entity.RoomType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByRoomTypeAndPriceBetweenAndHotel_IdAndIsAvailableTrue(RoomType roomTypeValue, Double minPrice, Double maxPrice, Long hotelId);

}
