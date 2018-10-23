package sut.sa.g20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.sa.g20.entity.HotelEntity;
import sut.sa.g20.entity.RoomEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Collection<RoomEntity> findByhotel(HotelEntity hotel);

    @Query("SELECT t.roomNumber FROM RoomEntity t WHERE t.roomNumber = :Name")
    Integer findByRoomNumber(@Param("Name")int Name);

    @Query("SELECT t.hotel.hotelId FROM RoomEntity t WHERE t.hotel.hotelId = :id and t.roomNumber = :roomNumber")
    Long findByHotelId(@Param("id")Long id,@Param("roomNumber")int roomNumber);
}
