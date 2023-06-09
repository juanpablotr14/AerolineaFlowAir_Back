package co.edu.usbcali.aerolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO {
    private Integer idUsuario;
    private Integer idRolusuario;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String estado;
}
