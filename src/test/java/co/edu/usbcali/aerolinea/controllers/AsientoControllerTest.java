package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.AsientoDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.AsientoService;
import co.edu.usbcali.aerolinea.utility.AsientoUtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AsientoControllerTest {
    @Autowired
    private AsientoController asientoController;

    @MockBean
    private AsientoService asientoService;

    //Prueba Buena
    @Test
    public void guardarAsiento() throws Exception {
        when(asientoService.agregarAsiento(any())).thenReturn(AsientoUtilTest.asientoDTO);

        ResponseEntity<AsientoDTO> response = asientoController.guardarAsiento(AsientoUtilTest.asientoDTO);

        verify(asientoService).agregarAsiento(eq(AsientoUtilTest.asientoDTO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarAsiento_malo() {
        try {
            asientoController.guardarAsiento(AsientoUtilTest.asientoDTO_malo);
        } catch (Exception e) {
            assertEquals(AsientoUtilTest.UBICACION_MALO, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerAsientos() {
        when(asientoService.obtenerAsientos()).thenReturn(AsientoUtilTest.asientosDTO);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(2, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerAsientos_malo() {
        when(asientoService.obtenerAsientos()).thenReturn(AsientoUtilTest.asientos_vacio);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerAsiento() throws Exception {
        when(asientoService.obtenerAsiento(any())).thenReturn(AsientoUtilTest.asientoDTO);

        ResponseEntity<AsientoDTO> response = asientoController.obtenerAsiento(AsientoUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerAsiento_malo() {
        try {
            asientoController.obtenerAsiento(AsientoUtilTest.CODIGO2);
        } catch (Exception e) {
            assertEquals(String.format(AsientoUtilTest.ID_NULL, AsientoUtilTest.CODIGO2), e.getMessage());
        }
    }
}
