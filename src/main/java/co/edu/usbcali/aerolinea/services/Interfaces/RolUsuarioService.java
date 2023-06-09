package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.ReservaDTO;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;

import java.util.List;

public interface RolUsuarioService {
    List<RolUsuarioDTO> obtenerRolUsuarios();
    RolUsuarioDTO obtenerRolUsuario(Integer id) throws Exception;
    RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;
    List<RolUsuarioDTO> obtenerRolUsuariosActivos();
    RolUsuarioDTO updateRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;
    RolUsuarioDTO deleteRolUsuario(Integer id) throws Exception;
}

