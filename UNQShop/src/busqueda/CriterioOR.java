package busqueda;

import java.util.List;

import catalogo.ItemCatalogo;

public class CriterioOR implements CriterioBusqueda {

	private List<CriterioBusqueda> filtro;
	
	public CriterioOR(List<CriterioBusqueda> filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		for(CriterioBusqueda c: filtro) {
			if(c.satisface(item)) {
				return true;
			}
		}
		return false;
	}
}
