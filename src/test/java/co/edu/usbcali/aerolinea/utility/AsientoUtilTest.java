package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.domain.Asiento;
import co.edu.usbcali.aerolinea.dto.AsientoDTO;

import java.util.Arrays;
import java.util.List;

public class AsientoUtilTest {
    public static Integer CODIGO1 = 1;
    public static Integer CODIGO2 = 2;
    public static String UBICACION = "Al lado de la ventana";
    public static long PRECIO = 130000;
    public static String ESTADO = "A";
    public static String UBICACION_MALO = "La ubicación del asiento no valida";
    public static String ID_NULL = "El asiento con id %s no existe";

    public static Asiento asiento1 = Asiento.builder()
            .idAsiento(CODIGO1).idTipoa(TipoAsientoUtilTest.Tipoasiento1).idAvion(AvionUtilTest.Avion1).ubicacion(UBICACION).precio(PRECIO).estado(ESTADO).build();
    public static Asiento asiento_malo = Asiento.builder()
            .idTipoa(TipoAsientoUtilTest.Tipoasiento1).idAvion(AvionUtilTest.Avion1).ubicacion(UBICACION).precio(PRECIO).estado(ESTADO).build();

    public static List<Asiento> asientoList = Arrays.asList(asiento1, asiento_malo);

    public static AsientoDTO asientoDTO = AsientoDTO.builder()
            .idAsiento(CODIGO1).idTipoa(TipoAsientoUtilTest.CODIGO1).idAvion(AvionUtilTest.CODIGO1).ubicacion(UBICACION).precio(PRECIO).estado(ESTADO).build();

    public static AsientoDTO asientoDTO_malo = AsientoDTO.builder()
            .idAsiento(CODIGO1).idTipoa(TipoAsientoUtilTest.CODIGO1).idAvion(AvionUtilTest.CODIGO2).ubicacion(null).precio(PRECIO).estado(ESTADO).build();

    public static List<AsientoDTO> asientosDTO = Arrays.asList(asientoDTO, asientoDTO_malo);
    public static List<AsientoDTO> asientos_vacio = Arrays.asList();
}
