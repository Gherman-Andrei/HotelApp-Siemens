package andrei.gherman.HotelApp.Repository;

import andrei.gherman.HotelApp.Entity.Reservation;
import andrei.gherman.HotelApp.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long> {
    Collection<Object> findByRoomAndCheckInDateBeforeAndCheckOutDateAfter(Room room, LocalDateTime checkOutDate, LocalDateTime checkInDate);

    Collection<Object> findByRoomAndCheckInDateBeforeAndCheckOutDateAfterAndIdNot(Room room, LocalDateTime checkOutDate, LocalDateTime checkInDate, Long id);
}
