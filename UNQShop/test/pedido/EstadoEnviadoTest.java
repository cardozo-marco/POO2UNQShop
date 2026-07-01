package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;

class EstadoEnviadoTest {

    private EstadoEnviado sut; // System Under Test
    private Pedido pedidoMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        sut = new EstadoEnviado();
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemCatalogo.class);
    }

    @Test
    void testEntregar_CambiaAEstadoEntregado() {
        // Act
        sut.entregar(pedidoMock);

        // Assert: Verifica la transición válida al estado terminal
        verify(pedidoMock).setEstado(any(EstadoEntregado.class));
    }

    @Test
    void testCancelar_ReembolsaSoloSubtotalYCambiaAEstadoCancelado() {
        // Setup: Definimos montos diferentes para subtotal (productos) y total (productos + envío)
        when(pedidoMock.calcularSubtotal()).thenReturn(1000.0);
        when(pedidoMock.calcularTotal()).thenReturn(1250.0);

        // Act
        NotaDeCredito nc = sut.cancelar(pedidoMock);

        // Assert: 1. Debe usar el Subtotal según el requerimiento 2.2 [3]
        assertEquals(1000.0, nc.getMontoReembolso(), "Debe reembolsar solo productos, no el envío");
        
        // 2. Debe transicionar a Cancelado
        verify(pedidoMock).setEstado(any(EstadoCancelado.class));
    }

    // TESTS DE EXCEPCIONES (Para cubrir las ramas de operaciones inválidas en el src)

    @Test
    void testOperacionesInvalidas_LanzanExcepcion() {
        assertAll("Verifica operaciones prohibidas en el estado ENVIADO",
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.agregarItem(pedidoMock, itemMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.quitarItem(pedidoMock, itemMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.confirmar(pedidoMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.preparar(pedidoMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.enviar(pedidoMock))
        );
    }
}