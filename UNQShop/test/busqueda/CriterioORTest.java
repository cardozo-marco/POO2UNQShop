package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;

class CriterioORTest {
	
	private CriterioBusqueda f1;
	private CriterioBusqueda f2;
	private ItemCatalogo item;
	
	@BeforeEach 
	public void setUp() {
		f1 = mock(CriterioBusqueda.class);
		f2 = mock(CriterioBusqueda.class);
		item = mock(ItemCatalogo.class);
	}
	
	@Test
	void testSatisfaceSiAlMenosUnFiltroEsTrue() {
		List<CriterioBusqueda> filtros =  Arrays.asList(f1, f2);
		CriterioOR sut = new CriterioOR(filtros);
		
		when(f1.satisface(item)).thenReturn(true);
        when(f2.satisface(item)).thenReturn(false);

        assertTrue(sut.satisface(item), "OR debe fallar si todos los filtros son falso");
	}
	
	@Test
    void testNoSatisfaceSiTodosLosFiltrosSonFalse() {
        List<CriterioBusqueda> filtros = Arrays.asList(f1, f2);
        CriterioOR sut = new CriterioOR(filtros);
		
        when(f1.satisface(item)).thenReturn(false);
        when(f2.satisface(item)).thenReturn(false);

        assertFalse(sut.satisface(item), "OR debe devolver false si todos sus filtros fallan");
    }
	
	@Test
    void testSatisfaceSiLaListaDeFiltrosEstaVacia() {
        // Caso de borde: una bssqueda sin criterios debería fallar.
        CriterioOR sut = new CriterioOR(new ArrayList<>());

        assertFalse(sut.satisface(item), "Un OR sin opciones no puede satisfacer ninguna (false)");
    }
}
