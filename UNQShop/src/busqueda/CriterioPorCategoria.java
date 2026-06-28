package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorCategoria implements CriterioBusqueda {
	
	private String categoriaBuscada;
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getCategoria().equals(categoriaBuscada);
	}
}
