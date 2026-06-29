package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.*;

class CriterioPorCategoriaTest {
	private CriterioPorCategoria categoriaCriterio;
    private ItemCatalogo itemMock;

    @BeforeEach
    public void setUp() {
        itemMock = mock(ItemCatalogo.class);
        categoriaCriterio = new CriterioPorCategoria("Indumentaria");
    }

    @Test
    public void testSatisfaceSiLaCategoriaEsIdentica() {
        when(itemMock.getCategoria()).thenReturn("Indumentaria");
        assertTrue(categoriaCriterio.satisface(itemMock));
    }

    @Test
    public void testNoSatisfaceSiLaCategoriaEsDiferente() {
        when(itemMock.getCategoria()).thenReturn("Hogar");
        assertFalse(categoriaCriterio.satisface(itemMock)); 
    }
}

