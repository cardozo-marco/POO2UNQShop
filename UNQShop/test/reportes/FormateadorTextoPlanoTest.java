package reportes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

        formateador = new FormateadorTextoPlano();
    }

    @Test
    public void testFormateadorGeneraReporteConItemsVendidosYSinVentas() {
        // 2. EXERCISE
        formateador.visitarProducto(mockProducto, 10, 100.50);
        formateador.visitarPaquete(mockPaquete, 5, 950.0);
        formateador.visitarProducto(mockProductoSinVentas, 0, 0.0); 
        
        String reporte = formateador.obtenerReporte();

        // 3. VERIFY
        // Compruebo la estructura del texto 
        assertTrue(reporte.contains("--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---"));
        assertTrue(reporte.contains("- Producto: Zapatillas | Unidades vendidas: 10 | Precio Promedio: $100.50"));
        assertTrue(reporte.contains("- Paquete: Kit Escolar | Unidades vendidas: 5 | Precio Promedio: $950.00"));
        assertTrue(reporte.contains("- Producto: Medias | Unidades vendidas: 0 | Precio Promedio: $0.00")); 
        
        // Me aseguro que el formateador le pidió el nombre a los items
        verify(mockProducto).getNombre();
        verify(mockPaquete).getNombre();
        verify(mockProductoSinVentas).getNombre();
    }
}
