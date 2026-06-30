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

public class FormateadorTextoPlanoTest {

    private FormateadorTextoPlano formateador;
    private Producto mockProducto;
    private Paquete mockPaquete;
    private Producto mockProductoSinVentas;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        mockProducto = mock(Producto.class);
        mockPaquete = mock(Paquete.class);
        mockProductoSinVentas = mock(Producto.class); 

        // Configuro el comportamiento de los stubs
        when(mockProducto.getNombre()).thenReturn("Zapatillas");
        when(mockPaquete.getNombre()).thenReturn("Kit Escolar");
        when(mockProductoSinVentas.getNombre()).thenReturn("Medias");

        // Preparo el map de ventas 
        Map<ItemCatalogo, Integer> mapVentas = new HashMap<>();
        mapVentas.put(mockProducto, 10);
        mapVentas.put(mockPaquete, 5);
        // No agrego mockProductoSinVentas al map por estar usando getOrDefault(p, 0)

        formateador = new FormateadorTextoPlano(mapVentas);
    }

    @Test
    public void testFormateadorGeneraReporteConItemsVendidosYSinVentas() {
        // 2. EXERCISE
        formateador.visitarProducto(mockProducto);
        formateador.visitarPaquete(mockPaquete);
        formateador.visitarProducto(mockProductoSinVentas); 
        
        String reporte = formateador.obtenerReporte();

        // 3. VERIFY
        // Compruebo la estructura del texto 
        assertTrue(reporte.contains("--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---"));
        assertTrue(reporte.contains("- Producto: Zapatillas | Unidades vendidas: 10"));
        assertTrue(reporte.contains("- Paquete: Kit Escolar | Unidades vendidas: 5"));
        assertTrue(reporte.contains("- Producto: Medias | Unidades vendidas: 0")); 
        
        // Me aseguro que el formateador le pidió el nombre a los items
        verify(mockProducto).getNombre();
        verify(mockPaquete).getNombre();
        verify(mockProductoSinVentas).getNombre();
    }
}
