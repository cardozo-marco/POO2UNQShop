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

    @Test
    public void testGettersBasicos() {
        assertEquals("Smartphone", producto.getNombre());
        assertEquals("Producto: Smartphone - SKU: SKU123", producto.getDescripcion());
        assertEquals(1000.0, producto.getPrecioBase());
        assertNull(producto.getMarca());
        assertNull(producto.getCategoria());
        assertEquals(0, producto.getStock());
    }

    @Test
    public void testAtributosDinamicos() {
        assertTrue(producto.getAtributosDinamicos().isEmpty());
        producto.agregarAtributoDinamico("Color", "Negro");
        assertEquals("Negro", producto.getAtributosDinamicos().get("Color"));
    }

    @Test
    public void testValidarInvalidoPorAtributoDinamicoNulo() {
        producto.agregarAtributoDinamico("Color", null);
        assertFalse(producto.validar());
    }

    @Test
    public void testValidarInvalidoPorSKUVacio() {
        Producto p = new Producto("", "Nombre", 100.0, 1.0);
        assertFalse(p.validar());
        Producto p2 = new Producto(null, "Nombre", 100.0, 1.0);
        assertFalse(p2.validar());
    }

    @Test
    public void testValidarInvalidoPorNombreVacio() {
        Producto p = new Producto("SKU", "", 100.0, 1.0);
        assertFalse(p.validar());
        Producto p2 = new Producto("SKU", null, 100.0, 1.0);
        assertFalse(p2.validar());
    }

    @Test
    public void testStock() {
        assertEquals(0, producto.getStock());
        producto.reponerStock();
        assertEquals(1, producto.getStock());
        producto.reducirStock();
        assertEquals(0, producto.getStock());
    }

    @Test
    public void testAceptar() {
        reportes.FormateadorReporte mockVisitante = org.mockito.Mockito.mock(reportes.FormateadorReporte.class);
        producto.aceptar(mockVisitante, 5, 950.0);
        org.mockito.Mockito.verify(mockVisitante, org.mockito.Mockito.times(1)).visitarProducto(producto, 5, 950.0);
    }
}
