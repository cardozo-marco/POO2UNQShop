package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pago.MetodoPago;
import catalogo.ItemCatalogo;

class PedidoTest {
	private Pedido pedido;
    private Cliente clienteMock;
    private MetodoEnvio envioMock;
    private MetodoPago pagoMock;
    private PedidoObserver observadorMock;

    @BeforeEach
    public void setUp() {
        clienteMock = mock(Cliente.class);
        envioMock = mock(MetodoEnvio.class);
        pagoMock = mock(MetodoPago.class);
        observadorMock = mock(PedidoObserver.class);
        
        pedido = new Pedido(clienteMock, envioMock, pagoMock);
        pedido.agregarObservador(observadorMock);
    }

    @Test
    public void testCalcularTotalDelegaEnEnvioYSubtotal() {
        // Configuramos un ítem con precio final 100
        ItemCatalogo item = mock(ItemCatalogo.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        pedido.agregarItem(item);
        
        // El envío con costo 50
        when(envioMock.calcularCosto(pedido)).thenReturn(50.0);

        assertEquals(150.0, pedido.calcularTotal(), "El total debe ser subtotal + envio");
    }

    @Test
    public void testSetEstadoNotificaCambioCorrectamente() {
        EstadoPedido nuevoEstado = mock(EstadoPedido.class);
        EstadoPedido estadoAnterior = pedido.getEstado();
        
        pedido.setEstado(nuevoEstado);

        // Verificamos si se envía el mensaje actualizar
        verify(observadorMock).actualizar(pedido, estadoAnterior, nuevoEstado);
        assertEquals(nuevoEstado, pedido.getEstado());
    }
    
    @Test
    public void testNoSePuedeEntregarUnPedidoQueAunEstaEnBorrador() {
        // El pedido nace en Borrador
        assertThrows(RuntimeException.class, () -> { pedido.entregar(); }, "No se puede entregar sin haber pasado por confirmado, preparación y enviado");
    }
    
    @Test
    public void testCancelarPedidoEnPreparacionGeneraYRegistraNotaDeCredito() {   
        pedido.confirmar(); 
        pedido.preparar(); //esta en EstadoEnPreparacion
        
        double totalEsperado = pedido.calcularTotal();

        NotaDeCredito nc = pedido.cancelar();

        assertNotNull(nc, "Debe generarse una nota de crédito");
        assertEquals(nc, pedido.getNotaDeCredito(), "La nota debe quedar registrada en el pedido");
        assertEquals(totalEsperado, nc.getMontoReembolso(), "El reembolso en preparación es total");
    }
       
}
