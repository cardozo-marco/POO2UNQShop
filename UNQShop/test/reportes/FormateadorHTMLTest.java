package reportes;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;


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
        producto.aceptar(mockFormateador, 10);

        // 3. Verifico con Mockito que el producto llamó al método visitarProducto pasándose a sí mismo
        verify(mockFormateador).visitarProducto(producto, 10);
              
    }
    
    @Test
    public void testFormateadorHTMLExtraeDatosYGeneraReporte() {
        // 1. SETUP
        Producto mockProducto = mock(Producto.class);
        when(mockProducto.getNombre()).thenReturn("Zapatillas");
        
        // Instancio el formateador
        FormateadorHTML formateador = new FormateadorHTML();

        // 2. EXERCISE
        formateador.visitarProducto(mockProducto, 10);
        String resultadoHTML = formateador.obtenerReporte();

        // 3. VERIFY
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("Zapatillas"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("10"));
    }
    
    
    @Test
    public void testPaqueteAceptaVisitanteYHaceDobleDespacho() {
        // 1. 
        Paquete paquete = new Paquete("Kit Home Office", "Pack de oficina", 0.15); 
        
        // Creo un Mock del visitante (FormateadorReporte)
        FormateadorReporte mockFormateador = mock(FormateadorReporte.class);

        // 2. Ejecuto el método aceptar sobre el paquete
        paquete.aceptar(mockFormateador, 5);

        // 3. Verifico el doble-despacho
        verify(mockFormateador).visitarPaquete(paquete, 5);
    }
    
    @Test
    public void testFormateadorHTMLExtraeDatosDelPaqueteYGeneraReporte() {
        // 1. SETUP
        Paquete mockPaquete = mock(Paquete.class);
        // Simulamos el comportamiento del paquete
        when(mockPaquete.getNombre()).thenReturn("Kit Home Office");
        
        FormateadorHTML formateador = new FormateadorHTML();

        // 2. EXERCISE
        formateador.visitarPaquete(mockPaquete, 5);
        String resultadoHTML = formateador.obtenerReporte();

        // 3. VERIFY
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("Kit Home Office"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("(Paquete)"));
        org.junit.jupiter.api.Assertions.assertTrue(resultadoHTML.contains("5"));
    }
}

