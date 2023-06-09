package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.domain.Avion;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.AvionDTO;

import java.util.List;

public interface AvionService {
    List<AvionDTO> obtenerAviones();
    AvionDTO obtenerAvion(Integer id) throws Exception;
    AvionDTO agregarAvion(AvionDTO avionDTO) throws Exception;
    List<AvionDTO> obtenerAvionesActivos();
    AvionDTO updateAvion(AvionDTO avionDTO) throws Exception;
    AvionDTO deleteAvion(Integer id) throws Exception;
}
