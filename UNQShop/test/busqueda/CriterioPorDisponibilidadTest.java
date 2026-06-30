package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.*;

class CriterioPorDisponibilidadTest {
	
	private CriterioPorDisponibilidad criterioDisponibilidad;
    private ItemCatalogo itemMock;

    @BeforeEach
    public void setUp() {
        itemMock = mock(ItemCatalogo.class);
        criterioDisponibilidad = new CriterioPorDisponibilidad();
    }

    @Test
    public void testSatisfaceSiElStockEsMayorACero() {
        when(itemMock.getStock()).thenReturn(5);
        assertTrue(criterioDisponibilidad.satisface(itemMock)); 
    }

    @Test
    public void testNoSatisfaceSiElStockEsCero() {
        when(itemMock.getStock()).thenReturn(0);
        assertFalse(criterioDisponibilidad.satisface(itemMock)); 
    }
}
