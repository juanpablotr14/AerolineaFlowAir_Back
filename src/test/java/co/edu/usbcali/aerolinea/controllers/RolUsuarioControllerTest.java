package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.RolUsuarioService;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RolUsuarioControllerTest {
    @Autowired
    private RolUsuarioController rolUsuarioController;
    @MockBean
    private RolUsuarioService rolUsuarioService;

    //Prueba buena
    @Test
    public void guardarRolUsuario() throws Exception {
        when(rolUsuarioService.guardarRolUsuario(any())).thenReturn(RolUsuarioUtilTest.RolusuarioDTO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.guardarRolUsuario(RolUsuarioUtilTest.RolusuarioDTO);

        verify(rolUsuarioService).guardarRolUsuario(eq(RolUsuarioUtilTest.RolusuarioDTO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarRolUsuario_malo() {
        try {
            rolUsuarioController.guardarRolUsuario(RolUsuarioUtilTest.RolusuarioDTO_No_DESCRIPCION);
        } catch (Exception e) {
            assertEquals(RolUsuarioUtilTest.DESCRIPTION_INVALIDA, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerRolUsuarios() throws Exception {
        when(rolUsuarioService.obtenerRolUsuarios()).thenReturn(RolUsuarioUtilTest.rolusuariosDTOS);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.obtenerTodos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(1, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerRolUsuarios_mala() throws Exception {
        when(rolUsuarioService.obtenerRolUsuarios()).thenReturn(RolUsuarioUtilTest.rolUsuario_null);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.obtenerTodos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerRolUsuario() throws Exception {
        when(rolUsuarioService.obtenerRolUsuario(any())).thenReturn(RolUsuarioUtilTest.RolusuarioDTO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.obtenerRolUsuario(RolUsuarioUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerRolUsuario_mala() {
        try {
            rolUsuarioController.obtenerRolUsuario(2);
        } catch (Exception e) {
            assertEquals(String.format(RolUsuarioUtilTest.ID_INVALID, 2), e.getMessage());
        }
    }
}
