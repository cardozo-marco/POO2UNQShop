package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;

class EstadoEntregadoTest {

    private EstadoEntregado sut; // System Under Test
    private Pedido pedidoMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        sut = new EstadoEntregado();
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemCatalogo.class);
    }

    @Test
    void testAgregarItem_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.agregarItem(pedidoMock, itemMock));
    }

    @Test
    void testQuitarItem_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.quitarItem(pedidoMock, itemMock));
    }

    @Test
    void testConfirmar_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.confirmar(pedidoMock));
    }

    @Test
    void testPreparar_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.preparar(pedidoMock));
    }

    @Test
    void testEnviar_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.enviar(pedidoMock));
    }

    @Test
    void testEntregar_LanzaExcepcionSiYaFueEntregado() {
        assertThrows(OperacionInvalidaException.class, () -> sut.entregar(pedidoMock));
    }

    @Test
    void testCancelar_RetornaNullYNoCambiaEstado() {
        // En estado entregado, la cancelación no genera reembolso ni transiciones
        NotaDeCredito nc = sut.cancelar(pedidoMock);
        
        assertNull(nc, "No debe haber nota de crédito para un pedido ya entregado");
        verify(pedidoMock, never()).setEstado(any());
    }
}