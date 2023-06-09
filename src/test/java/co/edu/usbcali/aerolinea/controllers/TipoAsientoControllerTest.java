package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.TipoAsientoService;
import co.edu.usbcali.aerolinea.utility.TipoAsientoUtilTest;
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
public class TipoAsientoControllerTest {
    @Autowired
    private TipoAsientoController tipoAsientoController;

    @MockBean
    private TipoAsientoService tipoAsientoService;

    //Prueba buena
    @Test
    public void guardarTipoAsiento() throws Exception {
        when(tipoAsientoService.agregarTipoAsiento(any())).thenReturn(TipoAsientoUtilTest.TipoasientoDTO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.guardarTipoAsiento(TipoAsientoUtilTest.TipoasientoDTO);

        verify(tipoAsientoService).agregarTipoAsiento(eq(TipoAsientoUtilTest.TipoasientoDTO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void guardarTipoAsiento_malo() {
        try {
            tipoAsientoController.guardarTipoAsiento(TipoAsientoUtilTest.TipoAsientoDTO_No_Descrip);
        } catch (Exception e) {
            assertEquals(TipoAsientoUtilTest.DESCRIPCION_INVALID, e.getMessage());
        }
    }
    //Prueba buena
    @Test
    public void obtenerTipoAsientos() {
        when(tipoAsientoService.obtenerTipoAsientos()).thenReturn(TipoAsientoUtilTest.tipoasientosDTOS);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(1, response.getBody().size());
    }
    //Prueba mala
    @Test
    public void obtenerTipoAsientos_mala() {
        when(tipoAsientoService.obtenerTipoAsientos()).thenReturn(TipoAsientoUtilTest.tipoAsiento_vacio);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(0, response.getBody().size());
    }
    //Prueba buena
    @Test
    public void obtenerTipoAsiento() throws Exception {
        when(tipoAsientoService.obtenerTipoAsiento(any())).thenReturn(TipoAsientoUtilTest.TipoasientoDTO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.obtenerTipoAsiento(TipoAsientoUtilTest.CODIGO1);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    //Prueba mala
    @Test
    public void obtenerTipoAsiento_malo() {
        try {
            tipoAsientoController.obtenerTipoAsiento(2);
        } catch (Exception e) {
            assertEquals(String.format(TipoAsientoUtilTest.ID_INVALID, 2), e.getMessage());
        }
    }
}
