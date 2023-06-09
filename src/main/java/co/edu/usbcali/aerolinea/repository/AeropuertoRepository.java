package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
    List<Aeropuerto> findAllByEstado(String estado);
}