package andrei.gherman.HotelApp.Repository;

import andrei.gherman.HotelApp.Entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {
    Collection<Object> findAllByHotelId(Long id);
}
