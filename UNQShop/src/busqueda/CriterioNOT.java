package busqueda;

import catalogo.ItemCatalogo;

public class CriterioNOT implements CriterioBusqueda {
	
	private CriterioBusqueda criterioAEvaluar;
	
	public CriterioNOT(CriterioBusqueda criterioAEvaluar) {
		this.criterioAEvaluar = criterioAEvaluar;
	}
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		return !this.criterioAEvaluar.satisface(item);
	}
}
