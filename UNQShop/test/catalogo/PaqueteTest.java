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

    @Test
    public void testGettersBasicos() {
        assertEquals("Combo Tech", paquete.getNombre());
        assertEquals("Smartphone + Funda", paquete.getDescripcion());
        assertEquals("Paquete", paquete.getCategoria());
        
        when(itemMock1.getPrecioBase()).thenReturn(1100.0);
        when(itemMock2.getPrecioBase()).thenReturn(250.0);
        assertEquals(1350.0, paquete.getPrecioBase());
    }

    @Test
    public void testManejoDeItems() {
        assertEquals(2, paquete.getItems().size());
        paquete.quitarItem(itemMock1);
        assertEquals(1, paquete.getItems().size());
        assertFalse(paquete.getItems().contains(itemMock1));
    }

    @Test
    public void testStockEsElMinimoDeSusItems() {
        when(itemMock1.getStock()).thenReturn(5);
        when(itemMock2.getStock()).thenReturn(2);
        
        assertEquals(2, paquete.getStock());
    }

    @Test
    public void testReducirYReponerStockSeDelegaALosItems() {
        paquete.reducirStock();
        verify(itemMock1, times(1)).reducirStock();
        verify(itemMock2, times(1)).reducirStock();

        paquete.reponerStock();
        verify(itemMock1, times(1)).reponerStock();
        verify(itemMock2, times(1)).reponerStock();
    }

    @Test
    public void testAceptar() {
        reportes.FormateadorReporte mockVisitante = mock(reportes.FormateadorReporte.class);
        paquete.aceptar(mockVisitante, 2, 1000.0);
        verify(mockVisitante, times(1)).visitarPaquete(paquete, 2, 1000.0);
    }
}
