package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.domain.Usuario;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.UsuarioService;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilTest;
import co.edu.usbcali.aerolinea.utility.TipoAsientoUtilTest;
import co.edu.usbcali.aerolinea.utility.UsuarioUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioServiceImplTest {
    @Autowired
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;
    //Prueba unitaria buena
    @Test
    void obtenerUsuario() throws Exception{
        RolUsuario rolUsuario = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        Usuario usuarioTest = Usuario.builder()
                .idUsuario(1)
                .idRolUsuario(rolUsuario)
                .cedula("1126603627")
                .nombre("Prueba1")
                .apellido("Prueb")
                .correo("prueba@gmail.com")
                .estado("A")
                .build();

        Mockito.when(usuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(usuarioRepository.getReferenceById(1)).thenReturn(usuarioTest);

        // Llamado al método a probar
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuario(1);

        // Verificación del resultado esperado
        assertEquals(1, usuarioDTO.getIdUsuario());
    }
    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerUsuario_estadoNoActivo() throws Exception {
        RolUsuario rolUsuario = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        Usuario usuarioTest = Usuario.builder()
                .idUsuario(1)
                .idRolUsuario(rolUsuario)
                .cedula("1126603627")
                .nombre("Prueba1")
                .apellido("Prueb")
                .correo("prueba@gmail.com")
                .estado("D")
                .build();

        Mockito.when(usuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(usuarioRepository.getReferenceById(1)).thenReturn(usuarioTest);

        // Llamado al método a probar
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuario(1);

        // Verificación del resultado esperado
        assertEquals("D", usuarioDTO.getEstado());
    }

    //Prueba unitaria buena
    @Test
    void obtenerTipoAsientos() throws Exception {
        RolUsuario rolUsuario2 = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        List<Usuario> usuarioList = Arrays.asList(Usuario.builder()
                        .idUsuario(1)
                        .idRolUsuario(rolUsuario2)
                        .cedula("1126603627")
                        .nombre("Prueba1")
                        .apellido("Prueb")
                        .correo("prueba@gmail.com")
                        .estado("A")
                        .build(),
                Usuario.builder()
                        .idUsuario(2)
                        .idRolUsuario(rolUsuario2)
                        .cedula("765362762")
                        .nombre("Prueba2")
                        .apellido("Prueb")
                        .correo("prueba2@gmail.com")
                        .estado("D")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);

        List<UsuarioDTO> usuarioDTOList = usuarioService.obtenerUsuarios();

        assertEquals(2, usuarioDTOList.size());
        assertEquals("prueba2@gmail.com", usuarioDTOList.get(1).getCorreo());

    }
    //Prueba unitaria mala
    @Test
    void obtenerUsuarios_listaVacia() throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);

        List<UsuarioDTO> usuarioDTOList = usuarioService.obtenerUsuarios();

        assertTrue(usuarioDTOList.isEmpty());
    }

}
