package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;

class EstadoEnPreparacionTest {

    private EstadoEnPreparacion sut;
    private Pedido pedidoMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        sut = new EstadoEnPreparacion();
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemCatalogo.class);
    }

    @Test
    void testEnviar_CambiaAEstadoEnviado() {
        sut.enviar(pedidoMock);

        // transición válida 
        verify(pedidoMock).setEstado(any(EstadoEnviado.class));
    }

    @Test
    void testCancelar_ReponeStockYReembolsaMontoTotal() {
        // Configuramos el pedido con un ítem y un monto total
        when(pedidoMock.getItems()).thenReturn(Arrays.asList(itemMock));
        when(pedidoMock.calcularTotal()).thenReturn(2000.0);

        NotaDeCredito nc = sut.cancelar(pedidoMock);

        // 1. Debe reponer stock de los ítems
        verify(itemMock).reponerStock();
        
        // 2. Debe generar nota de credito por el monto TOTAL (productos + envío)
        assertEquals(2000.0, nc.getMontoReembolso());
        
        // 3. Debe transicionar a Cancelado
        verify(pedidoMock).setEstado(any(EstadoCancelado.class));
    }

    // TESTS DE EXCEPCIONES 

    @Test
    void testOperacionesInvalidas_LanzanExcepcion() {
        assertAll("Verifica que todas las operaciones prohibidas lancen OperacionInvalidaException",
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.agregarItem(pedidoMock, itemMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.quitarItem(pedidoMock, itemMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.confirmar(pedidoMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.preparar(pedidoMock)),
            () -> assertThrows(OperacionInvalidaException.class, () -> sut.entregar(pedidoMock))
        );
    }
}