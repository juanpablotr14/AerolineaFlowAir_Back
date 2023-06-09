package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.*;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.RolUsuarioService;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class RolUsuarioServiceImplTest {
    @Autowired
    private RolUsuarioService rolUsuarioService;
    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;

    //Prueba unitaria buena
    @Test
    void obtenerRolUsuario() throws Exception{
        RolUsuario rolUsuarioTest = RolUsuario.builder()
                .idRolusuario(1)
                .descripcion("Prueba")
                .estado("A")
                .build();

        Mockito.when(rolUsuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(rolUsuarioRepository.getReferenceById(1)).thenReturn(rolUsuarioTest);

        // Llamado al método a probar
        RolUsuarioDTO rolUsuarioDTO = rolUsuarioService.obtenerRolUsuario(1);

        // Verificación del resultado esperado
        assertEquals(1, rolUsuarioDTO.getIdRolusuario());
    }

    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerRolUsuario_estadoNoActivo() throws Exception {
        RolUsuario rolUsuarioTest = RolUsuario.builder()
                .idRolusuario(1)
                .descripcion("Prueba")
                .estado("D")
                .build();

        Mockito.when(rolUsuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(rolUsuarioRepository.getReferenceById(1)).thenReturn(rolUsuarioTest);

        // Llamado al método a probar
        RolUsuarioDTO rolUsuarioDTO = rolUsuarioService.obtenerRolUsuario(1);

        // Verificación del resultado esperado
        assertEquals("D", rolUsuarioDTO.getEstado());
    }

    //Prueba unitaria buena
    @Test
    void obtenerRolUsuarios() throws Exception {

        List<RolUsuario> rolUsuarioList = Arrays.asList(RolUsuario.builder()
                    .idRolusuario(1)
                    .descripcion("Prueba")
                    .estado("A")
                    .build(),
                RolUsuario.builder()
                        .idRolusuario(2)
                        .descripcion("Prueba2")
                        .estado("D")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(rolUsuarioRepository.findAll()).thenReturn(rolUsuarioList);

        List<RolUsuarioDTO> rolUsuarioDTOList = rolUsuarioService.obtenerRolUsuarios();

        assertEquals(2, rolUsuarioDTOList.size());
        assertEquals("Prueba", rolUsuarioDTOList.get(0).getDescripcion());

    }

    //Prueba unitaria mala
    @Test
    void obtenerRolUsuarios_listaVacia() throws Exception {
        List<RolUsuario> rolUsuarioList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(rolUsuarioRepository.findAll()).thenReturn(rolUsuarioList);

        List<RolUsuarioDTO> rolUsuarioDTOList = rolUsuarioService.obtenerRolUsuarios();

        assertTrue(rolUsuarioDTOList.isEmpty());
    }
    //Prueba unitaria buena
    @Test
    void guardarRolUsuario() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilTest.CODIGO1)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilTest.CODIGO1)).willReturn(RolUsuarioUtilTest.Rolusuario1);
        given(rolUsuarioRepository.save(RolUsuarioUtilTest.Rolusuario1)).willReturn(RolUsuarioUtilTest.Rolusuario1);
        RolUsuarioDTO rolUsuarioGuardar = rolUsuarioService.guardarRolUsuario(RolUsuarioUtilTest.RolusuarioDTO);

        assertEquals(RolUsuarioUtilTest.CODIGO1, rolUsuarioGuardar.getIdRolusuario());
        assertEquals(RolUsuarioUtilTest.DESCRIPCION1, rolUsuarioGuardar.getDescripcion());
    }
    //Prueba unitaria mala
    @Test
    void guardarRolUsuario_Exception(){
        Exception exception = assertThrows(Exception.class, () ->
                rolUsuarioService.guardarRolUsuario(RolUsuarioUtilTest.RolusuarioDTO_No_id));

        String expectedMessage = "Id del usuario no valido!";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
