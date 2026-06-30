package pago;

import pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TarjetaCreditoTest {
    private TarjetaCredito metodoPago;
    private TarjetaCreditoAPI apiMock;
    private Pedido pedidoMock;

    @BeforeEach
    public void setUp() {
        apiMock = mock(TarjetaCreditoAPI.class);
        pedidoMock = mock(Pedido.class);
        metodoPago = new TarjetaCredito("12345678", "123", "12/25", apiMock);
        
        when(pedidoMock.calcularTotal()).thenReturn(1500.0);
    }

    @Test
    public void testProcesarPagoEjecutaElTemplateMethodYRegistraCupon() {
        // Configuramos la API mockeada para que responda OK a todo
        when(apiMock.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(true);
        when(apiMock.preAutorizar(anyDouble())).thenReturn(true);
        when(apiMock.transferirFondos(anyDouble())).thenReturn(true);

        // Ejecutamos el Template Method
        assertDoesNotThrow(() -> metodoPago.procesarPago(pedidoMock));
        
        // Verificamos que se ejecutaron todos los pasos primitivos obligatorios en la API
        verify(apiMock, times(1)).validarTarjeta(anyString(), anyString(), anyString());
        verify(apiMock, times(1)).preAutorizar(1500.0);
        verify(apiMock, times(1)).transferirFondos(1500.0);
        
        // Verificamos que el Hook generó e intentó guardar el cupón imprimible
        verify(pedidoMock, times(1)).registrarCupon(anyString());
    }

    @Test
    public void testProcesarPagoFallaSiLaValidacionDaFalse() {
        // La tarjeta es rechazada en la primera validación
        when(apiMock.validarTarjeta(anyString(), anyString(), anyString())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> metodoPago.procesarPago(pedidoMock));
    }
}
