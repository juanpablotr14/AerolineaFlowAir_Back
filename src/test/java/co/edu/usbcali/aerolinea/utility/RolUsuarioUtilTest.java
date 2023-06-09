package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.mappers.RolUsuarioMapper;

import java.util.Arrays;
import java.util.List;

public class RolUsuarioUtilTest {
    public static Integer CODIGO1 = 1;
    public static String DESCRIPCION1 = "Cliente";
    public static String ESTADO1 = "A";
    public static String DESCRIPTION_INVALIDA = "La descripción es invalida";
    public static String ID_INVALID = "El rol de usuario con id %s no existe";

    public static RolUsuario Rolusuario1 =
            RolUsuario.builder().idRolusuario(CODIGO1).descripcion(DESCRIPCION1).estado(ESTADO1).build();
    public static RolUsuarioDTO RolusuarioDTO =
            RolUsuarioDTO.builder().idRolusuario(CODIGO1).descripcion(DESCRIPCION1).estado(ESTADO1).build();
    public static RolUsuarioDTO RolusuarioDTO_No_id =
            RolUsuarioDTO.builder().descripcion(DESCRIPCION1).estado(ESTADO1).build();
    public static RolUsuarioDTO RolusuarioDTO_No_DESCRIPCION =
            RolUsuarioDTO.builder().descripcion(null).estado(ESTADO1).build();

    public static List<RolUsuario> rolUsuarios = Arrays.asList(Rolusuario1);
    public static List<RolUsuarioDTO> rolusuariosDTOS = RolUsuarioMapper.domainToDtoList(rolUsuarios);
    public static List<RolUsuarioDTO> rolUsuario_null = Arrays.asList();
}
