package andrei.gherman.HotelApp.Repository;

import andrei.gherman.HotelApp.DTO.FeedbackDTO;
import andrei.gherman.HotelApp.Entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {
    List<FeedbackDTO> findAllByHotelId(Long id);
}
