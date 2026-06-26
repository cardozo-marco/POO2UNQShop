package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaqueteTest {
    private Paquete paquete;
    private ItemCatalogo itemMock1;
    private ItemCatalogo itemMock2;

    @BeforeEach
    public void setUp() {
        // Paquete con 10% de descuento
        paquete = new Paquete("Combo Tech", "Smartphone + Funda", 0.10);
        
        // Mockeamos los items hoja para probar solo la lógica del Composite (Paquete)
        itemMock1 = mock(ItemCatalogo.class);
        itemMock2 = mock(ItemCatalogo.class);
        
        when(itemMock1.getPrecioFinal()).thenReturn(1000.0);
        when(itemMock1.getPeso()).thenReturn(0.5);
        
        when(itemMock2.getPrecioFinal()).thenReturn(200.0);
        when(itemMock2.getPeso()).thenReturn(0.2);
        
        paquete.agregarItem(itemMock1);
        paquete.agregarItem(itemMock2);
    }

    @Test
    public void testGetPrecioFinalConDescuento() {
        // Suma de precios finales: 1000 + 200 = 1200
        // Descuento del 10%: 1200 * 0.90 = 1080.0
        assertEquals(1080.0, paquete.getPrecioFinal());
    }

    @Test
    public void testGetPesoEsLaSumaDeLosPesosDeLosItems() {
        // Suma de pesos: 0.5 + 0.2 = 0.7
        assertEquals(0.7, paquete.getPeso(), 0.01);
    }
}
