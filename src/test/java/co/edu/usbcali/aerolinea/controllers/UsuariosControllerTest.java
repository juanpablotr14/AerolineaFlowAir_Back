package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.UsuarioService;
import co.edu.usbcali.aerolinea.utility.UsuarioUtilTest;
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
public class UsuariosControllerTest {
    @Autowired
    private UsuariosController usuarioController;

    @MockBean
    private UsuarioService usuarioService;

    //Prueba buena
    @Test
    public void guardarUsuario() throws Exception {
        when(usuarioService.agregarUsuario(any())).thenReturn(UsuarioUtilTest.usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.agregarUsuario(UsuarioUtilTest.usuarioDTO);

        verify(usuarioService).agregarUsuario(eq(UsuarioUtilTest.usuarioDTO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarUsuari_malo() {
        try {
            usuarioController.agregarUsuario(UsuarioUtilTest.UsuarioDTO_Malo);
        } catch (Exception e) {
            assertEquals(UsuarioUtilTest.ID_MALO, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerUsuarios() {
        when(usuarioService.obtenerUsuarios()).thenReturn(UsuarioUtilTest.usuariosDTOS);

        ResponseEntity<List<UsuarioDTO>> response = usuarioController.obtenerUsuarios();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(2, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerUsuarios_malo() {
        when(usuarioService.obtenerUsuarios()).thenReturn(UsuarioUtilTest.usuariosDTOS_vacio);

        ResponseEntity<List<UsuarioDTO>> response = usuarioController.obtenerUsuarios();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerUsuario() throws Exception {
        when(usuarioService.obtenerUsuario(any())).thenReturn(UsuarioUtilTest.usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.obtenerUsuario(UsuarioUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba malo
    @Test
    public void obtenerUsuario_malo() {
        try {
            usuarioController.obtenerUsuario(2);
        } catch (Exception e) {
            assertEquals(String.format(UsuarioUtilTest.ID_MALO, 2), e.getMessage());
        }
    }

}
