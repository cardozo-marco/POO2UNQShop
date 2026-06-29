package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import catalogo.ItemCatalogo;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

class CriterioPorNombreTest {
	
	private ItemCatalogo itemMock; 
	private CriterioBusqueda nombreCriterio;
	
	@BeforeEach
	public void setUp() {
		itemMock = mock(ItemCatalogo.class);
		
	    nombreCriterio = new CriterioPorNombre("Notebook");
	}
	
	@Test
    public void testSatisfaceCuandoElNombreContieneElTextoSinImportarMayusculas() {
        when(itemMock.getNombre()).thenReturn("notebook HP Pavilion");
        assertTrue(nombreCriterio.satisface(itemMock), "Debería aceptar coincidencia parcial en minúsculas");
    }

    @Test
    public void testNoSatisfaceSiElNombreNoContieneElTexto() {
        when(itemMock.getNombre()).thenReturn("Teclado Genius");
        assertFalse(nombreCriterio.satisface(itemMock));
    }
}
