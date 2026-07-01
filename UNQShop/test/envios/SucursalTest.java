package envios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

public class SucursalTest {

    private Sucursal sucursal;
    private Pedido mockPedido;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        sucursal = new Sucursal("Sucursal Bernal", "Roque Sáenz Peña 352");
        mockPedido = mock(Pedido.class);
    }

    @Test
    public void testSucursalRetornaSusDatosCorrectamente() {
        // VERIFY
        assertEquals("Sucursal Bernal", sucursal.getNombre());
        assertEquals("Roque Sáenz Peña 352", sucursal.getDireccion());
    }

    @Test
    public void testTieneStockRetornaTruePorDefecto() {
        assertTrue(sucursal.tieneStock(mockPedido));
    }
}