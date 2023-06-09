package co.edu.usbcali.aerolinea.services.Interfaces;

import co.edu.usbcali.aerolinea.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerUsuarios();
    UsuarioDTO obtenerUsuario(Integer id) throws Exception;
    UsuarioDTO agregarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    List<UsuarioDTO> obtenerUsuariosActivos();
    UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO) throws Exception;
    UsuarioDTO deleteUsuario(Integer id) throws Exception;
}
