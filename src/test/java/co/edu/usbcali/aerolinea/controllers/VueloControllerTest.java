package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.VueloDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.VueloService;
import co.edu.usbcali.aerolinea.utility.VueloUtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VueloControllerTest {
    @Autowired
    private VueloController vueloController;

    @MockBean
    private VueloService vueloService;

    //Prueba buena
    @Test
    public void guardarVuelo() throws Exception {
        when(vueloService.guardarVuelo(any())).thenReturn(VueloUtilTest.vueloDTO1);

        ResponseEntity<VueloDTO> response = vueloController.guardarVuelo(VueloUtilTest.vueloDTO1);

        verify(vueloService).guardarVuelo(eq(VueloUtilTest.vueloDTO1));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarVuelo_malo() {
        try {
            vueloController.guardarVuelo(VueloUtilTest.vueloDTO_malo);
        } catch (Exception e) {
            assertEquals(VueloUtilTest.HORA_MALA, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerVuelos() {
        when(vueloService.obtenerVuelos()).thenReturn(VueloUtilTest.vuelosDTOS);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(1, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerVuelos_malo() {
        when(vueloService.obtenerVuelos()).thenReturn(VueloUtilTest.vuelosDTOS_vacio);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerVuelo() throws Exception {
        when(vueloService.obtenerVuelo(any())).thenReturn(VueloUtilTest.vueloDTO1);

        ResponseEntity<VueloDTO> response = vueloController.obtenerVuelo(VueloUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerVuelo_malo() {
        try {
            vueloController.obtenerVuelo(2);
        } catch (Exception e) {
            assertEquals(String.format(VueloUtilTest.ID_INVALID, 2), e.getMessage());
        }
    }
}
