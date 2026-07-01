package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;

class EstadoCanceladoTest {

    private EstadoCancelado sut; // System Under Test
    private Pedido pedidoMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        sut = new EstadoCancelado();
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
    void testEntregar_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.entregar(pedidoMock));
    }

    @Test
    void testCancelar_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.cancelar(pedidoMock));
    }
}
