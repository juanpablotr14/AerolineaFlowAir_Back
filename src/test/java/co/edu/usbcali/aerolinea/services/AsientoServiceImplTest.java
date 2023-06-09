package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.*;
import co.edu.usbcali.aerolinea.dto.AsientoDTO;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.Interfaces.AsientoService;
import co.edu.usbcali.aerolinea.utility.AsientoUtilTest;
import co.edu.usbcali.aerolinea.utility.TipoAsientoUtilTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AsientoServiceImplTest {
    @Autowired
    private AsientoService asientoService;
    @MockBean
    private AsientoRepository asientoRepository;
    @MockBean
    private TipoAsientoRepository tipoAsientoRepository;

    //Prueba unitaria buena
    @Test
    void obtenerAsiento() throws Exception{
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();

        Asiento asientoTest = Asiento.builder()
                .idAsiento(1)
                .idTipoa(tipoAsiento)
                .idAvion(avion)
                .ubicacion("Ventana")
                .precio(100)
                .estado("A")
                .build();

        Mockito.when(asientoRepository.existsById(1)).thenReturn(true);
        Mockito.when(asientoRepository.getReferenceById(1)).thenReturn(asientoTest);

        // Llamado al método a probar
        AsientoDTO asientoDTO = asientoService.obtenerAsiento(1);

        // Verificación del resultado esperado
        assertEquals(1, asientoDTO.getIdAsiento());
    }

    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerAsiento_estadoNoActivo() throws Exception {
        // Configuración del mock
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();

        Asiento asientoTest = Asiento.builder()
                .idAsiento(1)
                .idTipoa(tipoAsiento)
                .idAvion(avion)
                .ubicacion("Ventana")
                .precio(100)
                .estado("D")
                .build();

        Mockito.when(asientoRepository.existsById(1)).thenReturn(true);
        Mockito.when(asientoRepository.getReferenceById(1)).thenReturn(asientoTest);

        // Llamado al método a probar
        AsientoDTO asientoDTO = asientoService.obtenerAsiento(1);

        // Verificación del resultado esperado
        assertEquals("D", asientoDTO.getEstado());
    }

    //Prueba Unitaria buena
    @Test
    void obtenerAsientos() throws Exception {
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();
        List<Asiento> asientoList = Arrays.asList(Asiento.builder()
                        .idAsiento(1)
                        .idTipoa(tipoAsiento)
                        .idAvion(avion)
                        .ubicacion("Ventana")
                        .precio(100)
                        .estado("A")
                        .build(),
                Asiento.builder()
                        .idAsiento(2)
                        .idTipoa(tipoAsiento)
                        .idAvion(avion)
                        .ubicacion("Centro")
                        .precio(50)
                        .estado("A")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(asientoRepository.findAll()).thenReturn(asientoList);

        List<AsientoDTO> asientoDTOList = asientoService.obtenerAsientos();

        assertEquals(2, asientoDTOList.size());
        assertEquals("Centro", asientoDTOList.get(1).getUbicacion());

    }

    //Prueba unitaria mala
    @Test
    void obtenerAsientos_listaVacia() throws Exception {
        List<Asiento> asientoList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(asientoRepository.findAll()).thenReturn(asientoList);

        List<AsientoDTO> asientos = asientoService.obtenerAsientos();

        assertTrue(asientos.isEmpty());
    }

}
