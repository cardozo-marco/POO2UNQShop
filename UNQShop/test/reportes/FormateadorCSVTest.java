package reportes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

        formateador = new FormateadorCSV();
    }

    @Test
    public void testFormateadorCSVGeneraReporte() {
        // 2. EXERCISE
        formateador.visitarProducto(mockProducto, 3, 100.50);
        formateador.visitarPaquete(mockPaquete, 1, 950.0);
        formateador.visitarPaquete(mockPaqueteSinVentas, 0, 0.0);
        
        String reporte = formateador.obtenerReporte();

        // 3. VERIFY
        // Verifico cabecera y estructura 
        assertTrue(reporte.contains("Item,CantidadVendida,PrecioPromedio"));
        assertTrue(reporte.contains("Monitor,3,100.50"));
        assertTrue(reporte.contains("Combo Gamer (Paquete),1,950.00"));
        assertTrue(reporte.contains("Kit Oficina (Paquete),0,0.00"));
        
        verify(mockProducto).getNombre();
        verify(mockPaquete).getNombre();
        verify(mockPaqueteSinVentas).getNombre();
    }
}