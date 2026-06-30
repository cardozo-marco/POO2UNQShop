package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.ItemCatalogo;

class CriterioNOTTest {
    
	private CriterioBusqueda mockInterno;
	private ItemCatalogo item;
	
	@BeforeEach
	public void setUp() {
		item = mock(ItemCatalogo.class);
		mockInterno = mock(CriterioBusqueda.class);
	}
	
	@Test
    public void testInvierteElResultadoDelCriterioInterno() {
        CriterioNOT sut = new CriterioNOT(mockInterno);

        // Si el interno dice TRUE, el NOT debe decir FALSE
        when(mockInterno.satisface(item)).thenReturn(true);
        assertFalse(sut.satisface(item));

        // Si el interno dice FALSE, el NOT debe decir TRUE
        when(mockInterno.satisface(item)).thenReturn(false);
        assertTrue(sut.satisface(item));
    }
}