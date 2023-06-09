package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.Avion;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.AvionDTO;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.AvionService;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilTest;
import co.edu.usbcali.aerolinea.utility.AvionUtilTest;
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
public class AvionServiceImplTest {
    @Autowired
    private AvionService avionService;
    @MockBean
    private AvionRepository avionRepository;

    //Prueba Unitaria Buena
    @Test
    void obtenerAvion() throws Exception{
        Avion avionTest = Avion.builder()
                .idAvion(1)
                .aerolineaAvion("FlowAir")
                .estado("A")
                .build();

        Mockito.when(avionRepository.existsById(1)).thenReturn(true);
        Mockito.when(avionRepository.getReferenceById(1)).thenReturn(avionTest);
        // Llamado al método a probar
        AvionDTO avionDTO = avionService.obtenerAvion(1);
        // Verificación del resultado esperado
        assertEquals(1 ,avionDTO.getIdAvion());
    }

    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerAvion_estadoNoActivo() throws Exception {
        Avion avionTest = Avion.builder()
                .idAvion(1)
                .aerolineaAvion("FlowAir")
                .estado("D")
                .build();

        Mockito.when(avionRepository.existsById(1)).thenReturn(true);
        Mockito.when(avionRepository.getReferenceById(1)).thenReturn(avionTest);

        // Llamado al método a probar
        AvionDTO avionDTO = avionService.obtenerAvion(1);

        // Verificación del resultado esperado
        assertEquals("D", avionDTO.getEstado());
    }

    //Prueba unitaria buena
    @Test
    void obtenerAviones() throws Exception {

        List<Avion> avionList = Arrays.asList(Avion.builder()
                    .idAvion(1)
                    .aerolineaAvion("FlowAir")
                    .estado("A")
                    .build(),
                Avion.builder()
                        .idAvion(2)
                        .aerolineaAvion("VivaAir")
                        .estado("A")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(avionRepository.findAll()).thenReturn(avionList);

        List<AvionDTO> aviones = avionService.obtenerAviones();

        assertEquals(2, aviones.size());
        assertEquals("FlowAir", aviones.get(0).getAerolineaAvion());

    }

    //Prueba unitaria mala
    @Test
    void obtenerAviones_listaVacia() throws Exception {
        List<Avion> avionList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(avionRepository.findAll()).thenReturn(avionList);

        List<AvionDTO> aviones = avionService.obtenerAviones();

        assertTrue(aviones.isEmpty());
    }
    //Prueba unitaria buena
    @Test
    void agregarAvion() throws Exception {
        given(avionRepository.existsById(AvionUtilTest.CODIGO1)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilTest.CODIGO1)).willReturn(AvionUtilTest.Avion1);
        given(avionRepository.save(AvionUtilTest.Avion1)).willReturn(AvionUtilTest.Avion1);
        AvionDTO avionGuardado = avionService.agregarAvion(AvionUtilTest.AvionDTO1);

        assertEquals(AvionUtilTest.CODIGO1, avionGuardado.getIdAvion());
        assertEquals(AvionUtilTest.AEROLINEA1, avionGuardado.getAerolineaAvion());
    }
    //Prueba unitaria mala
    @Test
    void agregarAvion_malo(){
        Exception exception = assertThrows(Exception.class, () -> avionService.agregarAvion(AvionUtilTest.AvionDTO2_No_id));

        String expectedMessage = "El id del avion es obligatorio!";
        assertEquals(expectedMessage, exception.getMessage());

    }
}
