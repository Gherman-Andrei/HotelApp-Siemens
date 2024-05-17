package andrei.gherman.HotelApp;

import andrei.gherman.HotelApp.Entity.Hotel;
import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Repository.HotelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Hotel>> typeRef = new TypeReference<List<Hotel>>() {};
        InputStream inputStream = DataLoader.class.getResourceAsStream("/json/hotels.json");
        System.out.println("InputStream is null: " + (inputStream == null));

        try {
            List<Hotel> hotels = mapper.readValue(inputStream, typeRef);

            Map<String, Hotel> hotelMap = new HashMap<>();
            for (Hotel hotel : hotels) {
                hotelMap.put(hotel.getName(), hotel);
            }

            for (Hotel hotel : hotels) {
                for (Room room : hotel.getRooms()) {
                    Hotel foundHotel = hotelMap.get(hotel.getName());
                    if (foundHotel != null) {
                        room.setHotel(foundHotel);
                    }
                }
            }
            hotelRepository.saveAll(hotels);
            System.out.println("Hotels saved!");
        } catch (Exception e) {
            System.out.println("Hotels not saved! " + e.getMessage());
        }
    }
}
