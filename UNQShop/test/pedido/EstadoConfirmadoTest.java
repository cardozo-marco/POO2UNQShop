package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;


class EstadoConfirmadoTest {

    private EstadoConfirmado sut; 
    private Pedido pedidoMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemCatalogo.class);
        
        // Configuramos el pedido para que devuelva una lista con un ítem
        List<ItemCatalogo> items = Arrays.asList(itemMock);
        when(pedidoMock.getItems()).thenReturn(items);

        // El constructor de EstadoConfirmado reduce el stock automáticamente [1]
        sut = new EstadoConfirmado(pedidoMock);
    }

    @Test
    void testConstructor_ReduceElStockDeLosItemsAlCrearse() {
        // Verificamos que se llamó a reducirStock() en el ítem durante el setUp
        verify(itemMock, times(1)).reducirStock();
    }

    @Test
    void testPreparar_CambiaAEstadoEnPreparacion() {
        sut.preparar(pedidoMock);
        
        // Verificamos la transición según el requerimiento 2.2
        verify(pedidoMock).setEstado(any(EstadoEnPreparacion.class));
    }

    @Test
    void testCancelar_ReponeStockYGeneraNotaDeCredito() {
        when(pedidoMock.calcularTotal()).thenReturn(500.0);
        
        NotaDeCredito nc = sut.cancelar(pedidoMock);

        // Assert: 1. Repone stock [2]
        verify(itemMock).reponerStock();
        
        // transición a Cancelado
        verify(pedidoMock).setEstado(any(EstadoCancelado.class));
        
        // Valida la Nota de Crédito
        assertNotNull(nc);
        assertEquals(500.0, nc.getMontoReembolso(), "Debe reembolsar el total en este estado");
    }

    // TESTS DE EXCEPCIONES

    @Test
    void testAgregarItem_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.agregarItem(pedidoMock, itemMock));
    }

    @Test
    void testQuitarItem_LanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> sut.quitarItem(pedidoMock, itemMock));
    }

    @Test
    void testConfirmar_LanzaExcepcionSiYaFueConfirmado() {
        assertThrows(OperacionInvalidaException.class, () -> sut.confirmar(pedidoMock));
    }
    
    @Test
    void testEnviar_LanzaExcepcion() {
        // Al llamar a enviar en este estado, debe ejecutarse el 'throw new...' del src
        assertThrows(OperacionInvalidaException.class, () -> sut.enviar(pedidoMock));
    }

    @Test
    void testEntregar_LanzaExcepcion() {
        // Al llamar a entregar en este estado, debe ejecutarse el 'throw new...' del src
        assertThrows(OperacionInvalidaException.class, () -> sut.entregar(pedidoMock));
    }
}