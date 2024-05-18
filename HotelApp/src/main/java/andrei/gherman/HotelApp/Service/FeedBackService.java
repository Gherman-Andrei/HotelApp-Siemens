package andrei.gherman.HotelApp.Service;

import andrei.gherman.HotelApp.DTO.FeedbackDTO;
import andrei.gherman.HotelApp.Entity.FeedBack;
import andrei.gherman.HotelApp.Entity.Hotel;
import andrei.gherman.HotelApp.Repository.FeedbackRepository;
import andrei.gherman.HotelApp.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public FeedbackDTO saveFeedback(FeedbackDTO feedbackDTO) {
        FeedBack feedBack = new FeedBack();
        feedBack.setNume(feedbackDTO.getNume());
        feedBack.setComment(feedbackDTO.getComment());
        feedBack.setRating(feedbackDTO.getRating());

        Hotel hotel = hotelRepository.findById(feedbackDTO.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found"));
        feedBack.setHotel(hotel);
        feedBack = feedbackRepository.save(feedBack);
        feedbackDTO.setId(feedBack.getId());
        return feedbackDTO;
    }
    public List<FeedbackDTO> getFeedbacksByHotelId(Long hotelId) {
        return feedbackRepository.findAllByHotelId(hotelId);
    }

    private FeedbackDTO convertToDTO(FeedBack feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setNume(feedback.getNume());
        dto.setComment(feedback.getComment());
        dto.setRating(feedback.getRating());
        dto.setHotelId(feedback.getHotel().getId());
        return dto;
    }
}
