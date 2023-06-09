package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Usuario;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static UsuarioDTO domainToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .idRolusuario(usuario.getIdRolUsuario() != null ? usuario.getIdRolUsuario().getIdRolusuario() : null)
                .cedula(usuario.getCedula())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado(usuario.getEstado())
                .build();
    }
    public static List<UsuarioDTO> domainToDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> domainToDTO(usuario)).collect(Collectors.toList());
    }
    public static Usuario dtoToDomain(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .idUsuario(usuarioDTO.getIdUsuario())
                .cedula(usuarioDTO.getCedula())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .correo(usuarioDTO.getCorreo())
                .estado(usuarioDTO.getEstado())
                .build();
    }
    public static List<Usuario> dtoToDomainList(List<UsuarioDTO> usuariosDTO) {
        return usuariosDTO.stream().map(usuarioDTO -> dtoToDomain(usuarioDTO)).collect(Collectors.toList());
    }
}
