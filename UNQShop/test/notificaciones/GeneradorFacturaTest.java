package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pedido.EstadoConfirmado;
import pedido.EstadoEntregado;
import pedido.EstadoPedido;
import pedido.Pedido;

public class GeneradorFacturaTest {

    private GeneradorFactura generador;
    private Pedido mockPedido;
    private EstadoPedido mockEstadoAnterior;
    private entidades.Cliente mockCliente;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        generador = new GeneradorFactura();
        mockPedido = mock(Pedido.class);
        mockEstadoAnterior = mock(EstadoPedido.class);
        mockCliente = mock(entidades.Cliente.class);
        when(mockPedido.getCliente()).thenReturn(mockCliente);
        when(mockPedido.getItems()).thenReturn(java.util.Collections.emptyList());
    }

    @Test
    public void testGeneradorCreaFacturaSiElEstadoEsEntregado() {
        // 2. EXERCISE 
        EstadoPedido nuevoEstado = new EstadoEntregado();
        generador.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
        
        // 3. VERIFY
        verify(mockCliente, times(1)).recibirFactura(any(entidades.Factura.class));
    }

    @Test
    public void testGeneradorNoHaceNadaSiElEstadoEsDistintoAEntregado() {
        // 2. EXERCISE 
        EstadoPedido nuevoEstado = new EstadoConfirmado(mockPedido);
        generador.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
    }
}