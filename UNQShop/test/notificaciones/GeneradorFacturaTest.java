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

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        generador = new GeneradorFactura();
        mockPedido = mock(Pedido.class);
        mockEstadoAnterior = mock(EstadoPedido.class);
    }

    @Test
    public void testGeneradorCreaFacturaSiElEstadoEsEntregado() {
        // 2. EXERCISE 
        EstadoPedido nuevoEstado = new EstadoEntregado();
        generador.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
        
        // 3. VERIFY
        // verifico que el pedido mockeado no sufra interacciones no deseadas
        verifyNoInteractions(mockPedido); 
    }

    @Test
    public void testGeneradorNoHaceNadaSiElEstadoEsDistintoAEntregado() {
        // 2. EXERCISE 
        EstadoPedido nuevoEstado = new EstadoConfirmado(mockPedido);
        generador.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
    }
}