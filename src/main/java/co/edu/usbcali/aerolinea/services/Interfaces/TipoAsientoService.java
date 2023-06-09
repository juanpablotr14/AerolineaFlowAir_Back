package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dto.TipoAsientoDTO;

import java.util.List;

public interface TipoAsientoService {
    List<TipoAsientoDTO> obtenerTipoAsientos();
    TipoAsientoDTO obtenerTipoAsiento(Integer id) throws Exception;
    TipoAsientoDTO agregarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;
    List<TipoAsientoDTO> obtenerTipoAsientosActivos();
    TipoAsientoDTO updateTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;
    TipoAsientoDTO deleteTipoAsiento(Integer id) throws Exception;
}
