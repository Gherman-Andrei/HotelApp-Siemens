package andrei.gherman.HotelApp.Service;

import andrei.gherman.HotelApp.DTO.HotelDTO;
import andrei.gherman.HotelApp.DTO.RoomDTO;
import andrei.gherman.HotelApp.Entity.Hotel;
import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Entity.RoomType;
import andrei.gherman.HotelApp.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    private static final double WGS84_KM = 6371.0088;

    public List<HotelDTO> findNearbyHotels(Double latitude, Double longitude, Double radius) {
        List<HotelDTO> hotelsInRadius = new ArrayList<>();
        List<HotelDTO> allHotelsDTO = findAll();

        for (HotelDTO hotelDTO : allHotelsDTO) {
            double distance = calculateDistance(latitude, longitude, hotelDTO.getLatitude(), hotelDTO.getLongitude());
            if (distance < radius) {
                hotelsInRadius.add(hotelDTO);
            }
        }

        return hotelsInRadius;
    }

    public List<HotelDTO> findAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public HotelDTO findById(Long id) {
        return convertToDTO(hotelRepository.findById(id).get());
    }

    public HotelDTO saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = convertToEntity(hotelDTO);
        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToDTO(savedHotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        hotel.setName(hotelDTO.getName());
        hotel.setLatitude(hotelDTO.getLatitude());
        hotel.setLongitude(hotelDTO.getLongitude());
        hotel.setRooms(hotelDTO.getRooms().stream().map(this::convertToEntity).collect(Collectors.toList()));
        Hotel updatedHotel = hotelRepository.save(hotel);
        return convertToDTO(updatedHotel);
    }

    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        return convertToDTO(hotel);
    }

    private HotelDTO convertToDTO(Hotel hotel) {
        List<RoomDTO> roomDTOs = hotel.getRooms().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getLatitude(), hotel.getLongitude(), roomDTOs);
    }

    private Hotel convertToEntity(HotelDTO hotelDTO) {
        List<Room> rooms = hotelDTO.getRooms().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setLatitude(hotelDTO.getLatitude());
        hotel.setLongitude(hotelDTO.getLongitude());
        hotel.setRooms(rooms);
        return hotel;
    }

    private RoomDTO convertToDTO(Room room) {
        return new RoomDTO(room.getId(), room.getRoomNumber(), room.getType(), room.getPrice(), room.isAvailable());
    }

    private Room convertToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        RoomType roomType;
        room.setId(roomDTO.getId());
        room.setRoomNumber(roomDTO.getRoomNumber());
        roomType = RoomType.valueOf(String.valueOf(roomDTO.getRoomType()));
        room.setPrice(roomDTO.getPrice());
        room.setAvailable(roomDTO.isAvailable());
        room.setRoomType(roomType);
        return room;
    }

    private double calculateDistance(double latitudeUser, double longitudeUser, double latitudeHotel, double longitudeHotel) {
            double latDistance = Math.toRadians(latitudeHotel - latitudeUser);
            double longDistance = Math.toRadians(longitudeHotel - longitudeUser);

            double a = Math.sin(latDistance/2)*Math.sin(latDistance/2) + Math.cos(Math.toRadians(latitudeUser))*Math.cos(Math.toRadians(latitudeHotel))*Math.sin(longDistance/2)*Math.sin(longDistance/2);
            double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return WGS84_KM * b;
    }
}