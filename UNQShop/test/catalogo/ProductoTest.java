package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    private Producto producto;

    @BeforeEach
    public void setUp() {
        // Producto con precio base 1000 y peso 0.5kg
        producto = new Producto("SKU123", "Smartphone", 1000.0, 0.5);
    }

    @Test
    public void testGetPrecioFinalSinDescuento() {
        assertEquals(1000.0, producto.getPrecioFinal());
    }

    @Test
    public void testGetPeso() {
        assertEquals(0.5, producto.getPeso());
    }

    @Test
    public void testValidarProductoCorrecto() {
        assertTrue(producto.validar());
    }
}
