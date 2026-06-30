package pago;

import pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BilleteraVirtualTest {
    private BilleteraVirtual metodoPago;
    private BilleteraVirtualAPI apiMock;
    private Pedido pedidoMock;

    @BeforeEach
    public void setUp() {
        apiMock = mock(BilleteraVirtualAPI.class);
        pedidoMock = mock(Pedido.class);
        metodoPago = new BilleteraVirtual("usuario@mail.com", apiMock);
        
        when(pedidoMock.calcularTotal()).thenReturn(2000.0);
    }

    @Test
    public void testProcesarPagoEnviaNotificacionPush() {
        when(apiMock.validarSaldo(anyString(), anyDouble())).thenReturn(true);
        when(apiMock.bloquearSaldo(anyString(), anyDouble())).thenReturn(true);
        when(apiMock.acreditar(anyString(), anyDouble())).thenReturn(true);

        assertDoesNotThrow(() -> metodoPago.procesarPago(pedidoMock));
        
        verify(apiMock, times(1)).validarSaldo("usuario@mail.com", 2000.0);
        verify(apiMock, times(1)).bloquearSaldo("usuario@mail.com", 2000.0);
        verify(apiMock, times(1)).acreditar("usuario@mail.com", 2000.0);
        
        verify(apiMock, times(1)).enviarNotificacionPush(eq("usuario@mail.com"), anyString());
        verify(pedidoMock, times(1)).setCodigoTransaccion(anyString());
    }
}
