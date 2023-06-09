package co.edu.usbcali.aerolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class RolUsuarioDTO {
    private Integer idRolusuario;
    private String descripcion;
    private String estado;
}
