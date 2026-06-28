package busqueda;

import java.util.List;

import catalogo.ItemCatalogo;

public class CriterioAND implements CriterioBusqueda {
	
	private List<CriterioBusqueda> filtros;
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		for(CriterioBusqueda c: filtros) {
			if(!c.satisface(item)) {
				return false;
			}
		}
		return true;
	}

}
