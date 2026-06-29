package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorCategoria implements CriterioBusqueda {
	
	private String categoriaBuscada;
	
	public CriterioPorCategoria(String categoriaBuscada) {
		this.categoriaBuscada = categoriaBuscada;
	}
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getCategoria().equals(categoriaBuscada);
	}
}
