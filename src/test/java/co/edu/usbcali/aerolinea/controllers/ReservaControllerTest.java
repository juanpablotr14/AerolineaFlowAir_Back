package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.ReservaDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.ReservaService;
import co.edu.usbcali.aerolinea.utility.ReservaUtilTest;
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
public class ReservaControllerTest {
    @Autowired
    private ReservaController reservaController;

    @MockBean
    private ReservaService reservaService;

    //Prueba buena
    @Test
    public void guardarReserva() throws Exception {
        when(reservaService.agregarReserva(any())).thenReturn(ReservaUtilTest.reservaDTO1);

        ResponseEntity<ReservaDTO> response = reservaController.agregarReserva(ReservaUtilTest.reservaDTO1);

        verify(reservaService).agregarReserva(eq(ReservaUtilTest.reservaDTO1));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarReserva_malo() {
        try {
            reservaController.agregarReserva(ReservaUtilTest.reservaDTO_null);
        } catch (Exception e) {
            assertEquals(ReservaUtilTest.ID_NULL, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerReservas() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilTest.reservasDTOS);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(1, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerReservas_mala() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilTest.reservaDTO_vacio);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerReserva() throws Exception {
        when(reservaService.obtenerReserva(any())).thenReturn(ReservaUtilTest.reservaDTO1);

        ResponseEntity<ReservaDTO> response = reservaController.obtenerReserva(ReservaUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerReserva_mala() {
        try {
            reservaController.obtenerReserva(2);
        } catch (Exception e) {
            assertEquals(String.format(ReservaUtilTest.ID_INVALID, 2), e.getMessage());
        }
    }
}
