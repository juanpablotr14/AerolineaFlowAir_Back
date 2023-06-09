package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RolUsuarioMapper {
    public static RolUsuarioDTO domainToDto(RolUsuario rolUsuario){
        return RolUsuarioDTO.builder()
                .idRolusuario(rolUsuario.getIdRolusuario())
                .descripcion(rolUsuario.getDescripcion())
                .estado(rolUsuario.getEstado())
                .build();
    }
    public static RolUsuario dtoToDomain(RolUsuarioDTO rolUsuarioDTO){
        return RolUsuario.builder()
                .idRolusuario(rolUsuarioDTO.getIdRolusuario())
                .descripcion(rolUsuarioDTO.getDescripcion())
                .estado(rolUsuarioDTO.getEstado())
                .build();
    }
    public static List<RolUsuarioDTO> domainToDtoList(List<RolUsuario> rolUsuarios){
        return rolUsuarios.stream().map(td -> domainToDto(td)).collect(Collectors.toList());
    }
    public static List<RolUsuario> dtoToDomainList(List<RolUsuarioDTO> rolUsuariosDTO){
        return rolUsuariosDTO.stream().map(td -> dtoToDomain(td)).collect(Collectors.toList());
    }
}
