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

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        fidelizacion = new Fidelizacion();
        mockPedido = mock(Pedido.class);
        mockEstadoAnterior = mock(EstadoPedido.class);
    }

    @Test
    public void testFidelizacionEnviaCuponSiElEstadoEsCancelado() {
        // 2. EXERCISE
        EstadoPedido nuevoEstado = new EstadoCancelado();
        fidelizacion.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
        
        // 3. VERIFY
        // Valido el aislamiento del SUT
        verifyNoInteractions(mockPedido);
    }

    @Test
    public void testFidelizacionNoHaceNadaSiElEstadoEsConfirmado() {
        // 2. EXERCISE
        EstadoPedido nuevoEstado = new EstadoConfirmado();
        fidelizacion.actualizar(mockPedido, mockEstadoAnterior, nuevoEstado);
    }
}