package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;

import java.sql.SQLException;
import java.util.List;

public interface AeropuertoService {
    AeropuertoDTO agregarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception;
    AeropuertoDTO obtenerAeropuerto(Integer id) throws Exception;
    List<AeropuertoDTO> obtenerAeropuertos();
    List<AeropuertoDTO> obtenerAeropuertosActivos();
    AeropuertoDTO updateAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception;
    AeropuertoDTO deleteAeropuerto(Integer id) throws Exception;
}
