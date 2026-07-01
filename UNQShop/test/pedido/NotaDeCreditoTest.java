package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotaDeCreditoTest {

    private NotaDeCredito sut; // System Under Test
    private Pedido pedidoMock; // DOC
    private double montoReembolso;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        montoReembolso = 1500.50;
        
        // El constructor dispara la lógica de generación del ID y asignación de fecha
        sut = new NotaDeCredito(pedidoMock, montoReembolso);
    }

    @Test
    void testConstructor_InicializaCorrectamenteLosAtributos() {
        // Verification de getters simples
        assertEquals(montoReembolso, sut.getMontoReembolso());
        assertEquals(pedidoMock, sut.getPedidoAsociado());
        assertEquals(LocalDate.now(), sut.getFechaEmision());
    }

    @Test
    void testGetIdNotaCredito_GeneraUnIdConFormatoCorrecto() {
        // El formato esperado es: NC-fecha-hashCodePedido
        String fechaEsperada = LocalDate.now().toString();
        int hashEsperado = pedidoMock.hashCode();
        String idEsperado = "NC-" + fechaEsperada + "-" + hashEsperado;

        assertEquals(idEsperado, sut.getIdNotaCredito(), 
            "El ID de la nota de crédito debe seguir el patrón definido en el constructor");
    }

    @Test
    void testGetFechaEmision_RetornaLaFechaDeHoy() {
        // Aseguramos que la fecha de emisión no sea nula y sea la actual
        assertNotNull(sut.getFechaEmision());
        assertEquals(LocalDate.now(), sut.getFechaEmision());
    }
}