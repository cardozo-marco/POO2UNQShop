package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pedido.EstadoCancelado;
import pedido.EstadoConfirmado;
import pedido.EstadoPedido;
import pedido.Pedido;

public class FidelizacionTest {

    private Fidelizacion fidelizacion;
    private Pedido mockPedido;
    private EstadoPedido mockEstadoAnterior;
    private entidades.Cliente mockCliente;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        fidelizacion = new Fidelizacion();
        mockPedido = mock(Pedido.class);
        mockEstadoAnterior = mock(EstadoPedido.class);
        mockCliente = mock(entidades.Cliente.class);
        when(mockPedido.getCliente()).thenReturn(mockCliente);
    }

    @Test
    public void testFidelizacionEnviaCuponSiElEstadoEsCancelado() {
        // 2. EXERCISE
        EstadoPedido nuevoEstado = new EstadoCancelado();
        fidelizacion.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
        
        // 3. VERIFY
        verify(mockCliente, times(1)).notificar(anyString());
    }

    @Test
    public void testFidelizacionNoHaceNadaSiElEstadoEsConfirmado() {
        // 2. EXERCISE
        EstadoPedido nuevoEstado = new EstadoConfirmado(mockPedido); 
        
        fidelizacion.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
    }
}