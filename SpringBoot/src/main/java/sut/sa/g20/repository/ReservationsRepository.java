package sut.sa.g20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.sa.g20.entity.ReservationsEntity;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public
interface ReservationsRepository extends JpaRepository<ReservationsEntity, Long> {
}