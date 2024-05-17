package andrei.gherman.HotelApp.Service;

import andrei.gherman.HotelApp.DTO.ReservationDTO;
import andrei.gherman.HotelApp.Entity.Reservation;
import andrei.gherman.HotelApp.Entity.Room;
import andrei.gherman.HotelApp.Repository.ReservationRepository;
import andrei.gherman.HotelApp.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;


    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        boolean isAvailable = reservationRepository.findByRoomAndCheckInDateBeforeAndCheckOutDateAfter(
                room, reservationDTO.getCheckOutDate(), reservationDTO.getCheckInDate()).isEmpty();

        if (!isAvailable) {
            throw new RuntimeException("Room is not available for the selected dates");
        }
        Reservation reservation = new Reservation();
        reservation.setNume(reservationDTO.getNume());
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setRoom(room);

        reservation = reservationRepository.save(reservation);
        reservationDTO.setId(reservation.getId());
        return reservationDTO;
    }
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        return convertToDTO(reservation);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO updatedReservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Room room = roomRepository.findById(updatedReservationDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        boolean isAvailable = reservationRepository.findByRoomAndCheckInDateBeforeAndCheckOutDateAfterAndIdNot(
                room, updatedReservationDTO.getCheckOutDate(), updatedReservationDTO.getCheckInDate(), id).isEmpty();

        if (!isAvailable) {
            throw new RuntimeException("Room is not available for the selected dates");
        }

        existingReservation.setNume(updatedReservationDTO.getNume());
        existingReservation.setCheckInDate(LocalDateTime.from(updatedReservationDTO.getCheckInDate()));
        existingReservation.setCheckOutDate(LocalDateTime.from(updatedReservationDTO.getCheckOutDate()));
        existingReservation.setRoom(room);

        existingReservation = reservationRepository.save(existingReservation);
        updatedReservationDTO.setId(existingReservation.getId());
        return updatedReservationDTO;
    }


    public void cancelReservationById(Long id) {
        reservationRepository.deleteById(id);
    }



    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setNume(reservation.getNume());
        dto.setCheckInDate(LocalDateTime.from(reservation.getCheckInDate()));
        dto.setCheckOutDate(LocalDateTime.from(reservation.getCheckOutDate()));
        dto.setRoomId(reservation.getRoom().getId());
        return dto;
    }


}
