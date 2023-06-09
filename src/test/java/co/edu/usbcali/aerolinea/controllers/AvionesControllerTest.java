package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.AvionDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.AvionService;
import co.edu.usbcali.aerolinea.utility.AvionUtilTest;
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
public class AvionesControllerTest {
    @Autowired
    private AvionesController avionController;
    @MockBean
    private AvionService avionService;

    //Prueba Buena
    @Test
    public void guardarAvion() throws Exception {
        when(avionService.agregarAvion(any())).thenReturn(AvionUtilTest.AvionDTO1);

        ResponseEntity<AvionDTO> response = avionController.agregarAvion(AvionUtilTest.AvionDTO1);

        verify(avionService).agregarAvion(eq(AvionUtilTest.AvionDTO1));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarAvion_malo() {
        try {
            avionController.agregarAvion(AvionUtilTest.AVIONDTO_AEROLINEA_NULL);
        } catch (Exception e) {
            assertEquals(AvionUtilTest.AEROLINEA_MALA, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerAviones() {
        when(avionService.obtenerAviones()).thenReturn(AvionUtilTest.avionDTOS);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAviones();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(2, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerAviones_malo() {
        when(avionService.obtenerAviones()).thenReturn(AvionUtilTest.AVIONESDTO_VACIO);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAviones();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerAvionOk() throws Exception {
        when(avionService.obtenerAvion(any())).thenReturn(AvionUtilTest.AvionDTO1);

        ResponseEntity<AvionDTO> response = avionController.obtenerAvion(AvionUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerAvionNotOk() {
        try {
            avionController.obtenerAvion(AvionUtilTest.CODIGO2);
        } catch (Exception e) {
            assertEquals(String.format(AvionUtilTest.ID_INVALID, AvionUtilTest.CODIGO2), e.getMessage());
        }
    }
}
