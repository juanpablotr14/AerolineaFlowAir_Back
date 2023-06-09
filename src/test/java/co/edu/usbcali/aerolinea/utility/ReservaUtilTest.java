package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.domain.Reserva;
import co.edu.usbcali.aerolinea.dto.ReservaDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReservaUtilTest {
    public static Integer CODIGO1 = 1;
    public static String FECHA_FUTURO = "2023-08-12 08:00";
    public static String PATTERN_FECHA = "yyyy-MM-dd HH:mm";
    public static Date FECHA_FUTURO_DATE;
    public static String ID_NULL = "El id de la reserva es invalida!";
    public static String ID_INVALID = "La reserva con id %s no existe";

    static {
        try {
            FECHA_FUTURO_DATE = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Reserva reserva1 = Reserva.builder()
            .idReserva(1).idVuelo(VueloUtilTest.vuelo1).idAsiento(AsientoUtilTest.asiento1).idUsuario(UsuarioUtilTest.Usuario1).precioTotal(200000).estadoPago("A").fecha(FECHA_FUTURO_DATE).estado("A").build();
    public static ReservaDTO reservaDTO1 = ReservaDTO.builder()
            .idReserva(1).idVuelo(VueloUtilTest.vuelo1.getIdVuelo()).idAsiento(AsientoUtilTest.asiento1.getIdAsiento()).idUsuario(UsuarioUtilTest.Usuario1.getIdUsuario()).precioTotal(130000).estadoPago("A").fecha(FECHA_FUTURO_DATE).estado("A").build();
    public static ReservaDTO reservaDTO_null = ReservaDTO.builder()
            .idReserva(null).idVuelo(VueloUtilTest.vuelo1.getIdVuelo()).idAsiento(AsientoUtilTest.asiento1.getIdAsiento()).idUsuario(UsuarioUtilTest.Usuario1.getIdUsuario()).precioTotal(160000).estadoPago("A").fecha(FECHA_FUTURO_DATE).estado("A").build();

    public static List<ReservaDTO> reservasDTOS = Arrays.asList(reservaDTO1);

    public static List<ReservaDTO> reservaDTO_vacio = Arrays.asList();

}
