package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pedido.EstadoCancelado;
import pedido.EstadoConfirmado;
import pedido.EstadoEntregado;
import pedido.EstadoEnviado;
import pedido.EstadoPedido;
import pedido.Pedido;

public class NotificadorEmailTest {
    
    private NotificadorEmail notificador;
    private MailSender mockMailSender;
    private Pedido mockPedido;
    private EstadoPedido mockEstadoAnterior;

    @BeforeEach
    public void setUp() {
        // 1. SETUP: Creo los Mocks
        mockMailSender = mock(MailSender.class);
        mockPedido = mock(Pedido.class);
        mockEstadoAnterior = mock(EstadoPedido.class);
        
        // SUT
        notificador = new NotificadorEmail(mockMailSender);
    }

    @Test
    public void testNotificadorEnviaMailSiElEstadoEsConfirmado() {
        // 2. EXERCISE
        notificador.actualizar(mockPedido, mockEstadoAnterior, new EstadoConfirmado());
        // 3. VERIFY: Verifico la interacción con el mock
        verify(mockMailSender, times(1)).enviar(anyString());
    }

    @Test
    public void testNotificadorEnviaMailSiElEstadoEsEnviado() {
        // 2. EXERCISE
        notificador.actualizar(mockPedido, mockEstadoAnterior, new EstadoEnviado());
        // 3. VERIFY
        verify(mockMailSender, times(1)).enviar(anyString());
    }

    @Test
    public void testNotificadorEnviaMailSiElEstadoEsEntregado() {
        // 2. EXERCISE
        notificador.actualizar(mockPedido, mockEstadoAnterior, new EstadoEntregado());
        // 3. VERIFY
        verify(mockMailSender, times(1)).enviar(anyString());
    }

    @Test
    public void testNotificadorNoEnviaMailSiElEstadoEsCancelado() {
        // 2. EXERCISE
        notificador.actualizar(mockPedido, mockEstadoAnterior, new EstadoCancelado());
        // 3. VERIFY: Asegura que nunca se envíe un mail por error
        verify(mockMailSender, never()).enviar(anyString());
    }
}