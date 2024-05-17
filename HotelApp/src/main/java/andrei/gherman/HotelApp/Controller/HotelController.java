package andrei.gherman.HotelApp.Controller;

import andrei.gherman.HotelApp.DTO.HotelDTO;
import andrei.gherman.HotelApp.Entity.Hotel;
import andrei.gherman.HotelApp.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.findAll();
    }

    @PostMapping
    public HotelDTO createHotel(@RequestBody HotelDTO hotel) {
        return hotelService.saveHotel(hotel);
    }


    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }



    @PutMapping("/{id}")
    public HotelDTO updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDetails) {
        return hotelService.updateHotel(id, hotelDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }

    @GetMapping("/nearby")
    public List<HotelDTO> getNearbyHotels(@RequestParam Double latitude,
                                          @RequestParam Double longitude,
                                          @RequestParam Double radius) {

        return hotelService.findNearbyHotels(latitude, longitude, radius);
    }



}
