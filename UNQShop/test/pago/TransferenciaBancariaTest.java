package pago;

import pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransferenciaBancariaTest {
    private TransferenciaBancaria metodoPago;
    private TransferenciaBancariaAPI apiMock;
    private Pedido pedidoMock;

    @BeforeEach
    public void setUp() {
        apiMock = mock(TransferenciaBancariaAPI.class);
        pedidoMock = mock(Pedido.class);
        metodoPago = new TransferenciaBancaria("00012345", "mi.alias", apiMock);
        
        when(pedidoMock.calcularTotal()).thenReturn(500.0);
    }

    @Test
    public void testProcesarPagoExitosoConGeneracionDeCBU() {
        when(apiMock.validarCbuAlias(anyString(), anyString())).thenReturn(true);
        when(apiMock.ejecutarTransferencia(anyDouble())).thenReturn(true);

        assertDoesNotThrow(() -> metodoPago.procesarPago(pedidoMock));
        
        verify(apiMock, times(1)).validarCbuAlias(anyString(), anyString());
        // Reserva de fondos está vacía, pasa directo a la ejecución
        verify(apiMock, times(1)).ejecutarTransferencia(500.0);
        
        verify(pedidoMock, times(1)).registrarComprobante(anyString());
    }
}
