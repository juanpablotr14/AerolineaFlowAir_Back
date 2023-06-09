package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.domain.Usuario;
import co.edu.usbcali.aerolinea.domain.Vuelo;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.dto.VueloDTO;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.UsuarioService;
import co.edu.usbcali.aerolinea.services.Interfaces.VueloService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VueloServiceImplTest {
    @Autowired
    private VueloService vueloService;
    @MockBean
    private VueloRepository vueloRepository;
    //Prueba unitaria buena
    @Test
    void obtenerVuelo() throws Exception{
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();

        Vuelo vueloTest = Vuelo.builder()
                .idVuelo(1)
                .idAeropuertoOrigen(aeropuertoOrigen)
                .idAeropuertoDestino(aeropuertoDestino)
                .precio(200000)
                .fechaHoraSalida( new Date())
                .fechaHoraLlegada(new Date())
                .precioAsientoPreferencial(150000)
                .precioAsientoVip(100000)
                .precioAsientoTurista(60000)
                .estado("A")
                .build();

        Mockito.when(vueloRepository.existsById(1)).thenReturn(true);
        Mockito.when(vueloRepository.getReferenceById(1)).thenReturn(vueloTest);

        // Llamado al método a probar
        VueloDTO vueloDTO = vueloService.obtenerVuelo(1);

        // Verificación del resultado esperado
        assertEquals(1, vueloDTO.getIdVuelo());
    }
    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerVuelo_estadoNoActivo() throws Exception {
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();

        Vuelo vueloTest = Vuelo.builder()
                .idVuelo(1)
                .idAeropuertoOrigen(aeropuertoOrigen)
                .idAeropuertoDestino(aeropuertoDestino)
                .precio(200000)
                .fechaHoraSalida( new Date())
                .fechaHoraLlegada(new Date())
                .precioAsientoPreferencial(150000)
                .precioAsientoVip(100000)
                .precioAsientoTurista(60000)
                .estado("D")
                .build();

        Mockito.when(vueloRepository.existsById(1)).thenReturn(true);
        Mockito.when(vueloRepository.getReferenceById(1)).thenReturn(vueloTest);

        // Llamado al método a probar
        VueloDTO vueloDTO = vueloService.obtenerVuelo(1);

        // Verificación del resultado esperado
        assertEquals("D", vueloDTO.getEstado());
    }

    //Prueba unitaria buena
    @Test
    void obtenerVuelos() throws Exception {
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();

        List<Vuelo> vueloList = Arrays.asList(Vuelo.builder()
                        .idVuelo(1)
                        .idAeropuertoOrigen(aeropuertoOrigen)
                        .idAeropuertoDestino(aeropuertoDestino)
                        .precio(200000)
                        .fechaHoraSalida( new Date())
                        .fechaHoraLlegada(new Date())
                        .precioAsientoPreferencial(150000)
                        .precioAsientoVip(100000)
                        .precioAsientoTurista(60000)
                        .estado("A")
                        .build(),
                Vuelo.builder()
                        .idVuelo(2)
                        .idAeropuertoOrigen(aeropuertoOrigen)
                        .idAeropuertoDestino(aeropuertoDestino)
                        .precio(200000)
                        .fechaHoraSalida( new Date())
                        .fechaHoraLlegada(new Date())
                        .precioAsientoPreferencial(150000)
                        .precioAsientoVip(100000)
                        .precioAsientoTurista(60000)
                        .estado("D")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(vueloRepository.findAll()).thenReturn(vueloList);

        List<VueloDTO> vueloDTOList = vueloService.obtenerVuelos();

        assertEquals(2, vueloDTOList.size());
        assertEquals(60000, vueloDTOList.get(1).getPrecioAsientoTurista());

    }
    //Prueba unitaria mala
    @Test
    void obtenerVuelos_listaVacia() throws Exception {
        List<Vuelo> vuelosList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(vueloRepository.findAll()).thenReturn(vuelosList);

        List<VueloDTO> vueloDTOList = vueloService.obtenerVuelos();

        assertTrue(vueloDTOList.isEmpty());
    }
}
