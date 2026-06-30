package reportes;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorHTMLTest {

    @Test
    public void testProductoAceptaAlVisitanteYHaceDobleDespacho() {
        // 1. Instancio el SUT 
    	Producto producto = new Producto("SKU-1234","Zapatillas", 5000.0, 1.2);  
        
        // Creo un Mock de la interfaz del visitante
        FormateadorReporte mockFormateador = mock(FormateadorReporte.class);

        // 2. Ejecutamos el método que queremos probar
        producto.aceptar(mockFormateador);

        // 3. Verifico con Mockito que el producto llamó al método visitarProducto pasándose a sí mismo
        verify(mockFormateador).visitarProducto(producto);
              
    }
    
    @Test
    public void testFormateadorHTMLExtraeDatosYGeneraReporte() {
        // 1. SETUP
        Producto mockProducto = mock(Producto.class);
        when(mockProducto.getNombre()).thenReturn("Zapatillas");
        
        // Creo el mapa de ventas que simula lo que entregaría el Reporte
        Map<ItemCatalogo, Integer> mapVentas = new HashMap<>();
        mapVentas.put(mockProducto, 10); // El mockProducto se vendió 10 veces

        // Instancio el formateador inyectándole el map
        FormateadorHTML formateador = new FormateadorHTML(mapVentas);

        // 2. EXERCISE
        formateador.visitarProducto(mockProducto);
        String resultadoHTML = formateador.obtenerReporte();

        // 3. VERIFY
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("Zapatillas"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("10"));
    }
    
    
    @Test
    public void testPaqueteAceptaVisitanteYDelegaASusItems() {
        // 1. 
        Paquete paquete = new Paquete("Kit Home Office", "Pack de oficina", 0.15); 
        
        ItemCatalogo mockItem1 = mock(ItemCatalogo.class);
        ItemCatalogo mockItem2 = mock(ItemCatalogo.class);
        
        // Creo un Mock del visitante (FormateadorReporte)
        FormateadorReporte mockFormateador = mock(FormateadorReporte.class);

        // Armo el árbol: agrego los hijos simulados al paquete real.
        paquete.agregarItem(mockItem1);
        paquete.agregarItem(mockItem2);

        // 2.Ejecuto el método aceptar sobre el paquete
        paquete.aceptar(mockFormateador);

        // 3.
        // A. Verifico que el paquete recorrió sus hijos y les pasó el visitante
        verify(mockItem1).aceptar(mockFormateador);
        verify(mockItem2).aceptar(mockFormateador);
        
        // B. Verifico el doble-despacho: el paquete se visitó a sí mismo
        verify(mockFormateador).visitarPaquete(paquete);
    }
    
    @Test
    public void testFormateadorHTMLExtraeDatosDelPaqueteYGeneraReporte() {
        // 1. SETUP
        Paquete mockPaquete = mock(Paquete.class);
        // Simulamos el comportamiento del paquete
        when(mockPaquete.getNombre()).thenReturn("Kit Home Office");
        
        // Creo el mapa de ventas simulado
        Map<ItemCatalogo, Integer> mapaVentas = new HashMap<>();
        mapaVentas.put(mockPaquete, 5); 

        FormateadorHTML formateador = new FormateadorHTML(mapaVentas);

        // 2. EXERCISE
        formateador.visitarPaquete(mockPaquete);
        String resultadoHTML = formateador.obtenerReporte();

        // 3. VERIFY
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("Kit Home Office"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("(Paquete)"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("5"));
    }
}

