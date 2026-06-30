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

class CriterioANDTest {
	
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
	void testSatisfaceSiTodosLosFiltrosSonTrue() {
		List<CriterioBusqueda> filtros =  Arrays.asList(f1, f2);
		CriterioAND sut = new CriterioAND(filtros);
		
		when(f1.satisface(item)).thenReturn(true);
        when(f2.satisface(item)).thenReturn(false);

        assertFalse(sut.satisface(item), "AND debe fallar si uno es falso");
	}
	
	@Test
    void testNoSatisfaceSiAlMenosUnFiltroEsFalse() {
        List<CriterioBusqueda> filtros = Arrays.asList(f1, f2);
        CriterioAND sut = new CriterioAND(filtros);
		
        when(f1.satisface(item)).thenReturn(false);
        when(f2.satisface(item)).thenReturn(true);

        assertFalse(sut.satisface(item), "AND debe fallar si al menos uno es false");
	}

	@Test
	void testANDVacioEsTrue() {
    	CriterioAND sut = new CriterioAND(new ArrayList<>());
    	assertTrue(sut.satisface(item), "Un AND sin filtros no restringe nada (true)");
	}
	

}
