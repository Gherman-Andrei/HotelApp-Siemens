package andrei.gherman.HotelApp.Repository;

import andrei.gherman.HotelApp.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}