package reportes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorCSVTest {

    private FormateadorCSV formateador;
    private Producto mockProducto;
    private Paquete mockPaquete;
    private Paquete mockPaqueteSinVentas;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        mockProducto = mock(Producto.class);
        mockPaquete = mock(Paquete.class);
        mockPaqueteSinVentas = mock(Paquete.class);

        when(mockProducto.getNombre()).thenReturn("Monitor");
        when(mockPaquete.getNombre()).thenReturn("Combo Gamer");
        when(mockPaqueteSinVentas.getNombre()).thenReturn("Kit Oficina");

        Map<ItemCatalogo, Integer> mapVentas = new HashMap<>();
        mapVentas.put(mockProducto, 3);
        mapVentas.put(mockPaquete, 1);

        formateador = new FormateadorCSV(mapVentas);
    }

    @Test
    public void testFormateadorCSVGeneraReporte() {
        // 2. EXERCISE
        formateador.visitarProducto(mockProducto);
        formateador.visitarPaquete(mockPaquete);
        formateador.visitarPaquete(mockPaqueteSinVentas);
        
        String reporte = formateador.obtenerReporte();

        // 3. VERIFY
        // Verifico cabecera y estructura 
        assertTrue(reporte.contains("Item,CantidadVendida"));
        assertTrue(reporte.contains("Monitor,3"));
        assertTrue(reporte.contains("Combo Gamer (Paquete),1"));
        assertTrue(reporte.contains("Kit Oficina (Paquete),0"));
        
        verify(mockProducto).getNombre();
        verify(mockPaquete).getNombre();
        verify(mockPaqueteSinVentas).getNombre();
    }
}