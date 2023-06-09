package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.dto.VueloDTO;

import java.util.List;

public interface VueloService {
    VueloDTO guardarVuelo(VueloDTO vuelDTO) throws Exception;
    VueloDTO obtenerVuelo(Integer id) throws Exception;
    List<VueloDTO> obtenerVuelos();
    List<VueloDTO> obtenerVuelosActivos();
    VueloDTO updateVuelo(VueloDTO vueloDTO) throws Exception;
    VueloDTO deleteVuelo(Integer id) throws Exception;

}
