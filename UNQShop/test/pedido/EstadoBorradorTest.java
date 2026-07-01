package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;

class EstadoBorradorTest {

    private EstadoBorrador sut; 
    private Pedido pedidoMock;  
    private ItemCatalogo itemMock;

    @BeforeEach
    public void setUp() {
        sut = new EstadoBorrador();
        pedidoMock = mock(Pedido.class); 
        itemMock = mock(ItemCatalogo.class);
    }

    @Test
    public void testAgregarItem_AgregaElItemALaListaDelPedido() {
        // Necesitamos que el pedido devuelva una lista (puedes usar un mock o una real)
        List<ItemCatalogo> items = mock(List.class);
        when(pedidoMock.getItems()).thenReturn(items);

        sut.agregarItem(pedidoMock, itemMock);

        verify(items).add(itemMock);
    }

    @Test
    public void testQuitarItem_RemueveElItemDeLaListaDelPedido() {
        List<ItemCatalogo> items = mock(List.class);
        when(pedidoMock.getItems()).thenReturn(items);

        sut.quitarItem(pedidoMock, itemMock);

        verify(items).remove(itemMock);
    }

    @Test
    public void testConfirmar_ProcesaElPagoYCambiaAEstadoConfirmado() {
        sut.confirmar(pedidoMock);
        
        // primero procesa el pago y luego cambia el estado
        verify(pedidoMock).procesarPago();
        verify(pedidoMock).setEstado(any(EstadoConfirmado.class));
    }

    @Test
    public void testCancelar_CambiaAEstadoCanceladoYDevuelveNull() {
        NotaDeCredito nc = sut.cancelar(pedidoMock);

        // Een Borrador no hay reembolso aun
        verify(pedidoMock).setEstado(any(EstadoCancelado.class));
        assertNull(nc, "La nota de crédito en borrador debe ser null");
    }

    @Test
    public void testPreparar_LanzaExcepcionDeDominio() {
        assertThrows(OperacionInvalidaException.class, () -> sut.preparar(pedidoMock));
    }

    @Test
    public void testEnviar_LanzaExcepcionDeDominio() {
        assertThrows(OperacionInvalidaException.class, () -> sut.enviar(pedidoMock));
    }

    @Test
    public void testEntregar_LanzaExcepcionDeDominio() {
        assertThrows(OperacionInvalidaException.class, () -> sut.entregar(pedidoMock));
    }
}