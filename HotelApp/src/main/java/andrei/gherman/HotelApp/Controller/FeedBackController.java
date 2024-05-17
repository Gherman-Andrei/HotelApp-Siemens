package andrei.gherman.HotelApp.Controller;

import andrei.gherman.HotelApp.DTO.FeedbackDTO;
import andrei.gherman.HotelApp.Service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping
    public FeedbackDTO leaveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedBackService.saveFeedback(feedbackDTO);
    }

    @GetMapping("/hotel/{hotelId}")
    public Collection<Object> getFeedback(@PathVariable Long hotelId) {
        return feedBackService.getFeedbacksByHotelId(hotelId);
    }

}
