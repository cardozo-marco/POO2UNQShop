package catalogo;

import busqueda.CriterioBusqueda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {
    private Catalogo catalogo;
    private ItemCatalogo itemCumple;
    private ItemCatalogo itemNoCumple;
    private CriterioBusqueda criterioMock;

    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
        itemCumple = mock(ItemCatalogo.class);
        itemNoCumple = mock(ItemCatalogo.class);
        criterioMock = mock(CriterioBusqueda.class);

        catalogo.agregarItem(itemCumple);
        catalogo.agregarItem(itemNoCumple);
    }

    @Test
    public void testBuscarFiltraCorrectamenteDelegandoAlCriterio() {
        // Definimos que el item 1 cumple el criterio y el 2 no
        when(criterioMock.satisface(itemCumple)).thenReturn(true);
        when(criterioMock.satisface(itemNoCumple)).thenReturn(false);

        List<ItemCatalogo> resultado = catalogo.buscar(criterioMock);

        // Verificamos que solo nos devolvió el que cumplió
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(itemCumple));
        assertFalse(resultado.contains(itemNoCumple));
    }
}
