package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.domain.*;
import co.edu.usbcali.aerolinea.dto.ReservaDTO;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservaServiceImplTest {
    @Autowired
    private ReservaService reservaService;
    @MockBean
    private ReservaRepository reservaRepository;

    //Prueba unitaria buena
    @Test
    void obtenerReserva() throws Exception{
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();
        Vuelo vuelo = Vuelo.builder().idVuelo(1).idAeropuertoOrigen(aeropuertoOrigen).idAeropuertoDestino(aeropuertoDestino).precio(10000).fechaHoraSalida(new Date()).fechaHoraLlegada(new Date()).precioAsientoPreferencial(80000).precioAsientoVip(60000).precioAsientoTurista(30000).estado("A").build();
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();
        Asiento asiento = Asiento.builder().idAsiento(1).idTipoa(tipoAsiento).idAvion(avion).ubicacion("Cai").precio(30000).estado("D").build();
        RolUsuario rolUsuario = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        Usuario usuario = Usuario.builder().idUsuario(1).idRolUsuario(rolUsuario).cedula("1112243").nombre("Prueba").apellido("APrueba").correo("prueba@gmail.com").estado("A").build();

        Reserva reservaTest = Reserva.builder()
                .idReserva(1)
                .idVuelo(vuelo)
                .idAsiento(asiento)
                .idUsuario(usuario)
                .precioTotal(200000)
                .estadoPago("T")
                .fecha(new Date())
                .estado("A")
                .build();

        Mockito.when(reservaRepository.existsById(1)).thenReturn(true);
        Mockito.when(reservaRepository.getReferenceById(1)).thenReturn(reservaTest);

        // Llamado al método a probar
        ReservaDTO reservaDTO = reservaService.obtenerReserva(1);

        // Verificación del resultado esperado
        assertEquals(1, reservaDTO.getIdReserva());
    }

    //Prueba unitaria menos probable (mala)
    @Test
    void obtenerReserva_estadoNoActivo() throws Exception {
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();
        Vuelo vuelo = Vuelo.builder().idVuelo(1).idAeropuertoOrigen(aeropuertoOrigen).idAeropuertoDestino(aeropuertoDestino).precio(10000).fechaHoraSalida(new Date()).fechaHoraLlegada(new Date()).precioAsientoPreferencial(80000).precioAsientoVip(60000).precioAsientoTurista(30000).estado("A").build();
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();
        Asiento asiento = Asiento.builder().idAsiento(1).idTipoa(tipoAsiento).idAvion(avion).ubicacion("Cai").precio(30000).estado("D").build();
        RolUsuario rolUsuario = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        Usuario usuario = Usuario.builder().idUsuario(1).idRolUsuario(rolUsuario).cedula("1112243").nombre("Prueba").apellido("APrueba").correo("prueba@gmail.com").estado("A").build();

        Reserva reservaTest = Reserva.builder()
                .idReserva(1)
                .idVuelo(vuelo)
                .idAsiento(asiento)
                .idUsuario(usuario)
                .precioTotal(200000)
                .estadoPago("T")
                .fecha(new Date())
                .estado("D")
                .build();

        Mockito.when(reservaRepository.existsById(1)).thenReturn(true);
        Mockito.when(reservaRepository.getReferenceById(1)).thenReturn(reservaTest);

        // Llamado al método a probar
        ReservaDTO reservaDTO = reservaService.obtenerReserva(1);

        // Verificación del resultado esperado
        assertEquals("D", reservaDTO.getEstado());
    }

    //Prueba unitaria buena
    @Test
    void obtenerReservas() throws Exception {
        Aeropuerto aeropuertoOrigen = Aeropuerto.builder().idAeropuerto(1).nombre("Cali").iata("DTW").ubicacion("Cali").estado("A").build();
        Aeropuerto aeropuertoDestino = Aeropuerto.builder().idAeropuerto(2).nombre("Medellin").iata("MDL").ubicacion("Medellin").estado("A").build();
        Vuelo vuelo = Vuelo.builder().idVuelo(1).idAeropuertoOrigen(aeropuertoOrigen).idAeropuertoDestino(aeropuertoDestino).precio(10000).fechaHoraSalida(new Date()).fechaHoraLlegada(new Date()).precioAsientoPreferencial(80000).precioAsientoVip(60000).precioAsientoTurista(30000).estado("A").build();
        TipoAsiento tipoAsiento = TipoAsiento.builder().idTipoa(1).descripcion("VIP").estado("A").build();
        Avion avion = Avion.builder().idAvion(1).aerolineaAvion("Avianca").estado("A").build();
        Asiento asiento = Asiento.builder().idAsiento(1).idTipoa(tipoAsiento).idAvion(avion).ubicacion("Cai").precio(30000).estado("D").build();
        RolUsuario rolUsuario = RolUsuario.builder().idRolusuario(1).descripcion("RolPrueba").estado("A").build();
        Usuario usuario = Usuario.builder().idUsuario(1).idRolUsuario(rolUsuario).cedula("1112243").nombre("Prueba").apellido("APrueba").correo("prueba@gmail.com").estado("A").build();

        List<Reserva> reservaList = Arrays.asList(Reserva.builder()
                        .idReserva(1)
                        .idVuelo(vuelo)
                        .idAsiento(asiento)
                        .idUsuario(usuario)
                        .precioTotal(200000)
                        .estadoPago("T")
                        .fecha(new Date())
                        .estado("A")
                        .build(),
                Reserva.builder()
                        .idReserva(2)
                        .idVuelo(vuelo)
                        .idAsiento(asiento)
                        .idUsuario(usuario)
                        .precioTotal(200000)
                        .estadoPago("T")
                        .fecha(new Date())
                        .estado("A")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(reservaRepository.findAll()).thenReturn(reservaList);

        List<ReservaDTO> reservaDTOS = reservaService.obtenerReservas();

        assertEquals(2, reservaDTOS.size());
        assertEquals(2, reservaList.get(1).getIdReserva());

    }

    //Prueba unitaria mala
    @Test
    void obtenerReserva_listaVacia() throws Exception {
        List<Reserva> reservaList = new ArrayList<>();

        // Mock de la respuesta del repositorio
        Mockito.when(reservaRepository.findAll()).thenReturn(reservaList);

        List<ReservaDTO> reservas = reservaService.obtenerReservas();

        assertTrue(reservas.isEmpty());
    }

}
