package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.*;

public class CriterioPorPrecioMaximoTest {
    private CriterioPorPrecioMaximo criterioPrecioMaximo;
    private ItemCatalogo itemMock;

    @BeforeEach
    public void setUp() {
        itemMock = mock(ItemCatalogo.class);
        criterioPrecioMaximo = new CriterioPorPrecioMaximo(1000.0);
    }

    @Test
    public void testSatisfaceSiElPrecioBaseEsMenorOIgualAlMaximo() {
        when(itemMock.getPrecioBase()).thenReturn(950.0);
        assertTrue(criterioPrecioMaximo.satisface(itemMock));
    }

    @Test
    public void testNoSatisfaceSiElPrecioBaseSuperaElMaximo() {
        when(itemMock.getPrecioBase()).thenReturn(1001.0);
        assertFalse(criterioPrecioMaximo.satisface(itemMock));
    }
}