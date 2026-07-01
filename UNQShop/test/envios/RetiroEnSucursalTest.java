package envios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

public class RetiroEnSucursalTest {

    private RetiroEnSucursal retiroEnSucursal;
    private Sucursal mockSucursal;
    private Pedido mockPedido;

    @BeforeEach
    public void setUp() {
        mockSucursal = mock(Sucursal.class);
        mockPedido = mock(Pedido.class);
        retiroEnSucursal = new RetiroEnSucursal(mockSucursal);
    }

    @Test
    public void testCalcularCostoSiempreEsCero() {
        float costo = retiroEnSucursal.calcularCosto(mockPedido);
        assertEquals(0f, costo); 
    }

    @Test
    public void testEstimarDiasEntregaEsInmediatoSiHayStock() {
        when(mockSucursal.tieneStock(mockPedido)).thenReturn(true);

        int dias = retiroEnSucursal.estimarDiasEntrega(mockPedido);
        assertEquals(0, dias); 
    }

    @Test
    public void testEstimarDiasEntregaEsDeTresDiasSiNoHayStock() {
        when(mockSucursal.tieneStock(mockPedido)).thenReturn(false);
        int dias = retiroEnSucursal.estimarDiasEntrega(mockPedido);
        assertEquals(3, dias); // Hasta 3 días 
    }
}